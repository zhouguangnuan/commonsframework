package cn.singno.commonsframework.model;

import java.util.List;

import cn.singno.commonsframework.entity.Student;
import cn.singno.commonsframework.entity.Teacher;

/**
 * 班级数据模型
 */
public class ClassModel
{
	private Teacher teacher;// 班主任
	
	private List<Student> studentList;// 班级学生列表

	public Teacher getTeacher()
	{
		return teacher;
	}

	public List<Student> getStudentList()
	{
		return studentList;
	}
}
