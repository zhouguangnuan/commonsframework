package cn.singno.commonsframework.cache.memcached;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

/**
 * 名称：MemcachedCacheManager.java<br>
 * 描述：缓存管理器<br>
 * <pre></pre>
 * @author 周光暖
 * @date 2015-2-5 下午10:33:50
 * @version 1.0.0
 */
public class MemcachedCacheManager extends AbstractTransactionSupportingCacheManager
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MemcachedCacheManager.class);
	
	private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();// 缓存map
	private Configuration cacheExpConfig;// // 缓存过期时间配置
	private MemcachedClient memcachedClient; // xmemcached的客户端
	
	public MemcachedCacheManager() {}

	@Override
	protected Collection<? extends Cache> loadCaches()
	{
		Collection<Cache> values = cacheMap.values();
		return values;
	}

	/**
	 * 获得指定名称的缓存
	 */
	@Override
	public Cache getCache(String name)
	{
		Assert.hasText(name, "cache name not be none");
		Cache cache = cacheMap.get(name);
		if (cache == null)
		{
			Integer expire = getCacheExp(name);
			cache = new MemcachedCache(name, expire.intValue(), memcachedClient);
			cacheMap.put(name, cache);
		}
		return cache;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient)
	{
		this.memcachedClient = memcachedClient;
	}

	public void setCacheExpConfig(Resource cacheExpConfig) throws IOException, ConfigurationException
	{
		this.cacheExpConfig = new PropertiesConfiguration(cacheExpConfig.getFile());
	}
	
	/**
	 * 获得设置的缓存过期时间
	 * @param name
	 * @return
	 * @author 周光暖
	 */
	private Integer getCacheExp(String name)
	{
		Integer expire = 0;
		try
		{
			expire = this.cacheExpConfig.getInteger(name, 0);
		} catch (ConversionException e)
		{
			logger.warn("缓存[" + name + "]过期时间异常");
		}
		return expire;
	}
}
