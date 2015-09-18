package cn.singno.commonsframework.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.singno.commonsframework.entity.User;

public class SessionUtils 
{
	
	private static final String CURRENT_USER = "CURRENT_USER";
	
	/**
	 * 获取当前登录用户信息
	 * @param request
	 */
	public static User getCurrentUser(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if(null == session){
			return null;
		}
		return (User)session.getAttribute(CURRENT_USER);
	}
	
	/**
	 * 设置当前登录用户信息
	 * @param request
	 * @param user
	 */
	public static void setCurrentUser(HttpServletRequest request, User user)
	{
		HttpSession session = request.getSession(Boolean.TRUE);
		session.setAttribute(CURRENT_USER, user);
	}
}
