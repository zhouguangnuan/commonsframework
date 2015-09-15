package cn.singno.commonsframework.module.app.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import cn.singno.commonsframework.constants.SexEnum;

@Entity
public class UserModel
{
	@Id
	private String refrenceId;

	private String name;

	private Integer age;

	@Enumerated(EnumType.STRING)
	private SexEnum sex;

	private String password;

	private String rPassword;

	private String roleName;// 角色名称
	
	public UserModel()
	{
		super();
	}

	public String getRefrenceId()
	{
		return refrenceId;
	}

	public void setRefrenceId(String refrenceId)
	{
		this.refrenceId = refrenceId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public SexEnum getSex()
	{
		return sex;
	}

	public void setSex(SexEnum sex)
	{
		this.sex = sex;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getrPassword()
	{
		return rPassword;
	}

	public void setrPassword(String rPassword)
	{
		this.rPassword = rPassword;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
}
