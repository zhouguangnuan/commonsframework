package cn.singno.commonsframework.module.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Role_Permission")
public class Role_Permission {
	
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Id
	@Column(name = "refrenceId", unique = true, nullable = false, length = 32)
	private String refrenceId;
	
	@Column(name = "roleId", nullable = false, length = 32)
	public String roleId;
	
	@Column(name = "permissionId", nullable = false, length = 32)
	public String permissionId;

	public String getRefrenceId()
	{
		return refrenceId;
	}

	public void setRefrenceId(String refrenceId)
	{
		this.refrenceId = refrenceId;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public String getPermissionId()
	{
		return permissionId;
	}

	public void setPermissionId(String permissionId)
	{
		this.permissionId = permissionId;
	}
}
