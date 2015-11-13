package cn.singno.commonsframework.userDao.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.singno.commonsframework.dao.ClassMapper;
import cn.singno.commonsframework.entity.User;
import cn.singno.commonsframework.generic.GenericTest;
import cn.singno.commonsframework.userDao.UserMapper;

import com.alibaba.fastjson.JSON;

public class UserMapperTest extends GenericTest
{
	@Autowired 
	private UserMapper userMapper;
	
	@Autowired 
	private ClassMapper classMapper;
	
	@Test
	public void selectByPrimaryKey() throws Exception
	{
		User user = userMapper.selectByPrimaryKey(1);
		logger.debug(JSON.toJSONString(user));
		
		cn.singno.commonsframework.entity.Class record = classMapper.selectByPrimaryKey(1);
		logger.debug(JSON.toJSONString(record));
	}
}
