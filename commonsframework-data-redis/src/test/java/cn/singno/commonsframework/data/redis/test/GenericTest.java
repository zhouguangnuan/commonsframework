package cn.singno.commonsframework.data.redis.test;

import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.singno.commonsframework.data.redis.demo.Order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy(@ContextConfiguration(locations = {"classpath:config/spring-config-redis.xml"}))
public class GenericTest
{
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = Logger.getLogger(GenericTest.class);
	
	@Autowired
	protected RedisTemplate redisTemplate;
	
	@Autowired
	protected StringRedisTemplate stringRedisTemplate;
	
	protected Order order1;
	protected Order order2;
	protected Order order3;
	protected Order order4;
	protected String user1 = "user1:id";
	protected String user2 = "user2:id";
	protected String orderNum = "orderNum";
	protected String totalPric = "totalPric";
	protected String orderNO = "orderNO";
	
	private ObjectMapper objectMapper;
	
	@Before
	public void before() throws Exception 
	{
		order1 = new Order();
		order1.setId("10000");
		order1.setOrderNo("AO001");
		order1.setPrice(100.1);
		order1.setCreateDate(new Date());
		
		order2 = new Order();
		order2.setId("20000");
		order2.setOrderNo("AO002");
		order2.setPrice(200.2);
		order2.setCreateDate(new Date());
		
		order3 = new Order();
		order3.setId("30000");
		order3.setOrderNo("AO003");
		order3.setPrice(300.3);
		order3.setCreateDate(new Date());
		
		order4 = new Order();
		order4.setId("40000");
		order4.setOrderNo("AO004");
		order4.setPrice(400.4);
		order4.setCreateDate(new Date());

		objectMapper = new ObjectMapper();
	}
	
	@After
	public void after() throws Exception
	{
		redisTemplate.opsForValue().getOperations().delete(Arrays.asList(new String[]{order1.getId(), order2.getId(), order3.getId(), order4.getId(), user1, user2, orderNum, totalPric, orderNO}));
		stringRedisTemplate.opsForValue().getOperations().delete(Arrays.asList(new String[]{order1.getId(), order2.getId(), order3.getId(), order4.getId(), user1, user2, orderNum, totalPric, orderNO}));
	}
	
	public String toJsonStr(Object obj) throws JsonProcessingException
	{
		if(null == obj){
			return "{}";
		}
		
		return objectMapper.writeValueAsString(obj);
	}
}
