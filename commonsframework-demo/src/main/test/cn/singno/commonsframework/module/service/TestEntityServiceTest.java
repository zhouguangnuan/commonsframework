package cn.singno.commonsframework.module.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.singno.commonsframework.generic.GenericTest;
import cn.singno.commonsframework.module.app.entity.TestEntity;
import cn.singno.commonsframework.module.app.service.TestEntityService;

import com.alibaba.fastjson.JSON;

public class TestEntityServiceTest extends GenericTest
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestEntityServiceTest.class);
	
	@Autowired
	private TestEntityService testEntityService;
	
	/**
	 * 描述：新增
	 * <pre></pre>
	 */
	@Test
	public void testTestEntity()
	{
		TestEntity testEntity = testEntityService.find();
		System.out.println(JSON.toJSONString(testEntity));
	};
	
}
