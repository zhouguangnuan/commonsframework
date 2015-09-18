package cn.singno.commonsframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * web接口安全认证拦截器
 */
public class WebSecurityInterceptor  implements HandlerInterceptor 
{
	/** 
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WebSecurityInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		logger.debug("============================ web接口安全认证拦截器  ================================");
		// TODO N web 防止CSRF攻击，等web安全控制
		String str = null;
		System.out.println(str.toString());
		return Boolean.TRUE; 
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
