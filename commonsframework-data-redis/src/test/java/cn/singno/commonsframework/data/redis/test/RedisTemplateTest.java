package cn.singno.commonsframework.data.redis.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import cn.singno.commonsframework.data.redis.demo.Order;

@SuppressWarnings("all")
public class RedisTemplateTest extends GenericTest
{
        // =============================== ValueOperations ================================
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
	
	@Test
        public void ValueOperations_setBit_getBit() throws Exception
        {
                
        }
	
	// =============================== ListOperations ================================
	@Test
        public void ListOperations_set_index() throws Exception
        {
	        ListOperations<String, Order> listOperations = redisTemplate.opsForList();
	        
	        logger.debug(listOperations.leftPush(user1, order1));
	        logger.debug(listOperations.leftPush(user1, order2));
	        
	        // 如果 key 不存在会抛异常，需先确认key存在
//	        listOperations.set(user1, 0, order1);
	        Order order = listOperations.index(user1, 0);
	        logger.debug(toJsonStr(order));
	        logger.debug(listOperations.size(user1));
        }
	
	@Test
        public void ListOperations_leftPush_leftPushAll() throws Exception
        {
                ListOperations<String, Order> listOperations = redisTemplate.opsForList();
                
                logger.debug(listOperations.leftPush(user1, order1));
                logger.debug(listOperations.leftPushAll(user1, order2, order3));
                logger.debug(listOperations.leftPushAll(user1, Arrays.asList(order1, order2, order3)));
                
                // 从左边开始寻找order1，插到目标的左边
                logger.debug(listOperations.leftPush(user1, order1, order4));
                
        //      Long remove(K key, long i, Object value);
                List<Order> list = listOperations.range(user1, 0, 0);
                logger.debug(toJsonStr(list));
                //  [{"id":"30000","orderNo":"AO003","price":300.3,"createDate":1447384630237}]
                list = listOperations.range(user1, 0, 1);
                logger.debug(toJsonStr(list));
                // [{"id":"30000","orderNo":"AO003","price":300.3,"createDate":1447384655450},{"id":"20000","orderNo":"AO002","price":200.2,"createDate":1447384655450}]

                // 同 range 截取范围，同时清空其余值
                // listOperations.trim(user1, 0, -1); // 无效
//                listOperations.trim(user1, 0, 3);
                logger.debug(toJsonStr(listOperations.range(user1, 0, -1)));
                
                // 从左边开始查找 order1，移除 1 个 order1
                logger.debug(listOperations.remove(user1, 1, order1));
                logger.debug(toJsonStr(listOperations.range(user1, 0, -1)));
        }
	
	@Test
        public void ListOperations_rightPop() throws Exception
        {
	        ListOperations<String, Order> listOperations = redisTemplate.opsForList();
	        logger.debug(listOperations.leftPushAll(user1, Arrays.asList(order1, order2, order3)));
	        logger.debug(toJsonStr(listOperations.range(user1, 0, -1)));
	        // [{"id":"30000","orderNo":"AO003","price":300.3,"createDate":1447385842793},{"id":"20000","orderNo":"AO002","price":200.2,"createDate":1447385842793},{"id":"10000","orderNo":"AO001","price":100.1,"createDate":1447385842793}]

	        logger.debug(toJsonStr(listOperations.rightPop(user1)));
	        //  {"id":"10000","orderNo":"AO001","price":100.1,"createDate":1447385842793}
	        logger.debug(toJsonStr(listOperations.range(user1, 0, -1)));
	        //  [{"id":"30000","orderNo":"AO003","price":300.3,"createDate":1447385842793},{"id":"20000","orderNo":"AO002","price":200.2,"createDate":1447385842793}]
	        
	        // ???????
	        logger.debug(toJsonStr(listOperations.rightPop(user1, 3, TimeUnit.SECONDS)));
	        logger.debug(toJsonStr(listOperations.range(user1, 0, -1)));
	        Thread.sleep(4 * 1000);
	        logger.debug(toJsonStr(listOperations.range(user1, 0, -1)));
        }
	
	@Test
        public void ListOperations_rightPopAndLeftPush() throws Exception
        {
	        //      V rightPopAndLeftPush(K sourceKey, K destinationKey, long timeout, TimeUnit unit);
	        //      Long rightPushIfPresent(K key, V value);
	        
	        ListOperations<String, Order> listOperations = redisTemplate.opsForList();
                logger.debug(listOperations.leftPushAll(user1, Arrays.asList(order1, order2, order3)));
                logger.debug(toJsonStr(listOperations.range(user1, 0, -1)));
                logger.debug(listOperations.leftPushAll(user2, Arrays.asList(order1, order2, order3)));
                logger.debug(toJsonStr(listOperations.range(user2, 0, -1)));
                
                listOperations.rightPopAndLeftPush(user1, user2);
                logger.debug(toJsonStr(listOperations.range(user1, 0, -1)));
                logger.debug(toJsonStr(listOperations.range(user2, 0, -1)));
        }
	
	// =============================== SetOperations ================================
	@Test
        public void testName() throws Exception
        {
	        ValueOperations<String, Integer> appdownCount = redisTemplate.opsForValue();
        }
	
	@Test
        public void SetOperations() throws Exception
        {
	        String appdownKey_groupkey = "appdown:resource:channel";
	        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
	        ValueOperations<String, Integer> appdownCount = redisTemplate.opsForValue();
	        
	        System.out.println("================================= 下载app START =================================");
	        for (int i = 0; i < 200; i++)
                {
	                Random random = new Random();
                        String appdownkey = "appdown:resource_" + random.nextInt(5) + ":channel_" + random.nextInt(5);
                        setOperations.add(appdownKey_groupkey, appdownkey);
                        appdownCount.increment(appdownkey, 1);
                }
	        System.out.println("================================= 下载app START =================================");
	        
	        Set<String> appdown_key＿set = setOperations.members(appdownKey_groupkey);
	        for (String appdown_key : appdown_key＿set)
                {
                        System.out.println(appdown_key + " 下载数量 : " + appdownCount.get(appdown_key));
                        appdownCount.getOperations().delete(appdown_key);
                }
	        
	        System.out.println("================================= 清空下载记录 =================================");
	        for (String appdown_key : appdown_key＿set)
                {
                        System.out.println(appdown_key + " 下载数量 : " + appdownCount.get(appdown_key));
                }
	        
//	        setOperations.getOperations().delete(appdownKey_groupkey);
	        
        }
	
	// =============================== ZSetOperations ================================
	// =============================== HashOperations ================================
}
