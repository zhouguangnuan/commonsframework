package cn.singno.commonsframework.cache.keyGenerator;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.interceptor.KeyGenerator;

import com.alibaba.fastjson.JSON;

/**
 * 名称：MyKeyGenerator.java<br>
 * 描述：key生成器<br>
 * <pre></pre>
 * @author 周光暖
 * @date 2015-2-3 下午10:58:06
 * @version 1.0.0
 */
public class MyKeyGenerator implements KeyGenerator
{

	@Override
	public Object generate(Object target, Method method, Object... params)
	{
		String key = null==method ? "" : method.getName() + "_";
		
		return key + StringUtils.remove(JSON.toJSONString(params), "\"");
	}
	
}
