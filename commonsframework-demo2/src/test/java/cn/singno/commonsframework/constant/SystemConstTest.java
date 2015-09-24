/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.constant</p>
 * <p>文件名：SystemConstTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-7-下午3:00:55</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.constant;

import org.apache.http.client.methods.HttpPost;
import org.junit.Test;

import cn.singno.commonsframework.utils.HttpUtils;

/**
 * <p>名称：SystemConstTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-5-7 下午3:00:55
 * @version 1.0.0
 */
public class SystemConstTest
{
	@Test
	public void testname() throws Exception
	{
		System.err.println("111");
	}
	
	@Test 
	public void testname2() throws Exception 
	{
		String url = "http://123.56.162.129/demo2/admin/test1?str=111";
		HttpPost request = new HttpPost(url);
//		HttpGet request = new HttpGet(url);
		
		request.setHeader("CSRFtoken", "4d2dbc88704dd12dc41db34a3836d42c");
		
		String result=  HttpUtils.executeRequest(request, null);
		System.out.println(result);
	}
}
