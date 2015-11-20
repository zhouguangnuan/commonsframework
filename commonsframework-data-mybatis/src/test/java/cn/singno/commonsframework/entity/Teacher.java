package cn.singno.commonsframework.entity;

import java.util.Date;

public class Teacher
{
	private Integer id;

	private String name;

	private Short sex;

	private Date birthday;

	private Date workDate;

	private String professional;

	public Teacher() {}
	
	public Teacher(String name, String professional)
	{
		super();
		this.name = name;
		this.professional = professional;
	}
	
	public Teacher(Integer id, String name, String professional)
	{
		super();
		this.id = id;
		this.name = name;
		this.professional = professional;
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

	public Date getWorkDate()
	{
		return workDate;
	}

	public void setWorkDate(Date workDate)
	{
		this.workDate = workDate;
	}

	public String getProfessional()
	{
		return professional;
	}

	public void setProfessional(String professional)
	{
		this.professional = professional == null ? null : professional
				.trim();
	}
}