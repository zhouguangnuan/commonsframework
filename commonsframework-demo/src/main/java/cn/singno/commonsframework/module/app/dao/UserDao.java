package cn.singno.commonsframework.module.app.dao;


import org.springframework.data.jpa.repository.Query;

import cn.singno.commonsframework.generic.GenericDao;
import cn.singno.commonsframework.module.app.entity.User;

public interface UserDao extends GenericDao<User, String>
{
	public static final String FIND_BY_NAME = "select o from User o where o.name=?1"; 
	
	@Query(FIND_BY_NAME)
	public User findByName(String userName);
}
