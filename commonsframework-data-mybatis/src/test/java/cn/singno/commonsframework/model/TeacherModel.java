package cn.singno.commonsframework.model;

import java.util.List;

import cn.singno.commonsframework.entity.Coures;
import cn.singno.commonsframework.entity.Teacher;
import cn.singno.commonsframework.entity.Class;

/**
 * 教师数据模型
 */
public class TeacherModel
{
	private Teacher teacher;// 教师
	
	private List<Coures> couresList;// 所授课程列表
	
	private Class proxyClass;// 代理班级（某班班主任）

	public Teacher getTeacher()
	{
		return teacher;
	}

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}

	public List<Coures> getCouresList()
	{
		return couresList;
	}

	public void setCouresList(List<Coures> couresList)
	{
		this.couresList = couresList;
	}

	public Class getProxyClass()
	{
		return proxyClass;
	}

	public void setProxyClass(Class proxyClass)
	{
		this.proxyClass = proxyClass;
	}
}
