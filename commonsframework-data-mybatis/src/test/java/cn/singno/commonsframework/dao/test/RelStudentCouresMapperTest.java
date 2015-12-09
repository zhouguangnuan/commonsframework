package cn.singno.commonsframework.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.singno.commonsframework.dao.CouresMapper;
import cn.singno.commonsframework.dao.RelStudentCouresMapper;
import cn.singno.commonsframework.dao.StudentMapper;
import cn.singno.commonsframework.entity.Coures;
import cn.singno.commonsframework.entity.RelStudentCoures;
import cn.singno.commonsframework.entity.Student;
import cn.singno.commonsframework.generic.GenericTest;

import com.google.common.collect.Lists;

public class RelStudentCouresMapperTest extends GenericTest
{
	@Autowired 
	private RelStudentCouresMapper relStudentCouresMapper;
	@Autowired 
	private StudentMapper studentMapper;
	@Autowired 
	private CouresMapper couresMapper;
	
	@Test
	public void insertList() throws Exception
	{
		List<RelStudentCoures> list = Lists.newArrayList();
		List<Student> student_list = studentMapper.selectAll();
		List<Coures> coures_list = couresMapper.selectAll();
		for (Student student : student_list)
		{
			for(Coures coures : coures_list)
			{
				list.add(new RelStudentCoures(student.getId(), coures.getId()));
			}
		}
		
		relStudentCouresMapper.insertList(list);
	}
}
