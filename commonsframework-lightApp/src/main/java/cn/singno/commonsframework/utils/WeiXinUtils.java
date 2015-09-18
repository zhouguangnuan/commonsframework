package cn.singno.commonsframework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.log4j.Logger;

import cn.singno.commonsframework.constants.DefaultSystemConst;
import cn.singno.commonsframework.constants.WxCodeEnum;
import cn.singno.commonsframework.constants.WxConst.Event;
import cn.singno.commonsframework.constants.WxConst.MsgType;
import cn.singno.commonsframework.exception.BusinessException;
import cn.singno.commonsframework.exception.WxException;
import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;
import cn.singno.commonsframework.weixin.msg.receive.common.R_c_imageMsg;
import cn.singno.commonsframework.weixin.msg.receive.common.R_c_linkMsg;
import cn.singno.commonsframework.weixin.msg.receive.common.R_c_locationMsg;
import cn.singno.commonsframework.weixin.msg.receive.common.R_c_textMsg;
import cn.singno.commonsframework.weixin.msg.receive.common.R_c_videoMsg;
import cn.singno.commonsframework.weixin.msg.receive.common.R_c_voiceMsg;
import cn.singno.commonsframework.weixin.msg.receive.event.R_e_customizeMenusMsg;
import cn.singno.commonsframework.weixin.msg.receive.event.R_e_quickmarkMsg;
import cn.singno.commonsframework.weixin.msg.receive.event.R_e_reportLocationMsg;
import cn.singno.commonsframework.weixin.msg.receive.event.R_e_subscribeMsg;
import cn.singno.commonsframework.weixin.msg.receive.event.R_e_unsubscribeMsg;
import cn.singno.commonsframework.weixin.msg.receive.other.R_o_voiceRecognitionMsg;
import cn.singno.commonsframework.weixin.msg.send.SendMsg;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;


public final class WeiXinUtils {

	private static final Logger logger = Logger.getLogger(WeiXinUtils.class);
	
	/*********************************************** 微信接参全局返回码说明 ***********************************************/
	public static final Map<Integer, WxCodeEnum> WxCodeInfo;
	
	/*********************************************** 微信接参数 ***********************************************/
	private static final String AppID;// (应用ID)
	private static final String AppSecret;// (应用密钥)
	
	private static final String Token;// 令牌
	
	private static final String App_Service_Admin;// (app应用服务器域名) 
	
	private static AccessToken accessToken;
	
	/*********************************************** 微信接口地址 ***********************************************/
	// 获取access_token
	private static final String get_access_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
	
	// 通过code换取网页授权access_token 
	private static final String get_auth_token = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
	
	// 拉取用户信息(需scope为 snsapi_userinfo)
	private static final String get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";
	
	// 检验授权凭证（access_token）是否有效
	private static final String verifyAuthToken = "https://api.weixin.qq.com/sns/auth?access_token={0}&openid={1}";
	
	static
	{
		WxCodeInfo = Maps.newHashMap();
		WxCodeEnum[] wxCodeEnums = WxCodeEnum.values();
		for (WxCodeEnum wxCodeEnum : wxCodeEnums)
		{
			WxCodeInfo.put(wxCodeEnum.getCode(), wxCodeEnum);
		} 
		try 
		{
			Configuration config = new PropertiesConfiguration("lightApp.properties");
			AppID = config.getString("wx.appId").trim();
			AppSecret = config.getString("wx.appSecret").trim();
			App_Service_Admin = config.getString("wx.app.service.admin").trim();
			Token = config.getString("wx.token").trim();
			if (StringUtils.isBlank(AppID) || StringUtils.isBlank(AppSecret) || StringUtils.isBlank(Token)|| StringUtils.isBlank(App_Service_Admin))
			{
				throw new RuntimeException("lightApp.properties config error");
			}
		} catch (ConfigurationException e)
		{
			throw new RuntimeException("lightApp.properties not find");
		}
	}
	/*************************************************************************************************************************/
	
