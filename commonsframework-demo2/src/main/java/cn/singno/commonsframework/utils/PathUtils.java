package cn.singno.commonsframework.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 路径管理工具
 */
public class PathUtils 
{

	public static Boolean isAppURI(HttpServletRequest request)
	{
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		if(StringUtils.startsWith(requestURI, contextPath + "/mobile/app"))
		{ 
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static Boolean isWxURI(HttpServletRequest request)
	{
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		if(StringUtils.startsWith(requestURI, contextPath + "/mobile/wx"))
		{ 
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
