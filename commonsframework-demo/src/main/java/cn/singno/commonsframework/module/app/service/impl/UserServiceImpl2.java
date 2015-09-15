package cn.singno.commonsframework.module.app.service.impl;

import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.singno.commonsframework.constants.SexEnum;
import cn.singno.commonsframework.module.app.dao.UserDao2;
import cn.singno.commonsframework.module.app.entity.User;

import com.google.common.collect.Lists;

@Service
public class UserServiceImpl2
{
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserServiceImpl2.class);

	@Autowired
	private UserDao2 userDao;
	
//	@Caching(
//		evict = {
//	        	@CacheEvict(value = "user", allEntries = true)	
//	        },	
//        put = {
//            @CachePut(value = "user", key = "#user.refrenceId"),
//            @CachePut(value = "user", key = "#user.name"),
//            @CachePut(value = "user", key = "#user.refrenceId + #user.name")
//        }
//    )
	@CacheEvict(value = "user", allEntries = true)	
	public User save(User user)
	{
		System.out.println("执行了save()");
		
//		user = userDao.save(user);
		return user;
	};
	
	@Cacheable(value = "user")
	public User update(@NotBlank(message="用户名不能为空") String refrenceId)
	{
		return this.findOne(refrenceId);
	}
	
	@CacheEvict(value = "user", allEntries = true)
	public void delete(String refrenceId){
		System.out.println("执行了delete()");
		
		userDao.delete(refrenceId);
	};
	
	@CacheEvict(value = "user", allEntries = true)
	public void deleteAll(){
		System.out.println("执行了deleteAll()");
		
		userDao.deleteAll();
	};
	
//	@Cacheable(value = "user", key = "#refrenceId")
	@Cacheable(value = "user")
	public User findOne(String refrenceId){
		System.out.println("执行了findOne()");
		
//		return null;
		
		return userDao.findOne(refrenceId);
	};
	
//	@Cacheable(value = "user", key = "#name")
	@Cacheable(value = "user")
	public User findByName(String name)
	{
		System.out.println("执行了findByName()");
		
		return this.createUser();
		
//		return null;
	}
	
//	@Cacheable(value = "user", key = "#user.refrenceId + #user.name")
	@Cacheable(value = "user")
	public User findByIdAndName(String refrenceId, String name)
	{
		System.out.println("执行了findByIdAndName()");
		
		return this.createUser();
		
//		return null;
	}
	
	@Cacheable(value = "users")
	public Page<User> findAll(Pageable pageable){
		System.out.println("执行了findAll()");
		
		return userDao.findAll(pageable);
	};
	
	
	
	
	
	
	
	
	public List<User> list()
	{
		int size = 10;
		List<User> users = Lists.newArrayList();
		for (int i = 0; i < size; i++) {
			users.add(this.createUser());
		}
		return users;
	}

//	public User findById(@NotBlank String id){
//		User user = this.createUser();
//		user.setId(id);
//		return user;
//	}

//	public @NotNull(message="保存失败") User save(@Valid User user)
//	{
//		if (RandomUtils.nextBoolean()) {
//			return null;
//		}
//		return user;
//	}
	
	// ===========================================================================
	
	private User createUser() {
		String name = RandomStringUtils.random(3, "周光暖李林蒋妮娜");
		int age = RandomUtils.nextInt(100);
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setSex(SexEnum.WOMAN);
		user.setPassword("123");
		user.setRefrenceId("123");
		return user;
	}

}
