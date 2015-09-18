/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.module.app.controller</p>
 * <p>文件名：EsyUiTestController.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-8-12-下午4:18:22</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.module.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.singno.commonsframework.bean.JsonResult;
import cn.singno.commonsframework.constants.DefaultDescribableEnum;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * <p>名称：EsyUiTestController.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-8-12 下午4:18:22
 * @version 1.0.0
 */
@Controller
@RequestMapping("/test/easyui")
public class EasyuiTestController
{
	@RequestMapping(value="/crud/view", method=RequestMethod.GET)
	public String crud_view() throws Exception{ 
		return "easyui/CRUD_3";
	};
	
	@ResponseBody
	@RequestMapping(value="/crud/data", method=RequestMethod.GET)
	public Object crud(Integer page, Integer rows) throws Exception
	{ 
		if(null == page){
			page = 1;
		}
		if(null == rows){
			rows = 10;
		}
		long totalElements = 100L;
		Integer totalPages = (int)(totalElements/rows);
		List<Map<String, String>> list = Lists.newArrayList();
		for (int i = 0; i < rows; i++) {
			Map<String, String> map = Maps.newHashMap();
			map.put("firstname", "fname_"+i+"_"+page+"");
			map.put("lastname", "lname_"+i+"_"+page+"");
			map.put("phone", "15669108193_"+i+"_"+page+"");
			map.put("email", "zhouguangnuan@yeah.net_"+i+"_"+page+"");
			list.add(map); 
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("rows", list);
		map.put("total",totalElements);
		return map;
	};
	
	@ResponseBody
	@RequestMapping(value="/crud/data2", method=RequestMethod.GET)
	public Object crud2(Integer page, Integer size, String param1, String param2) throws Exception
	{ 
		System.out.println("page: " + page);
		System.out.println("size: " + size);
		System.out.println("param1: " + param1);
		System.out.println("param2: " + param2);
		
		if(null == page){
			page = 1;
		}
		if(null == size){
			size = 10;
		}
		long totalElements = 100L;
		Integer totalPages = (int)(totalElements/size);
		List<Map<String, String>> list = Lists.newArrayList();
		for (int i = 0; i < size; i++) {
			Map<String, String> map = Maps.newHashMap();
			map.put("firstname", "fname_"+i+"_"+page+"");
			map.put("lastname", "lname_"+i+"_"+page+"");
			map.put("phone", "15669108193_"+i+"_"+page+"");
			map.put("email", "zhouguangnuan@yeah.net_"+i+"_"+page+"");
			list.add(map); 
		}
		 
		JsonResult jsonResult = new JsonResult(DefaultDescribableEnum.SUCCESS, new PageImpl<Map<String, String>>(list)); 
		return jsonResult;
	};
	
	public static void main(String[] args)
	{
		List<Map<String, String>> list = Lists.newArrayList();
		for (int i = 0; i < 200; i++) {
			Map<String, String> map = Maps.newHashMap();
			map.put("firstname", "fname1");
			map.put("lastname", "lname1");
			map.put("phone", "15669108193");
			map.put("email", "zhouguangnuan@yeah.net");
			list.add(map); 
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("rows", list);
		map.put("total",200);
		
		System.out.println(JSON.toJSONString(map));
	}
	
}
