/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.utils</p>
 * <p>文件名：StringUtils.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-9-下午1:03:15</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.utils;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang.CharSet;
import org.junit.Test;

/**
 * <p>名称：StringUtils.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-5-9 下午1:03:15
 * @version 1.0.0
 */
public class StringUtilsTest
{
	@Test
	public void testName() throws Exception
	{
		System.out.println("a".getBytes().length);//		1
		System.out.println("中".getBytes().length);//		3
		System.out.println("a".getBytes("UTF-8").length);//		1
		System.out.println("a".getBytes("GBK").length);//		1
		System.out.println("中".getBytes("UTF-8").length);//		3
		System.out.println("中".getBytes("GBK").length);//		2
		
		System.out.println(StringUtils.bytesLength("中", "123"));// 0

	}
	
}
