package cn.singno.commonsframework.module.app.service.impl;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import cn.singno.commonsframework.constants.SexEnum;
import cn.singno.commonsframework.module.app.entity.TestEntity;
import cn.singno.commonsframework.module.app.service.TestEntityService;

@Service
public class TestEntityServiceImpl implements TestEntityService
{
	public TestEntity find()
	{
		TestEntity user = this.createUser();
		return user;
	};
	
	// ===========================================================================
	
	private TestEntity createUser() {
		String name = RandomStringUtils.random(3, "周光暖李林蒋妮娜");
		int age = RandomUtils.nextInt(100);
		TestEntity user = new TestEntity();
		user.setName(name);
		user.setAge(age);
		user.setSex(SexEnum.WOMAN);
		user.setPassword("123");
		user.setRefrenceId("123");
		return user;
	}

}
