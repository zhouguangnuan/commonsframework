package cn.singno.commonsframework.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

public class HttpServletUtils
{
        public static HttpServletRequest getRequest(){
                return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
        }
        
        public static HttpServletResponse getResponse(){
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                return  ((ServletWebRequest)requestAttributes).getResponse();
        }
}
