package cn.singno.commonsframework.interceptor;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.singno.commonsframework.bean.CurrentRequestHolder;
import cn.singno.commonsframework.utils.SpringUtils;

/**
 * 请求记录拦截器
 * @author Administrator
 *
 */
public class RequestRecordInterceptor  implements HandlerInterceptor 
{
	/** 
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RequestRecordInterceptor.class);
	
	private NamedThreadLocal<Long>  startTimeThreadLocal  = new NamedThreadLocal<Long>("StopWatch-StartTime"); 
	
	private long slowFlag = 500L;// 慢请求标准
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		logger.debug("============================ 请求记录拦截器  ================================");
		
		CurrentRequestHolder.setCurrentRequest(request);
		CurrentRequestHolder.setCurrentResponse(response);
		
		long beginTime = System.currentTimeMillis();// 开始时间  
		startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
        
		logger.info("uri:     " + request.getRequestURI());
		logger.info("params:     " + JSON.toJSONString(request.getParameterMap()));
		
		return true; 
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
	        CurrentRequestHolder.clean();
	        
		long endTime = System.currentTimeMillis();// 结束时间  
	        long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）  
	        long consumeTime = endTime - beginTime;// 消耗的时间  
	        if(consumeTime > slowFlag) {  
	        //TODO 记录到日志文件  
        	
        	logger.info(String.format("%s consume %d millis", request.getRequestURI(), consumeTime)); 
        }
	}
}
