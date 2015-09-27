package cn.singno.commonsframework.module.controller;

import java.net.HttpCookie;
import java.util.List;

import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class CookieTestControllerTest
{
//	private HttpClient httpClient = HttpClients.createDefault();
	private HttpClient httpClient = new DefaultHttpClient();
	
	@Test
	public void testname() throws Exception
	{
		String uri = "http://localhost:8080/cookie/test";

		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader("Referer", "www.baidu.com");
		httpGet.setHeader("Cookie", getCookies("JSESSIONID", "1DA61BAC92AB2B061617A4487D94BBB6"));
		HttpResponse response = httpClient.execute(httpGet);
		Header[] headers = response.getHeaders("Set-Cookie");
		
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, Charsets.UTF_8);
		httpGet.releaseConnection();
		System.out.println(result);
	}
	
	@Test
	public void testname2() throws Exception
	{
		String uri = "http://localhost:8080/users";

		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader("Referer", "www.baidu.com");
		httpGet.setHeader("Cookie", getCookies("singno_session_id", "0be49afa-e232-454a-8a1b-ac95a3c85159"));
		HttpResponse response = httpClient.execute(httpGet);
		Header[] headers = response.getHeaders("Set-Cookie");
		
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, Charsets.UTF_8);
		httpGet.releaseConnection();
		System.out.println(result);
	}
	
	@Test
	public void testname3() throws Exception
	{
		// CSRF_TOKEN →62dac9f2ff0e774acf01440b75da1449
		String uri = "http://123.56.162.129/demo2/admin/test1?str=111";
		
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader("CSRF_TOKEN", "62dac9f2ff0e774acf01440b75da1449");
		HttpResponse response = httpClient.execute(httpGet);
		
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, Charsets.UTF_8);
		httpGet.releaseConnection();
		System.out.println(result);
	}
	
	
	
	
	// =============================================================================================
	
	private String getCookies(String cookieName, String value)
	{
		StringBuilder sb = new StringBuilder();
		List<Cookie> cookies = ((DefaultHttpClient)httpClient).getCookieStore().getCookies();
		Cookie cookie_sessionId = new BasicClientCookie(cookieName, value);
		cookies.add(cookie_sessionId);
		for (Cookie cookie : cookies)
		{
			sb.append(cookie.getName() + "=" + cookie.getValue() + ";");
		}
		return sb.toString();
	}
}
