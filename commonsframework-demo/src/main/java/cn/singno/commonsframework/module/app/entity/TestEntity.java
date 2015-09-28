package cn.singno.commonsframework.module.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.ScriptAssert;

import cn.singno.commonsframework.bind.annotation.EnumConst;
import cn.singno.commonsframework.constants.SexEnum;

@Entity
@Table(name = "TestEntity")
public class TestEntity implements Serializable
{
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Id
	@Column(name = "refrenceId", unique = true, nullable = false, length = 32)
	private String refrenceId;

	@NotBlank(message = "{user.name.null}")
	@Length(min = 0, max = 50, message = "{user.name.length.illegal}")
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@NotNull(message = "{user.age.null}")
	@Min(value = 0, message = "{user.age.illegal}")
	@Column(name = "age", nullable = false, length = 3)
	private Integer age;

	@EnumConst
	@NotNull(message = "{user.sex.null}")
	@Enumerated(EnumType.STRING)
	@Column(name = "sex", nullable = false)
	private SexEnum sex;

	@NotBlank(message = "{user.password.null}")
	@Length(min = 6, message = "{user.password.length.illegal}")
	@Column(name = "password", nullable = false, length = 32)
	private String password;

	@NotBlank(message = "{user.organizationId.null}")
	@Column(name = "organizationId", nullable = false, length = 32)
	private String organizationId;
	
	@Column(name = "salt", nullable = false, length = 32)
	private String salt;
	
	@Column(name = "locked")
	private boolean locked = false;
	
	@Transient
	private String rPassword;// 密码重复

	public TestEntity()
	{
		super();
	}

	public TestEntity(String refrenceId, String name, Integer age)
	{
		super();
		this.refrenceId = refrenceId;
		this.name = name;
		this.age = age;
	}
	
	public TestEntity(String refrenceId, String name, Integer age, SexEnum sex, String password)
	{
		super();
		this.refrenceId = refrenceId;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.password = password;
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

	public String getOrganizationId()
	{
		return organizationId;
	}

	public void setOrganizationId(String organizationId)
	{
		this.organizationId = organizationId;
	}

	public String getSalt()
	{
		return salt;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}

	public boolean isLocked()
	{
		return locked;
	}

	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

	@Transient
	public String getrPassword()
	{
		return rPassword;
	}

	public void setrPassword(String rPassword)
	{
		this.rPassword = rPassword;
	}

	@Transient
	public String getCredentialsSalt()
	{
		return this.name + this.salt;
	}
}
