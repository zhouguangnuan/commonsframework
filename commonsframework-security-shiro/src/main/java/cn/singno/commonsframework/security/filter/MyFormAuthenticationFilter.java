/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.security.filter</p>
 * <p>文件名：MyFormAuthenticationFilter.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-28-上午12:42:25</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**<p>名称：MyFormAuthenticationFilter.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-28 上午12:42:25
 * @version 1.0.0
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter
{
	private String captcha = "captcha";
	
	/**
	 * 增加对验证码的验证
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
	{
		// TODO N 增加对验证码的验证
		
		return super.isAccessAllowed(request, response, mappedValue);
	}
	
	/**
	 * 
	 */
	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae)
	{
	        request.setAttribute(getFailureKeyAttribute(), ae);
	}

	/**
	 * @return the captcha
	 */
	public String getCaptcha()
	{
		return captcha;
	}

	/**
	 * @param captcha the captcha to set
	 */
	public void setCaptcha(String captcha)
	{
		this.captcha = captcha;
	}
}
