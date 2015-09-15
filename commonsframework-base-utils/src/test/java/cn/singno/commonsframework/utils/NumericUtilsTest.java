/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.utils</p>
 * <p>文件名：NumericUtilsTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-31-下午2:33:29</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.utils;

import org.junit.Test;

/**<p>名称：NumericUtilsTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-31 下午2:33:29
 * @version 1.0.0
 */
public class NumericUtilsTest
{
	/**
	 * 将34.22%格式的字符串转为0.3422
	 * 
	 * @param string 		百分比格式的字符串
	 * @param len 		精确位数
	 * @return Double 	转化后的double
	 */
	@Test
	public void testStringToDouble() throws Exception
	{
		String percentStr = "43.45644444%";
		System.out.println(NumericUtils.percentStrToDouble(percentStr, 0));// 0.0
		System.out.println(NumericUtils.percentStrToDouble(percentStr, 2));// 0.43
		System.out.println(NumericUtils.percentStrToDouble(percentStr, 3));// 0.435
		System.out.println(NumericUtils.percentStrToDouble(percentStr, 4));// 0.4346
	}

	/**
	 * 将Double转百分比格式的字符串输出
	 * 
	 * @param d 		Double
	 * @param len 	精确位数
	 * @return String 百分比格式的字符串
	 */
	@Test
	public void testDoubleToPercentStr() throws Exception
	{
		Double d = 0.34235d;
		System.out.println(NumericUtils.doubleToPercentStr(d, 2));// 34.24%
		System.out.println(NumericUtils.doubleToPercentStr(d, 3));// 34.235%
		System.out.println(NumericUtils.doubleToPercentStr(d, 4));// 34.2350%
	}
	
	@Test
	public void testPreciseAdd() throws Exception
	{
		Double v1 = 1.2d;
		Double v2 = 15d;
		System.out.println(NumericUtils.preciseAdd(v1, v2));// 16.2
	}
	
	@Test
	public void testPreciseSubtract() throws Exception
	{
		Double v1 = 1.2d;
		Double v2 = 15d;
		System.out.println(NumericUtils.preciseSubtract(v1, v2));// -13.8
	}
	
	@Test
	public void testPreciseMultiply() throws Exception
	{
		Double v1 = 1.2d;
		Double v2 = 15d;
		System.out.println(NumericUtils.preciseMultiply(v1, v2));// 18.0

	}
	
	@Test
	public void testPreciseDivide() throws Exception
	{
		Double divisor = 10d;
		Double dividend = 100d;
		
		System.out.println(NumericUtils.preciseDivide(divisor, dividend));// 0.1
	}
	
	@Test
	public void testRound() throws Exception
	{
		System.out.println(NumericUtils.round(12.335235d, 2));// 12.34
	}
	
}
