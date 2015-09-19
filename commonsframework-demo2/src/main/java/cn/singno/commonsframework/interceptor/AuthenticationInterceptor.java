package cn.singno.commonsframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.singno.commonsframework.constant.ResultStateEnum;
import cn.singno.commonsframework.entity.User;
import cn.singno.commonsframework.exception.AuthenticationException;
import cn.singno.commonsframework.utils.PathUtils;
import cn.singno.commonsframework.utils.SessionUtils;
import cn.singno.commonsframework.utils.WeiXinUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户认证拦截器
 */
public class AuthenticationInterceptor  implements HandlerInterceptor 
{
	/** 
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AuthenticationInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		logger.debug("============================ 用户认证拦截器  ================================");
		
		
		User user = SessionUtils.getCurrentUser(request);
		if(null != user){
			return Boolean.TRUE;
		}
		
		if(PathUtils.isAppURI(request))
		{
			// 有免登陆机制
			String code = request.getParameter("code");
			if(null == code){
				throw new AuthenticationException(ResultStateEnum.AUTHORIZED_ERROR);
			}
			return Boolean.TRUE;
		}
		else if(WeiXinUtils.comeFromWxBrowser(request))
		{   
			// 有免登陆机制
			String code = request.getParameter("code");
			JSONObject authToken = WeiXinUtils.getAuthToken(code);
//				{                                     
//				   "access_token":"ACCESS_TOKEN",    
//				   "expires_in":7200,                
//				   "refresh_token":"REFRESH_TOKEN",  
//				   "openid":"OPENID",                
//				   "scope":"SCOPE"                   
//				}
			if(null == authToken)
			{
				throw new AuthenticationException(ResultStateEnum.AUTHORIZED_ERROR);
			}
			String openid = authToken.getString("openid");
			// TODO N 查询用户信息，没有则创建用户，并将用户信息存在session中
			
			// 注册用户新消息
			JSONObject wxUserInfo = WeiXinUtils.getUserInfo(authToken.getString("access_token"), openid);
//				{
//				   "openid":"OPENID",
//				   "nickname": NICKNAME,
//				   "sex":"1",
//				   "province":"PROVINCE"
//				   "city":"CITY",
//				   "country":"COUNTRY",
//				   "headimgurl":"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46", 
//				   "privilege":[
//					"PRIVILEGE1"
//					"PRIVILEGE2"
//				    ],
//				   "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
//				}
			user = new User();
			user.setName(wxUserInfo.getString("nickname"));
			user.setHeadimgurl(wxUserInfo.getString("headimgurl"));
			user.setSex(wxUserInfo.getInteger("sex"));
			
			SessionUtils.setCurrentUser(request, user);
			return Boolean.TRUE;
		}
		throw new AuthenticationException(ResultStateEnum.AUTHORIZED_ERROR);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		// TODO Auto-generated method stub

	}
}
