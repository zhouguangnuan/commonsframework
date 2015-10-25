package cn.singno.commonsframework.entity;

import java.util.Date;

public class Student
{
	private Integer id;

	private String name;

	private Short sex;

	private Date birthday;

	private Integer classId;

	private Boolean isDelete;

	public Student()
	{
		super();
	}

	public Student(String name, Integer classId)
	{
		super();
		this.name = name;
		this.classId = classId;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name == null ? null : name.trim();
	}

	public Short getSex()
	{
		return sex;
	}

	public void setSex(Short sex)
	{
		this.sex = sex;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public Integer getClassId()
	{
		return classId;
	}

	public void setClassId(Integer classId)
	{
		this.classId = classId;
	}

	public Boolean getIsDelete()
	{
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete)
	{
		this.isDelete = isDelete;
	}
}