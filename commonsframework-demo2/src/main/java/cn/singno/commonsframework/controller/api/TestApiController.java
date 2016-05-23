package cn.singno.commonsframework.controller.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * 通用接口
 */
@ApiIgnore
@RestController
@RequestMapping("/api")
public class TestApiController
{
	@RequestMapping(value="/test1" , method=RequestMethod.PUT)
	public Object putTest1(HttpServletRequest request, String productId, Integer count){
		System.out.println(JSON.toJSONString(request.getParameterMap()));
		
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Object> data = Maps.newHashMap();
		Map<String, Object> user = Maps.newHashMap();
		map.put("code", 0);
		map.put("message", "SUCCESS");
		
		user.put("name", "singno");
		user.put("age", 18);
		data.put("user", user);
		map.put("data", data);
		return map;
	}
	
	@RequestMapping(value="/test1" , method=RequestMethod.DELETE)
	public Object deleteTest1(HttpServletRequest request, String productId, Integer count){
		System.out.println(JSON.toJSONString(request.getParameterMap()));
		
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Object> data = Maps.newHashMap();
		Map<String, Object> user = Maps.newHashMap();
		map.put("code", 0);
		map.put("message", "SUCCESS");
		
		user.put("name", "singno");
		user.put("age", 18);
		data.put("user", user);
		map.put("data", data);
		return map;
	}
	
	@RequestMapping(value="/test1" , method=RequestMethod.POST)
	public Object postTest1(HttpServletRequest request, String productId, Integer count){
		System.out.println(JSON.toJSONString(request.getParameterMap()));
		
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Object> data = Maps.newHashMap();
		Map<String, Object> user = Maps.newHashMap();
		map.put("code", 0);
		map.put("message", "SUCCESS");
		
		user.put("name", "singno");
		user.put("age", 18);
		data.put("user", user);
		map.put("data", data);
		return map;
	}
	
	@RequestMapping(value="/test1" , method=RequestMethod.GET)
	public Object getTest1(HttpServletRequest request, String productId, Integer count){
		System.out.println(JSON.toJSONString(request.getParameterMap()));
		
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Object> data = Maps.newHashMap();
		Map<String, Object> user = Maps.newHashMap();
		map.put("code", 0);
		map.put("message", "SUCCESS");
		
		user.put("name", "singno");
		user.put("age", 18);
		data.put("user", user);
		map.put("data", data);
		return map;
	}
}
