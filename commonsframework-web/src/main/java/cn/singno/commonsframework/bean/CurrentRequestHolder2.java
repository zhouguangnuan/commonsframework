package cn.singno.commonsframework.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class CurrentRequestHolder2
{
        @Autowired(required=false)
        private HttpServletRequest request;
        
        @Autowired(required=false)
        private HttpServletResponse response;

        public HttpServletRequest getRequest()
        {
                return request;
        }

        public void setRequest(HttpServletRequest request)
        {
                this.request = request;
        }

        public HttpServletResponse getResponse()
        {
                return response;
        }

        public void setResponse(HttpServletResponse response)
        {
                this.response = response;
        }
}
