//package cn.singno.commonsframework.cache.memcached;
//
//import java.io.IOException;
//
//import net.rubyeye.xmemcached.MemcachedClient;
//import net.rubyeye.xmemcached.MemcachedClientBuilder;
//import net.rubyeye.xmemcached.XMemcachedClientBuilder;
//import net.rubyeye.xmemcached.utils.AddrUtil;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import cn.singno.commonsframework.cache.memcached.MemCache;
//
//import com.alibaba.fastjson.JSON;
//
//
//public class XMemcachedTest
//{
//	private MemCache memCache;
//	private String key;
//	
//	@Before
//	public void init() throws IOException
//	{
//		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("192.168.2.15:11211"));
//		MemcachedClient memcachedClient = builder.build();
//		memCache = new MemCache("keySetKey", 10, memcachedClient);
//		String key = "key1";
//	}
//	
//	@Test
//	public void testname() throws Exception
//	{
//		 Object obj = new String[]{"singno", "nana"};
//		 memCache.clear();
//		 memCache.put(key, obj);
//		 System.out.println(JSON.toJSONString(memCache.get(key)));
//	}
//	
//	@Test
//	public void testname2() throws Exception
//	{
//		System.out.println(JSON.toJSONString(memCache.get(key)));
//	}
//}
