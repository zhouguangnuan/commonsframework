package cn.singno.commonsframework.module.controller;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.ResultActions;

import cn.singno.commonsframework.generic.GenericControllerTest;
import cn.singno.commonsframework.module.app.model.TestMap;
import cn.singno.commonsframework.module.app.model.TestModel;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class WebDateBinderControllerTest extends GenericControllerTest
{

	@Test
	public void testAddUser() throws Exception
	{
//		List<TestMap> list = Lists.newArrayList();
//		TestMap map = new TestMap();
//		map.setKey("1");
//		map.setValue("2");
//		list.add(map);
		List<String> list = Lists.newArrayList();
		list.add("11");
		
		Map<String, String> map = Maps.newHashMap();
		map.put("key", "value");
		
		Map<String, Object> params = Maps.newHashMap();
		
		params.put("name", "11111");
		params.put("list", list);
		params.put("map", map);
		
		ResultActions resultActions = super.getResultActions("/test/map", params, HttpMethod.POST);
		logger.debug(resultActions.andReturn().getResponse().getContentAsString());
	}
}
