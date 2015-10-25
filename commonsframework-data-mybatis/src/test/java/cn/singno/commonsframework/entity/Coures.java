package cn.singno.commonsframework.entity;

/**
 * 课程
 */
public class Coures
{
	private Integer id;

	private String name;

	private Boolean required;// 是否必修

	private Integer credit;// 学分

	private Integer teacherId;
	
	public Coures()
	{
		super();
	}
	
	public Coures(String name, Boolean required, Integer credit,
			Integer teacherId)
	{
		super();
		this.name = name;
		this.required = required;
		this.credit = credit;
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

	public Boolean getRequired()
	{
		return required;
	}

	public void setRequired(Boolean required)
	{
		this.required = required;
	}

	public Integer getCredit()
	{
		return credit;
	}

	public void setCredit(Integer credit)
	{
		this.credit = credit;
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