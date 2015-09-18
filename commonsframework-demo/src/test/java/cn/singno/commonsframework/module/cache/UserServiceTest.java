package cn.singno.commonsframework.module.cache;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import cn.singno.commonsframework.constants.SexEnum;
import cn.singno.commonsframework.generic.GenericControllerTest;
import cn.singno.commonsframework.module.app.entity.User;
import cn.singno.commonsframework.module.app.service.impl.UserServiceImpl2;

import com.alibaba.fastjson.JSON;

public class UserServiceTest extends GenericControllerTest{
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserServiceTest.class);
	
	@Autowired
	private UserServiceImpl2 userService;
	
	@Autowired
    private CacheManager cacheManager;

    private Cache userCache;

    @Before
    public void setUp() {
        userCache = cacheManager.getCache("user");
    }
    
    
    /**
     * 描述：缓存规则<br>
     * <pre>
     * value = 实体名称（类似缓存组的概念）
     * 
     * 新增/修改：
     * 1. 清空缓存组
     * （可选操作 2. 新增缓存（所有查询单个实体的方法的缓存Key））
     * 
     * 删除：
     * 1. 清空缓存组
     * 
     * 查询单个实体：
     * 1. 新增缓存（拼接参数为key）
     * （如果新增/修改操作，没有新增缓存的话，则key值可以不设置，使调用MyKeyGenerator生成key）
     * 
     * 查询列表或分页：
     * 1. 新增缓存（key值不设置，使调用MyKeyGenerator生成key）
     * </pre>
     */
    
	@Test
	@Rollback(true)
	public void test1() throws Exception
	{
		User user = new User();
		user.setName("singno");
		user.setAge(19);
		user.setPassword("123456");
		user.setrPassword("123456");
		user.setSex(SexEnum.MAN);
		user.setName("admin");
		
		User user1 = userService.save(user);
		logger.debug(JSON.toJSONString(user1));
		
		User user2 = userService.findOne(user.getRefrenceId());
		logger.debug(JSON.toJSONString(user2));
		user2 = userService.findOne(user.getRefrenceId());
		logger.debug(JSON.toJSONString(user2));
		
		User user3 = userService.findByName(user.getName());
		logger.debug(JSON.toJSONString(user3));
		user3 = userService.findByName(user.getName());
		logger.debug(JSON.toJSONString(user3));
		
		User user4 = userService.findByIdAndName(user.getRefrenceId(), user.getName());
		logger.debug(JSON.toJSONString(user4));
		user4 = userService.findByIdAndName(user.getRefrenceId(), user.getName());
		logger.debug(JSON.toJSONString(user4));
	}
	
	@Test
	@Rollback(true)
	public void test2() throws Exception
	{
		Pageable param1 = new PageRequest(1, 10);
		
		Page<User> page = userService.findAll(param1);
		logger.debug(JSON.toJSONString(page));
		
		userService.deleteAll();
		
		Pageable param2 = new PageRequest(1, 10);
		Page<User> page2 = userService.findAll(param2);
		logger.debug(JSON.toJSONString(page2));
	}
	
	@Test
	public void test3() throws Exception
	{
		User user = new User();
		user.setName("singno");
		user.setAge(19);
		user.setPassword("123456");
		user.setrPassword("123456");
		user.setSex(SexEnum.MAN);
		user.setName("admin");
		
		
		User user2 = userService.findOne(user.getRefrenceId());
		logger.debug(JSON.toJSONString(user2));
		
		User user1 = userService.save(user);
		logger.debug(JSON.toJSONString(user1));
		
		user2 = userService.findOne(user.getRefrenceId());
		logger.debug(JSON.toJSONString(user2));
	}
	
	/**
	 * 描述：<br>
	 * <pre>service层方法嵌套调用时，cache只会操作最外层的service方法。（只读取最外层service方法的缓存值，只缓存最外层service方法的返回值）</pre>
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception
	{
		User user = userService.update("402881854b64e6af014b64e6bd1b0000");
		logger.debug(JSON.toJSONString(user));
	}
}
