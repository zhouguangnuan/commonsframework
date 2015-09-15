package cn.singno.commonsframework.cache.memcached;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * 名称：MemcachedCache.java<br>
 * 描述：Cache注解实现类<br>
 * <pre></pre>
 * @author 周光暖
 * @date 2015-2-5 下午10:33:14
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class MemcachedCache implements Cache
{

	private final String name;// 缓存名称（即组的概念）
	private final MemCache memCache;

	public MemcachedCache(String name, int expire, MemcachedClient memcachedClient)
	{
		this.name = name;
		this.memCache = new MemCache(name, expire, memcachedClient);
	}

	@Override
	public void clear()
	{
		this.memCache.clear();
	}

	@Override
	public void evict(Object key)
	{
		this.memCache.delete(key.toString());
	}

	@Override
	public ValueWrapper get(Object key)
	{
		ValueWrapper wrapper = null;
		Object value = this.memCache.get(key.toString());
		if (value != null)
		{
			wrapper = new SimpleValueWrapper(value);
		}
		return wrapper;
	}
	
	@Override
	public <T> T get(Object key, Class<T> type)
	{
		Object cacheValue = this.memCache.get(key.toString());
		Object value = (cacheValue != null ? cacheValue : null);
		if (type != null && !type.isInstance(value))
		{
			throw new IllegalStateException("Cached value is not of required type [" + type.getName() + "]: " + value);
		}
		return (T) value;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public MemCache getNativeCache()
	{
		return this.memCache;
	}

	@Override
	public void put(Object key, Object value)
	{
		this.memCache.put(key.toString(), value);
	}

}
