package cn.singno.commonsframework.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import cn.singno.commonsframework.dao.CouresMapper;
import cn.singno.commonsframework.entity.Coures;
import cn.singno.commonsframework.generic.GenericTest;

public class CouresMapperTest extends GenericTest
{
	@Autowired 
	private CouresMapper couresMapper;
	
	@Test
	public void insertList() throws Exception
	{
		List<Coures> list = Lists.newArrayList();
		list.add(new Coures("java", Boolean.TRUE, 10, 1));
		list.add(new Coures("android", Boolean.TRUE, 10, 2));
		list.add(new Coures("ios", Boolean.TRUE, 10, 3));
		
		couresMapper.insertList(list);
	}
	
	@Test
	public void testname() throws Exception
	{
		Integer teacherId = 1;
		List<Coures> list = couresMapper.selectByTeacherId(teacherId);
		logger.debug(JSON.toJSONString(list));
	}
}
