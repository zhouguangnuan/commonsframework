package cn.singno.commonsframework.module.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.singno.commonsframework.generic.GenericTest;
import cn.singno.commonsframework.module.app.entity.User;
import cn.singno.commonsframework.module.app.service.UserService;

public class UserServiceTest extends GenericTest
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserServiceTest.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 描述：新增
	 * <pre></pre>
	 */
	@Test
	public void testSave()
	{
		try
		{
			User user = null;//usernew User();
			userService.save(user);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	};
	
}
