package cn.singno.commonsframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.singno.commonsframework.constant.ResultStateEnum;
import cn.singno.commonsframework.exception.BusinessException;
import cn.singno.commonsframework.utils.WeiXinUtils;

/**
 * 移动微信接口安全认证拦截器
 */
public class WxSecurityInterceptor  implements HandlerInterceptor 
{
	/** 
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WxSecurityInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		logger.debug("============================ 移动微信接口安全认证拦截器  ================================");
		if(!WeiXinUtils.comeFromWxBrowser(request))
		{
			throw new BusinessException(ResultStateEnum.CLINET_UNAUTO_ERROR, "请在微信客户端访问");
		}
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
