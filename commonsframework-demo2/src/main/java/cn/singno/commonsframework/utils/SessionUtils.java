package cn.singno.commonsframework.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import cn.singno.commonsframework.bean.CurrentRequestHolder;
import cn.singno.commonsframework.bean.CurrentRequestHolder2;
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
		return (User)SessionUtils.getAttribute(request, CURRENT_USER);
	}
	
	/**
	 * 设置当前登录用户信息
	 * @param request
	 * @param user
	 */
	public static void setCurrentUser(HttpServletRequest request, User user)
	{
		SessionUtils.setAttribute(request, CURRENT_USER, user);
	}
	
	/**
	 * 设置属性
	 * @param request
	 * @param name
	 * @param value
	 */
	public static void setAttribute(HttpServletRequest request, String name, Object value)
	{
		HttpSession session = request.getSession(Boolean.TRUE);
		session.setAttribute(name, value);
	}
	
	/**
	 * 获取属性
	 * @param request
	 * @param name
	 * @return
	 */
	public static Object getAttribute(HttpServletRequest request, String name)
	{
		HttpSession session = request.getSession();
		if(null == session){
			return null;
		}
		return session.getAttribute(name);
	}
	
	public static void test(){
	        
	        HttpServletRequest request = CurrentRequestHolder.getCurrentRequest();
	        HttpServletResponse response = CurrentRequestHolder.getCurrentResponse();
	        
	        
	        System.out.println("request：" + JSON.toJSONString(request.getParameterMap()));
                
                System.out.println("request thread：" + Thread.currentThread().getId());
                
                
                System.out.println(response.getHeader("name"));
	}
	
	public static void test2(){
                
	        CurrentRequestHolder2 currentRequestHolder2 = SpringUtils.getBean(CurrentRequestHolder2.class);
	        
                HttpServletRequest request = currentRequestHolder2.getRequest();
                HttpServletResponse response = currentRequestHolder2.getResponse();
                
                System.out.println("request：" + JSON.toJSONString(request.getParameterMap()));
                
                System.out.println("request thread：" + Thread.currentThread().getId());
                
                
                System.out.println(response.getHeader("name"));
        }
}