	/**
	 * <p>描述：接收消息对象</p>
	 * <pre>
	 *    InputStream in --> ReceiveMsg
	 * </pre>
	 * @param in
	 * @return
	 */
	public static ReceiveMsg receiveMsg(HttpServletRequest request)
	{
		try
		{
			InputStream in = request.getInputStream();
			return parse(in);
		} catch (IOException e)
		{
			logger.info("未能接收到微信消息", e);
			return null;
		}
	}

	/**
	 * <p>描述：发送消息</p>
	 * <pre>
	 *    javaBean --> xml str 
	 * </pre>
	 * @param msg
	 * @return
	 */
	public static void sendMsg(SendMsg msg, HttpServletResponse response)
	{
		String xmlStr = toXmlStr(msg);
		writeToResponse(response, xmlStr);
	}
	
	/**
	 * <p>描述：获取access token</p>
	 * <pre>
	 * 	微信接口的返回说明：
	 *    成功：{"access_token":"ACCESS_TOKEN","expires_in":7200}
	 *    失败：{"errcode":40013,"errmsg":"invalid appid"}
	 * </pre>
	 * @return
	 */
	public static String getAccessToken()
	{
		if(null != accessToken && !accessToken.isExpires()){
			return accessToken.getAccess_token();
		}
		
		String url = MessageFormat.format(get_access_token, AppID, AppSecret);
		String jsonResult = HttpUtils.get(url);
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonResult);
		assertSuccess(jsonObject);
		Object access_token = jsonObject.getString("access_token"); 
		accessToken = new AccessToken(access_token.toString(), jsonObject.getLong("expires_in"));  
		return access_token.toString();
	}
	
	/**
	 * 描述：通过code换取网页授权access_token 
	 * @param code
	 * @return
	 * {
	 *	   "access_token":"ACCESS_TOKEN",
	 *	   "expires_in":7200,
	 *	   "refresh_token":"REFRESH_TOKEN",
	 *	   "openid":"OPENID",
	 *	   "scope":"SCOPE"
	 *	}
	 */
	public static com.alibaba.fastjson.JSONObject getAuthToken(String code)
	{
		if(StringUtils.isBlank(code)){
			return null; 
		}
		String url = MessageFormat.format(get_auth_token, AppID, AppSecret, code);
		String jsonResult = HttpUtils.get(url);
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonResult);
		assertSuccess(jsonObject);
		return jsonObject;
	}
	
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * @param access_token	网页授权access_token 
	 * @param openid		
	 * @return
	 * {
	 *	   "openid":" OPENID",
	 *	   " nickname": NICKNAME,
	 *	   "sex":"1",
	 *	   "province":"PROVINCE"
	 *	   "city":"CITY",
	 *	   "country":"COUNTRY",
	 *	    "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46", 
	 *		"privilege":[
	 *		"PRIVILEGE1"
	 *		"PRIVILEGE2"
	 *	    ],
	 *	    "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
	 *	}
	 */
	public static com.alibaba.fastjson.JSONObject getUserInfo(String access_token, String openid)
	{
		if(StringUtils.isBlank(access_token) || StringUtils.isBlank(openid))
		{
			return null; 
		}
		String url = MessageFormat.format(get_userinfo, access_token, openid);
		String jsonResult = HttpUtils.get(url);
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonResult);
		assertSuccess(jsonObject);
		return jsonObject;
	}
	
	/**
	 * 检验授权凭证（access_token）是否有效
	 * @param openId			必填
	 * @param access_token		必填
	 */
	public static void verifyAuthToken(String access_token, String openid)
	{
		AssertUtils.notNull(access_token, new WxException(WxCodeEnum.ERROR_CUSTOM_002, "access_token"));
		AssertUtils.notNull(openid, new WxException(WxCodeEnum.ERROR_CUSTOM_002, "openid")); 
		
		String url = MessageFormat.format(verifyAuthToken, access_token, openid);
		String jsonResult = HttpUtils.get(url);
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonResult);
		assertSuccess(jsonObject);
	}
	
	/**
	 * <p>描述：判断是否来自微信的消息</p>
	 * <pre>
	 *    验证消息真实性
	 * </pre>
	 * @param request
	 * @return
	 */
	public static boolean isWxMsg(HttpServletRequest request)
	{
		String signature1 = request.getParameter("signature"); // 微信加密签名
		String timestamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数
		
		if (StringUtils.isBlank(signature1) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce))
		{
			return false;
		}
		
		List<String> params = new ArrayList<String>();
		params.add(timestamp);
		params.add(nonce);
		params.add(Token);
		Collections.sort(params);
		StringBuilder builder = new StringBuilder();
		for(String param : params) {
			builder.append(param);
		}
		
		String signature2 = CryptoUtils.HASHencrypt(builder.toString(), CryptoUtils.ALGORITHM.HASH.SHA_1);
		return signature1.equalsIgnoreCase(signature2);
	}
	
	/**
	 * <p>描述：判断是否来自微信浏览器的请求</p>
	 * <pre>
	 *    验证请求是否是在微信客户端发起
	 *    可以根据此判断,对微信客户端和普通浏览器客户端做区别显示处理
	 * </pre>
	 * @param request
	 * @return
	 */
	public static boolean comeFromWxBrowser(HttpServletRequest request)
	{
		String userAgent = request.getHeader("User-Agent");
		String searchSeq ="MicroMessenger";
		return StringUtils.contains(userAgent, searchSeq);
	}
	
	/**
	 * <p>描述：接入微信公众平台</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param request
	 * @param response
	 */
	public static void accessWx(HttpServletRequest request, HttpServletResponse response)
	{
		if (isWxMsg(request))
		{
			// 随机字符串   
		    String echostr = request.getParameter("echostr");  
			writeToResponse(response, echostr);
			logger.info("接入微信公众平台成功");
			return;
		}
		logger.info("接入微信公众平台失败");
	}
	
	/**
	 * 创建场景二维码
	 * @param scene_str		场景参数
	 * @return
	 */
	public static String creaetSceneQRcode(String scene_str)
	{
		String body = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":\"" + scene_str + "\"}}}";
		String url_get_quickmarkTicket = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + getAccessToken();
		HttpEntity entity = EntityBuilder.create().setText(body).setContentType(ContentType.APPLICATION_JSON).build();
		String result=  HttpUtils.post(url_get_quickmarkTicket, entity);
		
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(result);
		assertSuccess(jsonObject);
		String sceneQRcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + jsonObject.getString("ticket");
		return sceneQRcodeUrl;
	}
	
	
	
	// ===================================================================================
	
	private static Object toBean(JSONObject jsonObject, Class<?> beanClass)
	{
		return JSON.parseObject(jsonObject.toString(), beanClass);
	}
	
	private static void writeToResponse(HttpServletResponse response, String str) 
	{
		if (null == str)
		{
			return;
		}
		PrintWriter out = null;
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			logger.error(e);
		}
		out.write(str);
		if (out != null)
		{
			 out.close();  
			 out = null;  
		}
	}
	
	/**
	 * <p>描述：从输入流解析接收消息对象</p>
	 * <pre>
	 *    InputStream in --> ReceiveMsg
	 * </pre>
	 * @param in
	 * @return
	 */
	private static ReceiveMsg parse(InputStream in)
	{  
		ReceiveMsg receiveMsg = null;
		JSONObject jsonObject = (JSONObject) new XMLSerializer().readFromStream(in);
		switch (EnumUtils.getEnum(MsgType.class, jsonObject.getString("MsgType"))) 
		{
			case text:// 文本消息
				receiveMsg = (R_c_textMsg) toBean(jsonObject, R_c_textMsg.class);
				break;
			case image:// 图片消息
				receiveMsg = (R_c_imageMsg) toBean(jsonObject, R_c_imageMsg.class);
				break;
			case voice:// 语音消息
				if (null != jsonObject.get("Recognition")) {
					receiveMsg = (R_o_voiceRecognitionMsg) toBean(jsonObject, R_o_voiceRecognitionMsg.class);
				}
				else
				{
					receiveMsg = (R_c_voiceMsg) toBean(jsonObject, R_c_voiceMsg.class);
				}
				break;
			case video:// 视频消息
				receiveMsg = (R_c_videoMsg) toBean(jsonObject, R_c_videoMsg.class);
				break;
			case shortvideo:// 小视频消息
				receiveMsg = (R_c_videoMsg) toBean(jsonObject, R_c_videoMsg.class);
				break;
			case location:// 地理位置消息
				receiveMsg = (R_c_locationMsg) toBean(jsonObject, R_c_locationMsg.class);
				break;
			case link:// 链接消息
				receiveMsg = (R_c_linkMsg) toBean(jsonObject, R_c_linkMsg.class);
				break;
			case event:// 事件消息
				switch (EnumUtils.getEnum(Event.class, jsonObject.getString("Event")))
				{
					case subscribe:// 订阅事件
						Object EventKey = jsonObject.get("EventKey");
						if (null!=EventKey && StringUtils.startsWith(EventKey.toString(), "qrscene")) 
						{
							receiveMsg = (R_e_quickmarkMsg) toBean(jsonObject, R_e_quickmarkMsg.class);
						}
						else {
							receiveMsg = (R_e_subscribeMsg) toBean(jsonObject, R_e_subscribeMsg.class);
						}
						break;
					case unsubscribe:// 取消订阅事件
						receiveMsg = (R_e_unsubscribeMsg) toBean(jsonObject, R_e_unsubscribeMsg.class);
						break;
					case SCAN:// 扫描二维码事件
						receiveMsg = (R_e_quickmarkMsg) toBean(jsonObject, R_e_quickmarkMsg.class);
						break;
					case LOCATION:// 上报地理位置事件
						receiveMsg = (R_e_reportLocationMsg) toBean(jsonObject, R_e_reportLocationMsg.class);
						break;
					case CLICK:// 自定义菜单拉取消息事件
						receiveMsg = (R_e_customizeMenusMsg) toBean(jsonObject, R_e_customizeMenusMsg.class);
						break;
					case VIEW:// 自定义菜单链接跳转事件
						receiveMsg = (R_e_customizeMenusMsg) toBean(jsonObject, R_e_customizeMenusMsg.class);
						break;
				}
		} 
		return receiveMsg;
	}
	
	/**
	 * <p>描述：将发送消息对象转换为xml文本格式</p>
	 * <pre>
	 *    javaBean --> xml str 
	 * </pre>
	 * @param msg
	 * @return
	 */
	private static String toXmlStr(SendMsg msg)
	{
		if (null == msg) {
			return null;
		}
		return XmlUtils.formatXml(msg.toDocument(), DefaultSystemConst.DEFAULT_UNICODE, false);
	}
	
	/**
	 * 抛出微信异常
	 * @param jsonResult
	 */
	private static void assertSuccess(com.alibaba.fastjson.JSONObject jsonObject)
	{
		AssertUtils.notNull(jsonObject, new WxException(WxCodeEnum.ERROR_CUSTOM_001));
		
		Object errcode = jsonObject.get("errcode");
		if(null!=errcode && ObjectUtils.notEqual(0, (Integer)errcode))
		{
			WxCodeEnum wxCodeEnum = WxCodeInfo.get((Integer)errcode);
			if(null != wxCodeEnum)
			{
				throw new WxException(wxCodeEnum);
			}
			else{
				throw new WxException((Integer)errcode, jsonObject.getString("errmsg"));
			}
		}
	}
	
	/**
	 * 公众号的全局唯一票据
	 * @author Administrator
	 */
	private static class AccessToken{
		
		private String access_token;
		private Long expires_in;// 单位秒
		private Long getTime;// 单位秒
		
		public AccessToken(String access_token, Long expires_in) {
			super();
			this.access_token = access_token;
			this.expires_in = expires_in;
			this.getTime = System.currentTimeMillis()/1000;
		}
		
		public String getAccess_token() {
			return access_token;
		}
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}
		public Long getExpires_in() {
			return expires_in;
		}
		public void setExpires_in(Long expires_in) {
			this.expires_in = expires_in;
		}
		public Long getGetTime() {
			return getTime;
		}
		public void setGetTime(Long getTime) {
			this.getTime = getTime;
		}
		
		public Boolean isExpires(){
			Long now = System.currentTimeMillis()/1000;
			Long delay = 20L;
			
			if(now - this.getTime > this.expires_in - delay){
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}
	}
}
