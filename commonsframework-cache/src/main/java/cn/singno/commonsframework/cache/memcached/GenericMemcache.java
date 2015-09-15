package cn.singno.commonsframework.cache.memcached;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

public class GenericMemcache
{
     private static final Logger     logger     = Logger.getLogger(GenericMemcache.class);

     private MemcachedClient          memcachedClient;

     public GenericMemcache(MemcachedClient memcachedClient)
     {
    	  Assert.notNull(memcachedClient, "MemcachedClient mast not be null!");
          this.memcachedClient = memcachedClient;
     }

     public boolean put(String key, Object value)
     {
          return this.put(key, value, 0);
     }

     public boolean put(String key, Object value, int expireSecond)
     {
          boolean bool = false;
          try
          {
               bool = memcachedClient.set(key, expireSecond, value);
          } catch (TimeoutException e)
          {
               logger.error(e);
          } catch (InterruptedException e)
          {
               logger.error(e);
          } catch (MemcachedException e)
          {
               logger.error(e);
          }
          return bool;
     }

     public Object get(String key)
     {
          Object value = null;
          try
          {
               value = memcachedClient.get(key);
          } catch (TimeoutException e)
          {
               logger.error(e);
          } catch (InterruptedException e)
          {
               logger.error(e);
          } catch (MemcachedException e)
          {
               logger.error(e);
          }
          return value;
     }

     public boolean delete(String key)
     {
          boolean bool = false;
          try
          {
               bool = memcachedClient.delete(key);
          } catch (TimeoutException e)
          {
               logger.error(e);
          } catch (InterruptedException e)
          {
               logger.error(e);
          } catch (MemcachedException e)
          {
               logger.error(e);
          }
          return bool;
     }

     public boolean delGroup(String groupKey)
     {
          Boolean bool = true;
         
          Set<String> keySet = (Set<String>) get(groupKey);
          if (CollectionUtils.isEmpty(keySet))
          {
               return bool;
          }
          for (String key : keySet)
          {
        	  delete(key);
          }

          boolean again = true;
          try
          {
               while (again)
               {
                    again = false;
                    Set<String> currentKeySet = null;
                    GetsResponse<Set<String>> currentResult = memcachedClient.gets(groupKey);
                    if (null != currentResult && CollectionUtils.isNotEmpty(currentResult.getValue()))
                    {
                         currentKeySet = currentResult.getValue();
                         currentKeySet.removeAll(keySet);
                         again = !memcachedClient.cas(groupKey, 0, currentKeySet, currentResult.getCas());
                    }
               }
          } catch (TimeoutException e)
          {
               logger.error(e);
          } catch (InterruptedException e)
          {
               logger.error(e);
          } catch (MemcachedException e)
          {
               logger.error(e);
          }
         
          return !again;
     }

     public boolean putInGroup(String groupKey, String key, Object value)
     {
          return putInGroup(groupKey, key, value, 0);
     }

     public boolean putInGroup(String groupKey, String key, Object value, Integer expireSecond)
     {
          expireSecond = expireSecond == null ? 0 : expireSecond;

          boolean bool = true;

          if (value == null || StringUtils.isBlank(key))
          {
               return bool;
          }
          if (StringUtils.isBlank(groupKey))
          {
               return this.put(key, value, expireSecond);
          }

          // 添加value到缓存
          bool = put(key, value, expireSecond);

          // 维护keySet，新增value对应的key到keySet
          boolean again = bool;
          try
          {
               while (again)
               {
                    GetsResponse<Set<String>> result = memcachedClient.gets(groupKey);
                    Set<String> keySet = null;
                    if (null != result)
                    {
                         keySet = result.getValue() == null ? new HashSet<String>() : result.getValue();
//                         if (keySet.contains(key))
//                         {
//                              remove(key);
//                              again = false;
//                         }
//                         else
//                         {
                              keySet.add(key);
                              again = !memcachedClient.cas(groupKey, 0, keySet, result.getCas());
//                         }
                    } else
                    {
                         keySet = new HashSet<String>();
                         keySet.add(key);
                         again = !memcachedClient.add(groupKey, 0, keySet);
                    }
               }
          } catch (TimeoutException e)
          {
               logger.error(e);
          } catch (InterruptedException e)
          {
               logger.error(e);
          } catch (MemcachedException e)
          {
               logger.error(e);
          }

          return !again;
     }
}