package cn.singno.commonsframework.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.singno.commonsframework.dao.TeacherMapper;
import cn.singno.commonsframework.entity.Teacher;
import cn.singno.commonsframework.generic.GenericTest;
import cn.singno.commonsframework.model.TeacherModel;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class TeacherMapperTest extends GenericTest
{
	@Autowired
	private TeacherMapper teacherMapper;

	/**
	 * 批量新增
	 * @throws Exception
	 */
	@Test
	public void insertList() throws Exception
	{
		List<Teacher> list = Lists.newArrayList();
		
		list.add(new Teacher("周光暖", "教授"));
		list.add(new Teacher("鲍贱民", "高级教师"));
		list.add(new Teacher("蒋妮娜", "普通教师"));
		
		teacherMapper.insertList(list);
	}
	
	/**
	 * 批量删除	
	 * @throws Exception
	 */
	@Test
	public void deleteList() throws Exception
	{
		List<Teacher> list = teacherMapper.selectAll();
		teacherMapper.deleteList(list);
	}
	
	/**
	 * 批量修改
	 * @throws Exception
	 */
	@Test
	public void updateList() throws Exception
	{
		// 需要在连接属性后加 allowMultiQueries=true
		// 如：jdbc:mysql://localhost:3306/spring-data-mybatis-write?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true
		
		List<Teacher> list = Lists.newArrayList();
		
		list.add(new Teacher(10, "周光暖2", "教授3"));
		list.add(new Teacher(11, "鲍贱民2", "高级教师3"));
		list.add(new Teacher(12, "蒋妮娜2", "普通教师3"));
		
		teacherMapper.updateList(list);
	}
	
	
	// ================================ 高级查询 ================================ 
	
	@Test
	public void selectAll() throws Exception
	{
		List<Teacher> list = teacherMapper.selectAll();
		logger.debug(JSON.toJSONString(list));
	}
	
	@Test
	public void selectModelByPrimaryKey() throws Exception
	{ 
		Integer teacherId = 1;
		TeacherModel teacherModel = teacherMapper.selectModelByPrimaryKey(teacherId);
		logger.debug(JSON.toJSONString(teacherModel));
	}
}
