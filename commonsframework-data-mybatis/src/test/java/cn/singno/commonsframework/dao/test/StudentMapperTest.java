package cn.singno.commonsframework.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.singno.commonsframework.dao.StudentMapper;
import cn.singno.commonsframework.entity.Student;
import cn.singno.commonsframework.generic.GenericTest;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class StudentMapperTest extends GenericTest
{
	@Autowired 
	private StudentMapper studentMapper;
	
	@Test
	public void insert() throws Exception
	{
		Student record = new Student();
		record.setName("singno");
		record.setIsDelete(Boolean.FALSE);
		record.setBirthday(new Date());
		record.setClassId(1);
		record.setSex((short)1);
		int num = studentMapper.insert(record);
		logger.debug(1==num ? "成功" : "失败");
		logger.debug("studentId：" + record.getId());
	}
	
	@Test
	public void selectByName() throws Exception
	{
		String name = "sin";
		List<Student> list = studentMapper.selectByName(name);
		logger.debug(JSON.toJSONString(list));
	}
	
	@Test
	public void insertList() throws Exception
	{
		List<Student> list = Lists.newArrayList();
		for (int i = 0; i < 50; i++)
		{
			Integer classId = i%3+1;
			list.add(new Student("菜鸟"+i, classId));
		}
		studentMapper.insertList(list);
	}
}
