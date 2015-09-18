package cn.singno.commonsframework.module.controller;

import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.ResultActions;

import cn.singno.commonsframework.generic.GenericControllerTest;

import com.google.common.collect.Maps;

public class UserControllerTest extends GenericControllerTest
{

	@Test
	public void testAddUser() throws Exception
	{
		Map<String, Object> params = Maps.newHashMap();
		ResultActions resultActions = super.getResultActions("/users", params, HttpMethod.POST);
		logger.debug(resultActions.andReturn().getResponse().getContentAsString());
	}
	
}
