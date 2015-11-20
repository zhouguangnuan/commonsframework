package cn.singno.commonsframework.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.singno.commonsframework.dao.ClassMapper;
import cn.singno.commonsframework.entity.Class;
import cn.singno.commonsframework.generic.GenericTest;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class ClassMapperTest extends GenericTest
{
	@Autowired 
	private ClassMapper classMapper;
	
	@Test
	public void insert() throws Exception
	{
		cn.singno.commonsframework.entity.Class record = new cn.singno.commonsframework.entity.Class();
		record.setName("周光暖");
		record.setTeacherId(2);
		record.setYear((short)1);
		int num = classMapper.insert(record);
		logger.debug(1==num ? "成功" : "失败");
		logger.debug("classId：" + record.getId());
	}
	
	@Test
	public void selectByName() throws Exception
	{
		// 必须为双眼号
		//  select * from class where name like "%"#{name}"%"
		
		String name = "sin";
		List<Class> list = classMapper.selectByName(name);
		logger.debug(JSON.toJSONString(list));
	}
	
	@Test
	public void insertList() throws Exception
	{
		List<Class> list = Lists.newArrayList();
		list.add(new Class("计算机1班", (short)1, 1));
		list.add(new Class("计算机2班", (short)2, 2));
		list.add(new Class("计算机3班", (short)3, 1));
		
		classMapper.insertList(list);
	}
}
