package cn.singno.commonsframework.dao.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.singno.commonsframework.bean.Page;
import cn.singno.commonsframework.dao.StudentMapper;
import cn.singno.commonsframework.entity.Student;
import cn.singno.commonsframework.generic.GenericTest;
import cn.singno.commonsframework.model.SearchModel;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
		record.setSex((short) 1);
		int num = studentMapper.insert(record);
		logger.debug(1 == num ? "成功" : "失败");
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
			Integer classId = i % 3 + 1;
			list.add(new Student("菜鸟" + i, classId));
		}
		studentMapper.insertList(list);
	}

	@Test
	public void selectByPage() throws Exception
	{
		Page<Student> page = new Page<Student>(0, 10);
		Map<String, Object> filter = Maps.newHashMap();
		filter.put("name", "菜鸟");
		filter.put("classId", 2);

		List<Student> content = studentMapper.selectByPage(page, filter);
		page.setContent(content);
		logger.debug(JSON.toJSONString(page));
	}

	@Test
	public void selectByPage2() throws Exception
	{
		Page<Student> page = new Page<Student>(0, 10);

		List<Student> content = studentMapper.selectByPage2(page);
		page.setContent(content);
		logger.debug(JSON.toJSONString(page));
	}

	@Test
	public void selectByPage3() throws Exception
	{
		SearchModel searchModel = new SearchModel();
		Page<Student> page = new Page<Student>(0, 10);
		searchModel.setPage(page);
		searchModel.setName("菜鸟");
		searchModel.setClassId(2);

		List<Student> content = studentMapper.selectByPage3(searchModel);
		page.setContent(content);
		logger.debug(JSON.toJSONString(page));
	}

	@Test
	public void selectByP() throws Exception
	{
		Page<Student> page = new Page<Student>(0, 10);

		List<Student> content = studentMapper.selectByP(page);
		logger.debug(JSON.toJSONString(content.size()));
	}
}
