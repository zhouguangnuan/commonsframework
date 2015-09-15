package cn.singno.commonsframework.module.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.singno.commonsframework.bean.JsonResult;
import cn.singno.commonsframework.constants.DefaultDescribableEnum;
import cn.singno.commonsframework.utils.CookieUtils;
import cn.singno.commonsframework.utils.HttpUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/test")
public class JsonpTestControll
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JsonpTestControll.class);
	
	@RequestMapping("/jsonpMathod_data")
	@ResponseBody
	public Object jsonpMathod_data(String src1, String src2) throws Exception 
	{
		logger.debug("===================================== 执行【jsonpMathod_data】  =======================================");
		
		JsonResult jsonResult = new JsonResult(DefaultDescribableEnum.SUCCESS);
		Map map = Maps.newHashMap(); 
		map.put("src1", src1);
		map.put("src2", src2);
		jsonResult.setObject(map);
		return jsonResult; 
	}
	
//	@RequestMapping(value="/jsonp", method=RequestMethod.POST)
//	public String test(HttpServletRequest request, HttpServletResponse response, MultipartFile testFile) throws Exception{ 
//		
//		SAXReader reader = new SAXReader(); 
////		InputStream in = request.getInputStream();
//		InputStream in = testFile.getInputStream();
//		Document dom = reader.read(in);
//		System.out.println(JSON.toJSONString(dom));
//		
//		return null;
//	};
	
	@RequestMapping("/jsonpMathod1")
	public Object jsonpMathod1(HttpServletRequest request, HttpServletResponse response, String url, Map<String, String> map) throws Exception 
	{
		logger.debug("===================================== 执行【jsonpMathod1】  =======================================");
		
		logger.debug(JSON.toJSONString(map));
		String jsonStr = HttpUtils.post(url, map); 
//		response.addHeader("Content-Type", "application/x-javascript");
		response.getWriter().write("handler("+jsonStr+")"); 
		return null; 
	}
	
	@RequestMapping("/jsonpMathod2") 
	public Object jsonpMathod2(HttpServletRequest request, HttpServletResponse response, String url) throws Exception 
	{
		logger.debug("===================================== 执行【jsonpMathod2】  =======================================");
		
		Map<String, String[]> params = request.getParameterMap();
		List<NameValuePair> parameters = Lists.newArrayList();
		for (String key  : params.keySet())
		{
			String[] array_param = params.get(key);
			if(ArrayUtils.isNotEmpty(array_param))
			{
				for (String param : array_param)
				{
					parameters.add(new BasicNameValuePair(key, param));
				}
			}
		} 
		
		HttpEntity entity = EntityBuilder.create().setParameters(parameters).build();
		String jsonStr = HttpUtils.post(url, entity); 
		response.addHeader("Content-Type", "application/x-javascript");
		response.getWriter().write("handler("+jsonStr+")");
		return null; 
	} 
	
	@RequestMapping(value="/jsonpMathod5", method=RequestMethod.POST)
	public Object jsonpMathod5_POST(HttpServletRequest request, HttpServletResponse response, String url) throws Exception 
	{
		logger.debug("===================================== 执行【jsonpMathod5_POST】  =======================================");
		
		Map<String, String[]> params = request.getParameterMap();
		List<NameValuePair> parameters = Lists.newArrayList();
		for (String key  : params.keySet())
		{
			String[] array_param = params.get(key);
			if(ArrayUtils.isNotEmpty(array_param))
			{
				for (String param : array_param)
				{
					parameters.add(new BasicNameValuePair(key, param));
				}
			}
		} 
		
		HttpEntity entity = EntityBuilder.create().setParameters(parameters).build();
		String jsonStr = HttpUtils.post(url, entity); 
		logger.debug(jsonStr);
		response.addHeader("Content-Type", "application/x-javascript");
		response.getWriter().write("handler("+jsonStr+")");
		return null; 
	}  
	  
	@RequestMapping(value="/jsonpMathod5", method=RequestMethod.GET)
	public Object jsonpMathod5_GET(HttpServletRequest request, HttpServletResponse response, String url) throws Exception 
	{
		logger.debug("===================================== 执行【jsonpMathod5_GET】  =======================================");
		logger.debug(request.getParameter("token"));
		logger.debug(JSON.toJSONString(request.getCookies()));  
		
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";
		StringBuilder queryParams = new StringBuilder();
		for (String key  : params.keySet())
		{
			String[] array_param = params.get(key); 
			if(ArrayUtils.isNotEmpty(array_param))
			{
				for (String param : array_param)
				{
					queryParams.append(key + "=" + param).append("&");
				}
			}
		} 
		if (StringUtils.isNotBlank(queryParams))
		{
			queryString = "?" + queryParams.substring(0, queryParams.length()-1);
		}
		logger.debug(queryString);
		String jsonStr = HttpUtils.get(url + queryString); 
		logger.debug(jsonStr);
		
		response.addHeader("Content-Type", "application/x-javascript");
		response.getWriter().write(request.getParameter("callback") + "("+jsonStr+")");
		Cookie cookie = new Cookie("test1", "test222");
		response.addCookie(cookie);  
		return null; 
	}
}
