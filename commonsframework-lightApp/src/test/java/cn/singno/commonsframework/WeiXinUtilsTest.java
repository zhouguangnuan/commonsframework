/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework</p>
 * <p>文件名：WeiXinUtilsTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-27-下午1:05:28</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework;


import java.util.Map;

import org.junit.Test;

import cn.singno.commonsframework.constants.WxConst.QRcodeType;
import cn.singno.commonsframework.utils.WeiXinUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * <p>名称：WeiXinUtilsTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-5-27 下午1:05:28
 * @version 1.0.0
 */
public class WeiXinUtilsTest
{
	
	@Test
	public void testParse() throws Exception
	{
//		String xml = " <xml> <ToUserName><![CDATA[toUser]]></ToUserName> <FromUserName><![CDATA[fromUser]]></FromUserName>  <CreateTime>1348831860</CreateTime> <MsgType>event</MsgType> <Content><![CDATA[this is a test]]></Content> <MsgId>1234567890123456</MsgId> </xml>";
//		InputStream in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
//		ReceiveMsg receiveMsg = WeiXinUtils.parse(in);
//		System.out.println(receiveMsg.getMsgType());
	}
	
	@Test
	public void testToXmlStr() throws Exception
	{
//		S_pr_textMsg msg = new S_pr_textMsg();
//		msg.setContent("content");
//		msg.setCreateTime(new Date().getTime());
//		msg.setFromUserName("fromUserName");
//		msg.setMsgType("msgType");
//		msg.setToUserName("toUserName");
//		
//		String xmlStr = WeiXinUtils.toXmlStr(msg);
//		System.out.println(xmlStr);
	}
	
	@Test
	public void testGetAccessToken() throws Exception {
		System.out.println(WeiXinUtils.getAccessToken());
	}
	
	@Test
	public void testGetAuthToken() throws Exception 
	{
		String code = "01104ef45939dd6f4796565b4fe14eer"; 
		JSONObject jsonObject = WeiXinUtils.getAuthToken(code);
		System.out.println(jsonObject.toString());
	}
	
	@Test
	public void testGetUserinfo() throws Exception {
		String access_token = "OezXcEiiBSKSxW0eoylIeOYkSU4hgXa-HbNLTsIHnBhDXGtQ9E6BUsRwawqijxf4rSvdGAXUj07f6STxXD13kwbypn2MwuLZ24cW9XWYuTGzYqj_zjmQIrOHz6Iu5We58E45934SPvozL7P9AVErIA"; 
		String openid = "oJ6pEw7ML27fvjNuSEAncgyh1f70"; 
		JSONObject jsonObject = WeiXinUtils.getUserInfo(access_token, openid);
		System.out.println(jsonObject.toString()); 
	}
	
	@Test
	public void testCreaetSceneQRcode() throws Exception {
		String scene_str = "abc123";
		String ticket = WeiXinUtils.creaetSceneQRcode(QRcodeType.QR_LIMIT_STR_SCENE, null, scene_str);
		System.out.println(ticket); 
	}
	
	@Test
	public void testAddKfaccount() throws Exception 
	{
//		{
//		     "kf_account" : "test1@test",
//		     "nickname" : "客服1",
//		     "password" : "pswmd5",
//		}
		// kf_account  @  后面的账号 要到微信公众平台设置的  而不是微信账号ID
		String kf_account = "singno@singno_world";
		String nickname = "singnoWorld";
		String password = "1314ainana";
		WeiXinUtils.addKfaccount(kf_account, nickname, password);
	}
	
	
	@Test
	public void testSendTempleMsg() throws Exception {
		Map<String, Object> msgBody = Maps.newHashMap();
		Map<String, Object> data = Maps.newHashMap();
		msgBody.put("touser", "oJ6pEw7ML27fvjNuSEAncgyh1f70");
		msgBody.put("template_id", "nNtnhJQSUp-3qJMj-NwVGWqitKokp5Yx4O_vt8EQ02g");
		msgBody.put("topcolor", "#FF0000");
		msgBody.put("data", data);
		data.put("first", ImmutableMap.of("value", "检查报告提醒", "color", "#173177"));
		data.put("keyword1", ImmutableMap.of("value", "体检报告", "color", "#173177"));
		data.put("keyword2", ImmutableMap.of("value", "周光暖", "color", "#173177"));
		data.put("keyword3", ImmutableMap.of("value", "2015-12-12", "color", "#173177"));
		data.put("remark", ImmutableMap.of("value", "欢迎再次购买！", "color", "#173177")); 
		
		String msgid = WeiXinUtils.sendTempleMsg(msgBody);
		System.out.println(msgid);
	}
}
