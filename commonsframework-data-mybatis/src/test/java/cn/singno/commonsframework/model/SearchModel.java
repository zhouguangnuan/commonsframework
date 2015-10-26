package cn.singno.commonsframework.model;

import cn.singno.commonsframework.bean.Page;
import cn.singno.commonsframework.entity.Student;

public class SearchModel
{
	private Page<Student> page;
	private String name;
	private Integer classId;

	public Page<Student> getPage()
	{
		return page;
	}

	public void setPage(Page<Student> page)
	{
		this.page = page;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getClassId()
	{
		return classId;
	}

	public void setClassId(Integer classId)
	{
		this.classId = classId;
	}

}
