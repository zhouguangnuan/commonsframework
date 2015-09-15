package cn.singno.commonsframework.bind.method;


import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.singno.commonsframework.bind.annotation.CurrentUser;
import cn.singno.commonsframework.bind.annotation.EnumConst;
import cn.singno.commonsframework.constants.SexEnum;

/**
 * 名称：CurrentUserMethodArgumentResolver.java<br>
 * 描述：用于绑定@CurrentUser的方法参数解析器<br>
 * <pre></pre>
 * @author 周光暖
 * @date 2015-3-23 下午9:52:15
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class EnumConstArgumentResolver implements HandlerMethodArgumentResolver 
{

    public EnumConstArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(EnumConst.class)) {
            return true;
        }
        return false;
    }

    
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
	    EnumConst enumConstAnnotation = parameter.getParameterAnnotation(EnumConst.class);
	    
	    
	    
        return SexEnum.MAN;
    }
}
