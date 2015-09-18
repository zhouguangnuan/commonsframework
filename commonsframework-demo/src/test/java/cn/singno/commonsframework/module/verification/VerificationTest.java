package cn.singno.commonsframework.module.verification;

import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.ResultActions;

import cn.singno.commonsframework.generic.GenericControllerTest;

import com.google.common.collect.Maps;

public class VerificationTest extends GenericControllerTest
{
	@Test
	public void testVerification2() throws Exception
	{
		Map<String, Object> params = Maps.newHashMap();
		ResultActions resultActions = super.getResultActions("/test/2", params, HttpMethod.POST);
		logger.debug(resultActions.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void testVerification3() throws Exception
	{
		Map<String, Object> params = Maps.newHashMap();
		ResultActions resultActions = super.getResultActions("/test/3", params, HttpMethod.POST);
		logger.debug(resultActions.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void testname() throws Exception
	{
		
	}
}
