package cn.singno.commonsframework.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 名称：CSRFManagerUtils.java<br>
 * 描述：CSRF管理工具类<br>
 * <pre>主要功能为CSRFtoken的获取、校验</pre>
 * @author 周光暖
 * @date 2015-3-17 上午9:06:23
 * @version 1.0.0
 */
public class CSRFManagerUtils
{
	
	public static final String CSRF_TOKEN = "CSRFtoken";
	
	/**
	 * 从服务端获取CSRFtoken
	 * @param request
	 * @return
	 */
	public synchronized static String getCSRFtotkenFromService(HttpServletRequest request)
	{
		Object token = SessionUtils.getAttribute(request, CSRF_TOKEN);
        if (ValidateUtils.isNull(token))
        {
            token = CryptoUtils.HASHencrypt(SerialUtils.buildRefrenceId(), CryptoUtils.ALGORITHM.HASH.MD5);
            SessionUtils.setAttribute(request, CSRF_TOKEN, token);
        }
        return (String)token;
	}
	
	/**
	 * 从客户端获取CSRFtoken
	 * @param request
	 * @return
	 */
	public synchronized static String getCSRFtotkenFromClient(HttpServletRequest request)
	{ 
        String token = request.getHeader(CSRF_TOKEN);
        if (StringUtils.isBlank(token))
        {
            token = request.getParameter(CSRF_TOKEN);
            if(StringUtils.isBlank(token)){
            	token = (String) request.getAttribute(CSRF_TOKEN);
            }
        }
        return token;
	}
	
	/**
	 * 验证客户端CSRFtoken是否合法
	 * @param request
	 * @return
	 */
	public static Boolean verifyCSRF(HttpServletRequest request)
	{
		String clientCSRFtoken = getCSRFtotkenFromClient(request);
		String serviceCSRFtoken = getCSRFtotkenFromService(request);
		
		if (StringUtils.equals(clientCSRFtoken, serviceCSRFtoken))
        {
            return Boolean.TRUE; 
        }
        else
        {
            return Boolean.FALSE;
        }
	}
}
