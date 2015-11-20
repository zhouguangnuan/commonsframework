package cn.singno.commonsframework.model;

import java.util.List;

import cn.singno.commonsframework.entity.Coures;
import cn.singno.commonsframework.entity.Student;
import cn.singno.commonsframework.entity.Teacher;
import cn.singno.commonsframework.entity.Class;

/**
 * 学生数据模型
 */
public class StudentModel
{
	private Student student;// 学生
	
	private Teacher teacher;// 班主任
	
	private Class resideClass;// 所在班级
	
	private List<Coures> couresList;// 所修课程列表
	
	public Student getStudent()
	{
		return student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	public Class getResideClass()
	{
		return resideClass;
	}

	public void setResideClass(Class resideClass)
	{
		this.resideClass = resideClass;
	}

	public List<Coures> getCouresList()
	{
		return couresList;
	}

	public void setCouresList(List<Coures> couresList)
	{
		this.couresList = couresList;
	}

	public Teacher getTeacher()
	{
		return teacher;
	}

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}
}
