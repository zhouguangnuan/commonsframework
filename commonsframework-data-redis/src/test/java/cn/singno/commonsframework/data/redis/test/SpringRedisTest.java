package cn.singno.commonsframework.data.redis.test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.util.Assert;

import cn.singno.commonsframework.data.redis.demo.Order;

@SuppressWarnings("all")
public class SpringRedisTest extends GenericTest
{
        @Test
        public void testName() throws Exception
        {
                ValueOperations<String, Integer> valueOper = (ValueOperations<String, Integer>) redisTemplate.opsForValue();
                Long num = valueOper.increment("num", 1);
                logger.debug(num);

                Object obj = valueOper.get("num");
                logger.debug(obj);
        }
        
        @Test
        public void valueOperationTest()
        {
                ValueOperations<String, Order> valueOper = (ValueOperations<String, Order>) redisTemplate.opsForValue();
                Order order = new Order("10000", "OA111011", 11.0, new Date());
                valueOper.set("order:" + order.getId(), order);
                logger.debug(valueOper.get("order:" + order.getId()).getOrderNo());
        }

        @Test
        public void listOperationTest() throws Exception
        {
                String key = "user:list3";
                ListOperations<String, Order> listOper = (ListOperations<String, Order>) redisTemplate.opsForList();
                logger.debug(listOper.leftPush(key, new Order("11", "OA1110", 11.0, new Date())));// lpush,head
                logger.debug(listOper.leftPush(key, new Order("22", "OA1110", 11.0, new Date())));// lpush,head
                logger.debug(listOper.rightPush(key, new Order("33", "OA1110", 11.0, new Date())));// rpush,tail
                
                logger.debug(toJsonStr(listOper.rightPop(key)));
                logger.debug(toJsonStr(listOper.rightPop(key)));
                logger.debug(toJsonStr(listOper.leftPop(key)));
        }

        @Test
        public void boundValueOperationTest() throws InterruptedException
        {
                Order order = new Order("10000", "OA1110", 11.0, new Date());
                BoundValueOperations<String, Order> bvo = (BoundValueOperations<String, Order>) redisTemplate.boundValueOps("order:" + order.getId());
                bvo.set(order);
                bvo.expire(30, TimeUnit.SECONDS);
                
                Thread.sleep(10 * 1000);
                Order order2 = bvo.get();
                Assert.notNull(order2);
                
                Thread.sleep(10 * 1000);
                order2 = bvo.get();
                Assert.notNull(order2);
                
                Thread.sleep(10 * 1000);
                order2 = bvo.get();
                Assert.notNull(order2);
                
                Thread.sleep(10 * 1000);
                order2 = bvo.get();
                Assert.notNull(order2);
                
                Thread.sleep(30 * 1000);
                order2 = bvo.get();
                Assert.isNull(order2);
        }

        /**
         * 非连接池环境下，事务操作；对于spring data
         * redis而言，每次操作(例如，get，set)都有会从pool中获取connection； 因此在连接池环境下，使用事务需要注意。
         */
        @Test
        public void txUnusedPoolTest() throws Exception
        {
                Order order = new Order("10000", "OA1110", 11.0, new Date());
                redisTemplate.watch("order:" + order.getId());
                redisTemplate.multi();
                ValueOperations<String, Order> tvo = redisTemplate.opsForValue();
                tvo.set("user:" + order.getId(), order);
                
                order = tvo.get("user:" + order.getId());
                logger.debug(toJsonStr(order));
                
                redisTemplate.exec();
                
                order = tvo.get("user:" + order.getId());
                logger.debug(toJsonStr(order));
        }

        /**
         * 在连接池环境中，需要借助sessionCallback来绑定connection
         */
        @Test
        public void txUsedPoolTest()
        {
                SessionCallback<Order> sessionCallback = new SessionCallback<Order>()
                {
                        public Order execute(@SuppressWarnings("rawtypes") RedisOperations operations)
                                        throws DataAccessException
                        {
                                operations.multi();
                                Order order = new Order("10000", "OA1110", 11.0, new Date());
                                String key = "order:" + order.getId();
                                BoundValueOperations<String, Order> oper = operations.boundValueOps(key);
                                oper.set(order);
                                oper.expire(60, TimeUnit.MINUTES);
                                operations.exec();
                                return order;
                        }
                };
                redisTemplate.execute(sessionCallback);
        }

        /**
         * pipeline : 1，正确使用方式
         */
        @Test
        public void pipelineTest()
        {
                final byte[] rawKey = redisTemplate.getKeySerializer().serialize("user_total");
                // pipeline
                RedisCallback<List<Object>> pipelineCallback = new RedisCallback<List<Object>>()
                {
                        public List<Object> doInRedis(RedisConnection connection) throws DataAccessException
                        {
                                connection.openPipeline();
                                connection.incr(rawKey);
                                connection.incr(rawKey);
                                return connection.closePipeline();
                        }

                };

                List<Object> results = (List<Object>) redisTemplate.execute(pipelineCallback);
                for (Object item : results)
                {
                        logger.debug(item.toString());
                }
        }

        @Test
        // pipeline:备用方式
        public void pipelineXTest()
        {
                byte[] rawKey = redisTemplate.getKeySerializer().serialize("user_total");
                RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
                RedisConnection redisConnection = RedisConnectionUtils.getConnection(factory);
                List<Object> results;
                try
                {
                        redisConnection.openPipeline();
                        redisConnection.incr(rawKey);
                        results = redisConnection.closePipeline();
                } finally
                {
                        RedisConnectionUtils.releaseConnection(redisConnection, factory);
                }
                if (results == null)
                {
                        return;
                }
                for (Object item : results)
                {
                        logger.debug(item.toString());
                }

        }

        /**
         * list/set排序，注意排序是根据value值的“字符”特性排序，如果value是java-object字节流，将不会有效
         */
        @Test
        public void sortTest()
        {
                ListOperations<String, String> listOper = redisTemplate.opsForList();
                String listKey = "user:list";
                listOper.leftPush(listKey, "zhangsan");
                listOper.leftPush(listKey, "lisi");
                listOper.leftPush(listKey, "wanger");
                SortQueryBuilder<String> builder = SortQueryBuilder.sort(listKey);
                builder.alphabetical(true);// 对字符串使用“字典顺序”
                builder.order(org.springframework.data.redis.connection.SortParameters.Order.DESC);// 倒序
                builder.limit(0, 2);
                // builder.limit(new Range(0, 2));
                List<String> results = redisTemplate.sort(builder.build());
                for (String item : results)
                {
                        logger.debug(item);
                }
        }
}
