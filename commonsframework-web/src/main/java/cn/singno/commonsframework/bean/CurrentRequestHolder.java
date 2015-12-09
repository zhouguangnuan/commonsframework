package cn.singno.commonsframework.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CurrentRequestHolder
{
        private static final ThreadLocal<HttpServletRequest> request_threadLocal = new ThreadLocal<HttpServletRequest>();
        
        private static final ThreadLocal<HttpServletResponse> response_threadLocal = new ThreadLocal<HttpServletResponse>();
        
        public static void setCurrentRequest(HttpServletRequest request)
        {
                request_threadLocal.set(request);
        }
        
        public static HttpServletRequest getCurrentRequest()
        {
                return request_threadLocal.get();
        }
        
        public static void setCurrentResponse(HttpServletResponse response)
        {
                response_threadLocal.set(response);
        }
        
        public static HttpServletResponse getCurrentResponse()
        {
                return response_threadLocal.get();
        }
        
        public static void clean(){
                request_threadLocal.remove();
                response_threadLocal.remove();
        }
}
