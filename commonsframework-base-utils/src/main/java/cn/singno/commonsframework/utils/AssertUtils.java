/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.utils</p>
 * <p>文件名：AssertUtils.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-9-下午1:52:52</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.utils;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import cn.singno.commonsframework.exception.DescribableException;

/**
 * <p>名称：AssertUtils.java</p>
 * <p>描述：断言工具类</p>
 * <pre>
 *    断言失败，抛出指定描述异常
 * </pre>
 * @author 周光暖
 * @date 2015-5-9 下午1:52:52
 * @version 1.0.0
 */
public class AssertUtils
{
	public static void isTrue(boolean expression, DescribableException describableException)
	{
		if (BooleanUtils.isNotTrue(expression)) throw describableException; 
	}

	public static void isFalse(boolean expression, DescribableException describableException)
	{
		if (BooleanUtils.isNotFalse(expression)) throw describableException; 
	}

	public static void isNull(Object object, DescribableException describableException)
	{
		if (ValidateUtils.isNotNull(object)) throw describableException; 
	}

	public static void notNull(Object object, DescribableException describableException)
	{
		if (ValidateUtils.isNull(object)) throw describableException; 
	}

	public static void doesNotContain(String textToSearch, String substring, DescribableException describableException)
	{
		if (StringUtils.contains(textToSearch, substring)) throw describableException; 
	}

	public static void isEmpty(Object collection, DescribableException describableException)
	{
		if (ValidateUtils.isNotEmpty(collection)) throw describableException; 
	}
	
	public static void notEmpty(Object collection, DescribableException describableException)
	{
		if (ValidateUtils.isEmpty(collection)) throw describableException; 
	}

	public static void equals(Object object1, Object object2, DescribableException describableException)
	{
		if (ObjectUtils.notEqual(object1, object2)) throw describableException; 
	}

	public static void notEquals(Object object1, Object object2, DescribableException describableException)
	{
		if (ObjectUtils.equals(object1, object2)) throw describableException; 
	}
}
