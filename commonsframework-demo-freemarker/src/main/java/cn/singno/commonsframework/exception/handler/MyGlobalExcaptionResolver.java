package cn.singno.commonsframework.exception.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import cn.singno.commonsframework.bean.JsonResult;
import cn.singno.commonsframework.exception.AuthenticationException;

import com.alibaba.fastjson.JSON;

/**
 * <p>名称：GlobalExcaptionResolver.java</p>
 * <p>描述：全局异常解析器</p>
 * <pre>
 *    将全局异常解析成相应的错误信息提示
 * </pre>
 * @author 周光暖
 * @date 2015-3-30 下午10:58:39
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class MyGlobalExcaptionResolver extends GlobalExcaptionResolver
{
	@Override
	protected ModelAndView handleCommonException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		int state = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		if (ex instanceof BindException)
		{
			state = HttpServletResponse.SC_NOT_FOUND;
		}
		else if (ex instanceof AuthenticationException)
		{
			state = HttpServletResponse.SC_UNAUTHORIZED;
		}
		else{
			JsonResult jsonResult = getExceptionJsonMessage(ex);
			request.setAttribute("code", jsonResult.getCode());
			request.setAttribute("message", jsonResult.getMessage());
		}
		
		response.sendError(state, JSON.toJSONString(getExceptionJsonMessage(ex)));
		return new ModelAndView();
	}
}