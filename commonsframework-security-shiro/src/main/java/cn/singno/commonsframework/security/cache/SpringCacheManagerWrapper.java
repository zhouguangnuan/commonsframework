package cn.singno.commonsframework.security.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import cn.singno.commonsframework.cache.AbstructNativeCache;

/**
 * 名称：SpringCacheManagerWrapper.java<br>
 * 描述：Spring缓存管理器<br>
 * <pre>使用spring cache实现缓存管理</pre>
 * @author 周光暖
 * @date 2015-3-23 下午8:17:55
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class SpringCacheManagerWrapper implements CacheManager {

    private org.springframework.cache.CacheManager springCacheManager;

    /**
     * 设置spring cache manager
     *
     * @param cacheManager
     */
    public void setSpringCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.springCacheManager = cacheManager;
    }

	@Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        org.springframework.cache.Cache springCache = springCacheManager.getCache(name);
        return new SpringCacheWrapper(springCache);
    }

    static class SpringCacheWrapper implements Cache {
        private org.springframework.cache.Cache springCache;

        SpringCacheWrapper(org.springframework.cache.Cache springCache) {
            this.springCache = springCache;
        }

        @Override
        public Object get(Object key) throws CacheException {
            Object value = springCache.get(key);
            if (value instanceof SimpleValueWrapper) {
                return ((SimpleValueWrapper) value).get();
            }
            return value;
        }

        @Override
        public Object put(Object key, Object value) throws CacheException {
            springCache.put(key, value);
            return value;
        }

        @Override
        public Object remove(Object key) throws CacheException {
            springCache.evict(key);
            return null;
        }

        @Override
        public void clear() throws CacheException {
            springCache.clear();
        }

        @Override
        public int size() {
            if(springCache.getNativeCache() instanceof AbstructNativeCache) {
            	AbstructNativeCache nativeCache = (AbstructNativeCache) springCache.getNativeCache();
                return nativeCache.size();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
        }

        @Override
        public Set keys() {
            if(springCache.getNativeCache() instanceof AbstructNativeCache) {
            	AbstructNativeCache nativeCache = (AbstructNativeCache) springCache.getNativeCache();
                return nativeCache.keys();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
        }

        @Override
        public Collection values() {
        	if(springCache.getNativeCache() instanceof AbstructNativeCache) {
            	AbstructNativeCache nativeCache = (AbstructNativeCache) springCache.getNativeCache();
                return nativeCache.values();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
        }
    }
}
