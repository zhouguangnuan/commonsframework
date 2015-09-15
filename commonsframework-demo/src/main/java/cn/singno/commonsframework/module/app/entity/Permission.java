package cn.singno.commonsframework.module.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Permission")
public class Permission {
	
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Id
	@Column(name = "refrenceId", unique = true, nullable = false, length = 32)
	private String refrenceId;
	
	@NotNull(message = "{permission.name.null}")
	@Column(name = "name", unique = true, nullable = false, length = 32)
	public String name;
	
	@NotNull(message = "{permission.description.null}")
	@Column(name = "description", nullable = false, length = 32)
	public String description;
	
	@NotNull(message = "{permission.resource.null}")
	@Column(name = "resource", nullable = false, length = 32)
	public String resource;

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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getResource()
	{
		return resource;
	}

	public void setResource(String resource)
	{
		this.resource = resource;
	}
}
