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
@Table(name = "Role")
public class Role
{
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Id
	@Column(name = "refrenceId", unique = true, nullable = false, length = 32)
	private String refrenceId;
	
	@NotNull(message = "{role.name.null}")
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@NotNull(message = "{role.description.null}")
	@Column(name = "description", nullable = false, length = 32)
	private String description;
	
	@Column(name = "available")
	public boolean available = true;

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

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable(boolean available)
	{
		this.available = available;
	}
}
