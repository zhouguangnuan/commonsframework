/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.utils</p>
 * <p>文件名：AssertUtilsTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-9-下午2:25:47</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.utils;

import org.junit.Test;

import cn.singno.commonsframework.constants.DefaultDescribableEnum;
import cn.singno.commonsframework.exception.BusinessException;

/**
 * <p>名称：AssertUtilsTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-5-9 下午2:25:47
 * @version 1.0.0
 */
public class AssertUtilsTest
{
	@Test
	public void testname() throws Exception
	{
		Object collection = new String[]{"", ""};
		AssertUtils.notEmpty(collection, new BusinessException(DefaultDescribableEnum.PARAMES_ERROR, "集合不能为空!"));
	}
	
	@Test
	public void testname2() throws Exception
	{
		String textToSearch = "abcd";
		String substring = "a";
		AssertUtils.doesNotContain(textToSearch, substring, new BusinessException(DefaultDescribableEnum.PARAMES_ERROR, "集合不能为空!"));
	}
}
