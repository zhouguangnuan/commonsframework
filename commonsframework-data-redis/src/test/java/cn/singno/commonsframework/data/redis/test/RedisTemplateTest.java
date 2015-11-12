package cn.singno.commonsframework.data.redis.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.data.redis.core.ValueOperations;

import cn.singno.commonsframework.data.redis.demo.Order;

@SuppressWarnings("all")
public class RedisTemplateTest extends GenericTest
{
	@Test
	public void ValueOperations_set_get() throws Exception
        {
                ValueOperations<String, Order> valueOper = redisTemplate.opsForValue();
                
                valueOper.set(order1.getId(), order1);
                
                valueOper.set(order2.getId(), order2, 5, TimeUnit.SECONDS);
                
                Boolean bool = valueOper.setIfAbsent(order2.getId(), order2);
                logger.debug(bool);// false
                
                Thread.sleep(6 * 1000);
                logger.debug(toJsonStr(valueOper.get(order1.getId())));
                // {"id":"10000","orderNo":"AO001","price":100.1,"createDate":1447343590690}
                logger.debug(toJsonStr(valueOper.get(order2.getId())));
                // {}
                
                bool = valueOper.setIfAbsent(order2.getId(), order2);
                logger.debug(bool);// true
                logger.debug(toJsonStr(valueOper.get(order2.getId())));
                // {"id":"20000","orderNo":"AO002","price":200.2,"createDate":1447343590690}
                
                logger.debug(toJsonStr(valueOper.get(order3.getId())));// {}
                logger.debug(toJsonStr(valueOper.getAndSet(order3.getId(), order3)));// {}
                logger.debug(toJsonStr(valueOper.get(order3.getId())));
                //  {"id":"30000","orderNo":"AO003","price":300.3,"createDate":1447344465359}
        }
	
	// ?
	@Test
	public void ValueOperations_set_get_2() throws Exception
        {
		ValueOperations<String, Order> valueOper = redisTemplate.opsForValue();
		
		valueOper.set(order2.getId(), order2, 1);
		valueOper.set(order3.getId(), order3, 2);
                logger.debug(valueOper.get(order3.getId(), 0, 2));
        }
	
	@Test
	public void ValueOperations_multiSet_multiGet() throws Exception
        {
                ValueOperations<String, Order> valueOper = redisTemplate.opsForValue();
                
                Map<String, Order> map = new HashMap<String, Order>();
                map.put(order1.getId(), order1);
                map.put(order2.getId(), order2);
                map.put(order3.getId(), order3);
                valueOper.multiSet(map);
                
                logger.debug(valueOper.multiSetIfAbsent(map));// false
                
                List<Order> list = valueOper.multiGet(map.keySet());
                logger.debug(toJsonStr(list));
                // [{"id":"10000","orderNo":"AO001","price":100.1,"createDate":1447344825043},
                //  {"id":"20000","orderNo":"AO002","price":200.2,"createDate":1447344825043},
                //  {"id":"30000","orderNo":"AO003","price":300.3,"createDate":1447344825043}]
        }
	
	@Test
	public void ValueOperations_increment() throws Exception
	{
		logger.debug(redisTemplate.opsForValue().get(orderNum));
		for (int i = 0; i < 10; i++)
                {
			logger.debug(redisTemplate.opsForValue().increment(orderNum, 1));
                }
		logger.debug(redisTemplate.opsForValue().get(orderNum));//  10
		
		logger.debug(redisTemplate.opsForValue().get(totalPric));
		for (int i = 0; i < 10; i++)
                {
			logger.debug(redisTemplate.opsForValue().increment(totalPric, 10.1));
                }
		logger.debug(redisTemplate.opsForValue().get(totalPric));//  100.99999999999999
		
		logger.debug(stringRedisTemplate.opsForValue().get(orderNO));
		for (int i = 1; i < 10; i++)
		{
			logger.debug(stringRedisTemplate.opsForValue().append(orderNO, "NO_" + i + ","));
		}
		logger.debug(stringRedisTemplate.opsForValue().get(orderNO));
		
//		错误
//		ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
//		logger.debug(valueOperations.get(orderNO));
//		for (int i = 1; i < 10; i++)
//                {
//			logger.debug(valueOperations.append(orderNO, "NO_" + i + ","));
//                }
//		logger.debug(valueOperations.get(orderNO));
	}
	
//	Boolean setBit(K key, long offset, boolean value);
//	Boolean getBit(K key, long offset);
//	Long size(K key);
//	RedisOperations<K, V> getOperations();
	
}
