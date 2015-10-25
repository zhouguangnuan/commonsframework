package cn.singno.commonsframework.entity;

/**
 * 班级
 */
public class Class
{
	private Integer id;

	private String name;

	private Short year;// 年级

	private Integer teacherId;// 班主任id

	public Class()
	{
		super();
	}
	
	public Class(String name, Short year, Integer teacherId)
	{
		super();
		this.name = name;
		this.year = year;
		this.teacherId = teacherId;
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

	public Short getYear()
	{
		return year;
	}

	public void setYear(Short year)
	{
		this.year = year;
	}

	public Integer getTeacherId()
	{
		return teacherId;
	}

	public void setTeacherId(Integer teacherId)
	{
		this.teacherId = teacherId;
	}
}