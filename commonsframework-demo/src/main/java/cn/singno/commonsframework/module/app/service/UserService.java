package cn.singno.commonsframework.module.app.service;

import java.util.Set;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import cn.singno.commonsframework.module.app.entity.User;

public interface UserService
{
	public User save(@Valid User user);
	
	public User update(@NotBlank(message="用户名不能为空") String refrenceId);

	public User findByUserName(String userName);

	public Set<String> findRoles(String userName);

	public Set<String> findPermissions(String userName);
}