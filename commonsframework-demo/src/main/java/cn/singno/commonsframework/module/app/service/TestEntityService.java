package cn.singno.commonsframework.module.app.service;

import org.springframework.validation.annotation.Validated;

import cn.singno.commonsframework.module.app.entity.TestEntity;

@Validated
public interface TestEntityService
{
	public TestEntity find();

}