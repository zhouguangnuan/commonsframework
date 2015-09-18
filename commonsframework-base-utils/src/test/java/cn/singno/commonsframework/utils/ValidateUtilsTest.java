/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.utils</p>
 * <p>文件名：ValidateUtilsTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-9-上午8:18:41</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.utils;

import java.util.ArrayList;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;


/**
 * <p>名称：ValidateUtilsTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-5-9 上午8:18:41
 * @version 1.0.0
 */
public class ValidateUtilsTest
{
	@Test
	public void testname() throws Exception
	{
		Object obj = null;
		System.out.println(ValidateUtils.isEmpty(obj));
		obj = new String[]{};
		System.out.println(ValidateUtils.isEmpty(obj));
		obj = new char[]{'1'};
		System.out.println(ValidateUtils.isEmpty(obj));
		obj = new int[]{1, 2};
		System.out.println(ValidateUtils.isEmpty(obj));
		obj = new Integer[]{1, 2};
		System.out.println(ValidateUtils.isEmpty(obj));
	}
	
	@Test
	public void testname2() throws Exception
	{
		Object obj = new ArrayList();
		System.out.println(obj.getClass().isArray());
		obj = new Object[]{};
		System.out.println(obj.getClass().isArray());
		obj = new String[]{};
		System.out.println(obj.getClass().isArray());
	}
	
	@Test
	public void testname3() throws Exception
	{
		System.err.println(BooleanUtils.isTrue(true));
	}
}
