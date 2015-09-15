package cn.singno.commonsframework.module.app.service.impl;

import java.util.Set;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.Sets;

import cn.singno.commonsframework.module.app.dao.UserDao;
import cn.singno.commonsframework.module.app.entity.User;
import cn.singno.commonsframework.module.app.service.UserService;
import cn.singno.commonsframework.security.bean.PasswordHelper;

@Validated
@Service
public class UserServiceImpl implements UserService
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private PasswordHelper passwordHelper;

	@Autowired
	private UserDao userDao;
	
	public User save(User user)
	{
		return user;
	};
	
	public User update(@NotBlank(message="用户名不能为空") String refrenceId)
	{
		return null;
	}
	
	public Set<String> findRoles(String userName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> findPermissions(String userName)
	{
		// TODO Auto-generated method stub
		return Sets.newHashSet();
	}

	public User findByUserName(String userName)
	{
		return userDao.findByName(userName);
	}

}
