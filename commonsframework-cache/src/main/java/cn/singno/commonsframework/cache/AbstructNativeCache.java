package cn.singno.commonsframework.cache;

import java.util.Collection;
import java.util.Set;

public abstract class AbstructNativeCache
{
	public abstract Object get(String key);

	public abstract void put(String key, Object value);

	public abstract void clear();

	public abstract void delete(String key);

	/**
	 * 描述：获得换出组的key<br>
	 * <pre></pre>
	 * @return
	 */
	public abstract String getGroupKey();
	
	/**
	 * 描述：获得缓存key<br>
	 * <pre></pre>
	 * @param key
	 * @return
	 */
	public String getKey(String key)
	{
		return getGroupKey() + "_" + key;
	};
	
	public abstract int size();
	
	public abstract Set keys();

	public abstract Collection values();
}
