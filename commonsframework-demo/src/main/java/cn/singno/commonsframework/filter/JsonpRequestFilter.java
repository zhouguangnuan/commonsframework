package cn.singno.commonsframework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import cn.singno.commonsframework.bean.ResponseWrapper;

public class JsonpRequestFilter implements Filter
{

	@Override
	public void destroy()
	{
		

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
//		// 使用我们自定义的响应包装器来包装原始的ServletResponse
		ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) response);
//		// 这句话非常重要，注意看到第二个参数是我们的包装器而不是response
		chain.doFilter(request, wrapper);
//		String result = wrapper.getResult();
//		System.out.println(result);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		

	}
}