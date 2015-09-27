package cn.singno.commonsframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class JsonpInterceptor implements HandlerInterceptor 
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JsonpInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception 
	{
		logger.debug(request.getParameter("token"));
		
//			ResponseWrapper wrapper = (ResponseWrapper)response;
//			String result = wrapper.getResult();
//			logger.debug(result); 
		
//		if(object instanceof HandlerMethod)
//		{
//			HandlerMethod handlerMethod = (HandlerMethod)object;
////			handlerMethod.getReturnType().
//			handlerMethod.get
//		}
		
		
		// 使用我们自定义的响应包装器来包装原始的ServletResponse
//		ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse)response);
//		String result = wrapper.getResult();
//		logger.debug(result);
		
		
		
		
		  
//		HttpServletResponseWrapper  httpServletResponseWrapper  = new HttpServletResponseWrapper (response);
//		logger.debug(response.toString());
//		logger.debug(httpServletResponseWrapper.toString());
	}
 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception 
	{
//		logger.debug(JSON.toJSONString(object));
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception 
	{
		logger.debug(request.getParameter("token"));
		
		return Boolean.TRUE;
	}
	
}
