package cn.singno.commonsframework.cache.memcached;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.serializer.MapSerializer;

import cn.singno.commonsframework.cache.AbstructNativeCache;

/**
 * 名称：MemCache.java<br>
 * 描述：Memcache服务器客户端执行类<br>
 * <pre></pre>
 * @author 周光暖
 * @date 2015-2-5 下午10:54:52
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class MemCache extends AbstructNativeCache
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MemCache.class);

	private final String name;
	private final int expire;// 缓存过期时间（0：永久）
	private final GenericMemcache genericMemcache;
	
	public MemCache(String name, int expire, MemcachedClient memcachedClient)
	{
		this.name = name;
		this.expire = expire;
		genericMemcache = new GenericMemcache(memcachedClient);
	}

	@Override
	public Object get(String key)
	{
		return genericMemcache.get(getKey(key));
	}

	@Override
	public void put(String key, Object value)
	{
		genericMemcache.putInGroup(getGroupKey(), getKey(key), value, expire);
	}

	@Override
	public void clear()
	{
		genericMemcache.delGroup(getGroupKey());
	}

	@Override
	public void delete(String key)
	{
		genericMemcache.delete(getKey(key));
	}
	
	@Override
	public int size()
	{
		Set<String> keySet = (Set<String>) genericMemcache.get(getGroupKey());
		return keySet==null ? 0 : keySet.size();
	}
	
	@Override
	public Set keys()
	{
		return (Set<String>) genericMemcache.get(getGroupKey());
	}
	
	@Override
	public Collection values()
	{
		Set<String> keySet = (Set<String>) genericMemcache.get(getGroupKey());
		Collection collection = null;
		if (CollectionUtils.isNotEmpty(keySet))
		{
			collection = new ArrayList();
			for (String key : keySet)
			{
				collection.add(genericMemcache.get(key));
			}
		}
		return collection;
	}
	
	/**
	 * 描述：获得缓存key集合的缓存key<br>
	 * <pre></pre>
	 * @return
	 */
	public String getGroupKey()
	{
		return "cacheGroup_" + this.name;// TODO N 避免该key值重复(对name进行转二进制码处理)
	}
}