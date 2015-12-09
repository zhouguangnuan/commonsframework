package cn.singno.commonsframework.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.google.common.collect.Maps;

import cn.singno.commonsframework.constants.DefaultSystemConst;

/**
 * <p>File：HttpUtils.java</p>
 * <p>Title: http请求工具</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-4 下午1:48:52</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@SuppressWarnings("all")
public class HttpUtils
{
	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	private static final String MOBILE_URL = "http://www.ip138.com:8080/search.asp?action=mobile&mobile={0}";

	// 私有构造器，防止类的实例化
	private HttpUtils()
	{
		super();
	}
    
	// 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复
	private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler()
	{
		// 自定义的恢复策略
		public boolean retryRequest(IOException exception, int executionCount, HttpContext context)
		{
			// 设置恢复策略，在发生异常时候将自动重试3次
			if (executionCount >= 3)
			{
				return false;
			}
			if (exception instanceof NoHttpResponseException)
			{
				return true;
			}
			if (exception instanceof SSLHandshakeException)
			{
				return false;
			}
			HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
			boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
			if (!idempotent)
			{
				return true;
			}
			return false;
		}
	};

	// 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理
	private static ResponseHandler<String> responseHandler = new ResponseHandler<String>()
	{
		// 自定义响应处理
		public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException
		{
			HttpEntity entity = response.getEntity();
			if (entity != null)
			{
				String charset = EntityUtils.getContentCharSet(entity) == null ? DefaultSystemConst.DEFAULT_UNICODE : EntityUtils.getContentCharSet(entity);
				return new String(EntityUtils.toByteArray(entity), charset);
			} else
			{
				return null;
			}
		}
	};

	// =====================================================================================================================================
	
	/**
	 * 根据手机号码获取号码所在地区
	 * @param mobile		手机号码
	 * @return String 		所在地区
	 */
	public static String getAreaFromMobile(String mobile)
	{
		String result = null;

		if (ValidateUtils.isMobile(mobile))
		{
			String url = MessageFormat.format(MOBILE_URL, mobile);
			logger.info("从以下地址获取手机号所在地区：{}", url);
			String html = get(url, "GBK");
			String[] strings = HtmlUtils.getTextByTagName(html, "td", "class", "tdc2", "gb2312");
			if (null != strings && strings.length >= 2)
			{
				result = StringUtils.replace(strings[1], "&nbsp;", "");
			}
		}
		return result;
	}
    
	
	
	// =====================================================================================================================================

	/**
	 * GET提交，不指定编码
	 * @param url		
	 * @return String 	响应的内容
	 */
	public static String get(String url)
	{
		HttpGet httpGet = new HttpGet(url);
		return executeRequest(httpGet);
	}
	
	/**
	 * GET提交，不指定编码
	 * @param url          请求地址
	 * @param map       请求参数
	 * @return
	 * @throws URISyntaxException 
	 */
	public static String get(String url, Map<String, String> map)
	{
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (Iterator<Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();)
                {
                        Entry<String, String> entry = it.next();
                        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                try
                {
                        URI uri = new URIBuilder().setPath(url).setParameters(nvps).build();
                        HttpGet httpGet = new HttpGet(uri);
                        return executeRequest(httpGet);
                } catch (URISyntaxException e)
                {
                        logger.error(e.getMessage(), e);
                }
	        return null;
	}

	/**
	 * GET提交，指定编码
	 * @param url 			请求地址
	 * @param charsetName 	编码
	 * @return String 			响应的内容
	 */
	public static String get(String url, String charsetName)
	{
		HttpGet httpGet = new HttpGet(url);
		return executeRequest(httpGet, charsetName);
	}

	/**
	 * POST提交
	 * @param url			提交地址
	 * @param entity 		提交的参数对象
	 * @return HttpEntity 
	 */
	public static String post(String url, HttpEntity entity)
	{
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		return executeRequest(httpPost);
	}

	/**
	 * POST提交
	 * @param url 	提交地址
	 * @param entity 	提交参数对象
	 * @return String 	响应内容
	 */
	public static String post(String url, HttpEntity entity, String charset)
	{
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		return executeRequest(httpPost, charset);
	}

	/**
	 * POST提交
	 * @param url 	提交地址
	 * @param map 	提交的参数MAP
	 * @return String	 响应的内容
	 */
	public static String post(String url, Map<String, String> map)
	{
		HttpEntity httpEntity = getEntityFromMap(map, null);
		return post(url, httpEntity);
	}

	/**
	 * POST提交
	 * @param url 			提交地址
	 * @param map 			提交的参数MAP
	 * @param charsetName 	字符编码
	 * @return String 响应的内容
	 */
	public static String post(String url, Map<String, String> map, String charsetName)
	{
		HttpEntity httpEntity = getEntityFromMap(map, charsetName);
		return post(url, httpEntity);
	}
	
	/**
	 * Post 文件上传
	 * @param url                  提交地址
	 * @param params          提交参数集, 键/值对
	 * @param charset          参数编码方式（默认为UTF-8）
	 * @param field               接收上传文件的字段
	 * @param file                 上传的文件
	 * @return
	 * @throws Exception
	 */
	public static String postMultipart(String url, Map<String, String> params, String charset, String field, File file) throws Exception
	{
		if (StringUtils.isBlank(url))
		{
			return null;
		}
		if (StringUtils.isBlank(charset))
		{
		        charset = DefaultSystemConst.DEFAULT_UNICODE;
		}
		// 创建HttpClient实例
		HttpClient httpclient = getHttpClient();
		HttpPost hp = new HttpPost(url);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		multipartEntityBuilder.setCharset(Consts.UTF_8);
		if (null != params && params.size() > 0)
		{
			for (Map.Entry<String, String> entry : params.entrySet())
			{
				multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(), ContentType.create(MediaType.TEXT_PLAIN_VALUE, charset));
			}
		}
		if (StringUtils.isNotBlank(field) && null != file)
		{
                        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE).addPart("file", new FileBody(file, ContentType.create(MediaType.MULTIPART_FORM_DATA_VALUE, charset), file.getName()));
		}
		hp.setEntity(multipartEntityBuilder.build());
		// 发送请求，得到响应
		String responseStr = null;
		try
		{
			responseStr = httpclient.execute(hp, responseHandler);
		} catch (ClientProtocolException e)
		{
			logger.error("客户端连接协议错误：" + e);
		} catch (IOException e)
		{
			logger.error("IO操作异常：" + e);
		} finally
		{
			abortConnection(hp, httpclient);
		}
		return responseStr;
	}
      
      // =====================================================================================================================================
	
      /**
       * 获取HttpClient
       * @return HttpClient HttpClient
       * @throws Exception 
       */
	private static HttpClient getHttpClient() throws Exception
	{
		SSLContext sslcontext = SSLContext.getInstance("TLS");
		X509TrustManager tm = new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
		};
		sslcontext.init(null, new TrustManager[] { tm }, null);
		HttpClient httpClient = HttpClientBuilder.create().setSslcontext(sslcontext).build();
                return httpClient;
	}
	
      /**
       * 执行request请求，返回响应内容
       * @param request HttpRequestBase
       * @param charsetName 编码
       * @return String 响应内容
       */
	public static String executeRequest(HttpRequestBase request, String charsetName)
	{
		String responseText = null;
		try
		{
			HttpResponse response = getHttpClient().execute(request);
			// 获取状态
			int statuscode = response.getStatusLine().getStatusCode();
			// 重定向处理
			if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY)
					|| (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
					|| (statuscode == HttpStatus.SC_SEE_OTHER)
					|| (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT))
			{
				Header redirectLocation = response.getFirstHeader("Location");
				String newUrl = redirectLocation.getValue();
				if (StringUtils.isNotBlank(newUrl))
				{
					request.setURI(new URI(newUrl));
					response = getHttpClient().execute(request);
				}
			}
			if (StringUtils.isNotBlank(charsetName))
			{
				responseText = EntityUtils.toString(response.getEntity(), charsetName);
			} else
			{
				responseText = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		} finally
		{
			releaseConnection(request);// 释放连接
		}
		return responseText;
	}
      
      /**
       * 获取请求返回的响应HttpResponse
       * @param request HttpRequestBase
       * @return String 响应内容
       */
	public static String executeRequest(HttpRequestBase request)
	{
		return executeRequest(request, null);
	}
	
	/**
         * 根据Map请求参数获取HTTPEntity
         * @param map Map<String, String>格式的请求参数
         * @param charsetName 编码名称
         * @return HttpEntity HttpEntity
         */
	private static HttpEntity getEntityFromMap(Map<String, String> map, String charsetName)
	{
		// 参数对象
		UrlEncodedFormEntity entity = null;
		// 参数LIST
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		if(null != map)
		{
		        for (Iterator<Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();)
	                {
	                        Entry<String, String> entry = it.next();
	                        paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
	                }
		}
		if (StringUtils.isBlank(charsetName))
                {
                        charsetName = DefaultSystemConst.DEFAULT_UNICODE;
                }
		try
		{
			entity = new UrlEncodedFormEntity(paramList, charsetName);
		} catch (UnsupportedEncodingException e)
		{
			logger.error(e.getMessage(), e);
		}
		return entity;
	}
	
	/**
	 * 释放请求连接
	 * @param request
	 */
	private static void releaseConnection(HttpRequestBase request)
	{
		if (request != null)
		{
			request.releaseConnection();
		}
	}
	
	/**
	 * 释放HttpClient连接
	 * @param hrb 		请求对象
	 * @param httpclient 	DefaultHttpClient，client对象
	 */
	private static void abortConnection(final HttpRequestBase hrb, final HttpClient httpclient)
	{
		if (hrb != null)
		{
			hrb.abort();
		}
		if (httpclient != null)
		{
			httpclient.getConnectionManager().shutdown();
		}
	}
}
