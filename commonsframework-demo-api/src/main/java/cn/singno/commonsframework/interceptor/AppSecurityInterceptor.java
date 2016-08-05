package cn.singno.commonsframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.singno.commonsframework.constant.ResultStateEnum;
import cn.singno.commonsframework.exception.BusinessException;

/**
 * 移动原生app接口安全认证拦截器
 */
public class AppSecurityInterceptor  implements HandlerInterceptor 
{
	/** 
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AppSecurityInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		logger.debug("============================ 移动原生app接口安全认证拦截器  ================================");
		// TODO N app安全校验算法
		if(true)
		{
			throw new BusinessException(ResultStateEnum.CLINET_UNAUTO_ERROR, "请在app客户端访问");
		}
		return Boolean.TRUE; 
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		

	}
}
