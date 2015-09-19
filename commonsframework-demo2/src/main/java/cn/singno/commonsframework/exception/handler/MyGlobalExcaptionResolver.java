package cn.singno.commonsframework.exception.handler;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import cn.singno.commonsframework.bean.JsonResult;
import cn.singno.commonsframework.constant.SystemConst;
import cn.singno.commonsframework.constants.DefaultDescribableEnum;
import cn.singno.commonsframework.exception.AuthenticationException;
import cn.singno.commonsframework.exception.BusinessException;
import cn.singno.commonsframework.exception.ConstraintViolationException;
import cn.singno.commonsframework.exception.DescribableException;
import cn.singno.commonsframework.utils.ConstraintValidateUtils;
import cn.singno.commonsframework.utils.ExceptionUtils;
import cn.singno.commonsframework.utils.PathUtils;
import cn.singno.commonsframework.utils.WeiXinUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

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
		
		// 区分请求的终端类型，区别提示
		if(PathUtils.isAppURI(request))
		{
			request.setAttribute("ClientType", SystemConst.ClientType.MOBILE_APP);
		} 
		else if(WeiXinUtils.comeFromWxBrowser(request))
		{
			request.setAttribute("ClientType", SystemConst.ClientType.MOBILE_WX);
		}
		
		response.sendError(state, JSON.toJSONString(getExceptionJsonMessage(ex)));
		return new ModelAndView();
	}
}