package cn.singno.commonsframework.security.credentials;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 名称：RetryLimitHashedCredentialsMatcher.java<br>
 * 描述：自定义认证匹配器<br>
 * <pre>带密码错误次数限制功能</pre>
 * @author 周光暖
 * @date 2015-3-23 下午8:39:12
 * @version 1.0.0
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	private Cache<String, AtomicInteger> passwordRetryCache;

	private int retryNum = 3;// 默认密码重试上限次数
	
	private String cacheName = "passwordRetryCache";// 默认密码重试缓存名称

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager)
	{
		Assert.notNull(cacheManager, "cacheManager not be null");
		passwordRetryCache = cacheManager.getCache(this.cacheName);
	}
	
	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager, String cacheName)
	{
		Assert.notNull(cacheManager, "cacheManager not be null");
		if (StringUtils.isNotBlank(cacheName))
		{
			this.cacheName = cacheName;
		}
		passwordRetryCache = cacheManager.getCache(cacheName);
	}

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// 获得密码重复次数
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null)
		{
			retryCount = new AtomicInteger(0);
		}
		// 判断密码重复次数是否上限
		if (retryCount.incrementAndGet() > retryNum)
		{
			// 这里可以做相应逻辑处理，比如保存密码错误上限被锁定的账号，供后续解锁使用
			throw new ExcessiveAttemptsException("密码重试次数上限");
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches)
		{
			// 密码匹配，清空密码重试次数缓存
			passwordRetryCache.remove(username);
		} else
		{
			// 密码错误，记录密码重试次数到缓存
			passwordRetryCache.put(username, retryCount);
		}
		return matches;
	}

	public int getRetryNum()
	{
		return retryNum;
	}

	public void setRetryNum(int retryNum)
	{
		this.retryNum = retryNum;
	}
}
