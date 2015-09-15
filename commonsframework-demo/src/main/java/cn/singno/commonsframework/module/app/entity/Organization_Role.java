package cn.singno.commonsframework.module.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Organization_Role")
public class Organization_Role {

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Id
	@Column(name = "refrenceId", unique = true, nullable = false, length = 32)
	private String refrenceId;
	
	@Column(name = "organizationId", nullable = false, length = 32)
	private String organizationId;
	
	@Column(name = "roleId", nullable = false, length = 32)
	private String roleId;

	public String getRefrenceId()
	{
		return refrenceId;
	}

	public void setRefrenceId(String refrenceId)
	{
		this.refrenceId = refrenceId;
	}

	public String getOrganizationId()
	{
		return organizationId;
	}

	public void setOrganizationId(String organizationId)
	{
		this.organizationId = organizationId;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}
}
