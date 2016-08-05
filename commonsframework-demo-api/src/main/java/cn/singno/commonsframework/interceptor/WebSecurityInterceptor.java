package cn.singno.commonsframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.singno.commonsframework.constant.ResultStateEnum;
import cn.singno.commonsframework.exception.BusinessException;
import cn.singno.commonsframework.utils.CSRFManagerUtils;

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
		
		// web安全控制...
		
		// 防止CSRF攻击
		if(request.getMethod().equalsIgnoreCase("POST"))
		{
			Boolean isCSRFsafe = CSRFManagerUtils.verifyCSRF(request);
			if(!isCSRFsafe)
			{
				throw new BusinessException(ResultStateEnum.CSRF_VERIFY_ERROR);
			}
		}
		
		response.setHeader(CSRFManagerUtils.CSRF_TOKEN, CSRFManagerUtils.getCSRFtotkenFromService(request));
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
