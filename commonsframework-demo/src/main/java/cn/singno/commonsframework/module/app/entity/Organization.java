package cn.singno.commonsframework.module.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Organization")
public class Organization {

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Id
	@Column(name = "refrenceId", unique = true, nullable = false, length = 32)
	private String refrenceId;
	
	@Column(name = "name", unique = true, nullable = false, length = 32)
	public String name;
	
	@Column(name = "parentId", nullable = false, length = 32)
	public String parentId;
	
	@Column(name = "available")
	public boolean available;

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

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable(boolean available)
	{
		this.available = available;
	}
}
