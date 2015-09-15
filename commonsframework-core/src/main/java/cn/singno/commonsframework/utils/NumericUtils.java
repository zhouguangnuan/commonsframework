/*
 * @(#)NumericUtils.java 2014-4-15 下午4:28:44
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * <p>File：NumericUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-4 下午4:42:05</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class NumericUtils
{
	private NumericUtils()
	{
		super();
	}

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	// 默认货币类型小数保留位数
	public static final int  CURRENCY_SCALE_NUM = 2;

	/**
	 * <p> 描述：百分比字符串-->Double</p>
	 * 
	 * <pre>
	 *    "34.22%" --> 0.3422
	 * </pre>
	 * @param percentStr 	百分比格式的字符串
	 * @param len		 精确位数
	 * @return
	 */
	public static Double percentStrToDouble(String percentStr, int len)
	{
		StringBuilder sb = new StringBuilder(".");
		for (int i = 0; i < len; i++)
		{
			sb.append("#");
		}
		DecimalFormat df = new DecimalFormat(sb.toString());
		percentStr = StringUtils.trimToEmpty(percentStr);
		NumberFormat nf = NumberFormat.getPercentInstance();
		Double d = 0D;
		try
		{
			d = (Double) nf.parse(percentStr);
			String str = df.format(d);
			d = Double.parseDouble(str);
		} catch (ParseException e)
		{
		} catch (ClassCastException e)
		{
		}
		return d;
	}

	/**
	 * <p>描述：Double --> 百分比字符串</p>
	 * <pre>
	 *    0.3422 --> "34.22%"
	 * </pre>
	 * @param d		Double
	 * @param len	精确位数
	 * @return String 	百分比格式的字符串
	 */
	public static String doubleToPercentStr(Double d, int len)
	{
		if (null == d)
			d = 0D;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumIntegerDigits(3);// 小数点前面最多显示几位的
		nf.setMaximumFractionDigits(len);// 小数点后面最多显示几位
		nf.setMinimumFractionDigits(len);
		return nf.format(d);
	}

	/**
	 * <p>描述：精确的加法运算</p>
	 * @param v1		加数
	 * @param v2		被加数
	 * @return
	 */
	public static double preciseAdd(double v1, double v2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * <p>描述：精确的减法运算</p>
	 * @param v1		减数
	 * @param v2		被减数
	 * @return
	 */
	public static double preciseSubtract(double v1, double v2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * <p>描述：精确的乘法运算</p>
	 * @param v1		乘数
	 * @param v2		被乘数
	 * @return
	 */
	public static double preciseMultiply(double v1, double v2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

    	/**
	 * <p>描述：精确除法运算</p>
	 * <pre>
	 *    提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入。
	 * </pre>
	 * @param divisor		除数
	 * @param dividend	被除数
	 * @return
	 */
	public static double preciseDivide(double divisor, double dividend)
	{
		return preciseDivide(divisor, dividend, DEF_DIV_SCALE);
	}
	
	/**
	 * <p>描述：精确除法运算</p>
	 * <pre>
	 *    提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后scale位，以后的数字四舍五入。
	 * </pre>
	 * @param divisor		除数
	 * @param dividend	被除数
	 * @param scale		小数点后保留的位数
	 * @return
	 */
	public static double preciseDivide(double divisor, double dividend, int scale)
	{
		if (scale < 0)
		{
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(divisor));
		BigDecimal b2 = new BigDecimal(Double.toString(dividend));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	
	public static double round(double v, int scale)
	{
		if (scale < 0)
		{
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * <p>描述：四舍五入</p>
	 * <pre>
	 *    精确的小数位四舍五入处理
	 * </pre>
	 * @param v		被四舍五入的数字
	 * @param scale	小数点后保留的位数
	 * @return
	 */
	public static BigDecimal round(BigDecimal v, int scale)
	{
		if (scale < 0)
		{
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v.toString());
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * <p>描述：（货币）四舍五入</p>
	 * <pre>
	 *    默认货币类型小数保留2位数
	 * </pre>
	 * @param bigDecimal
	 * @return
	 */
	public static BigDecimal roundCurrency(BigDecimal v)
	{
		return round(v, NumericUtils.CURRENCY_SCALE_NUM);
	}

	/**
	 * <p>描述：null --> 0</p>
	 * <pre>
	 *    整数null转换为零
	 * </pre>
	 * @param integer
	 * @return
	 */
	public static int nullToZero(Integer integer)
	{
		return nullToDefault(integer, 0);
	}

	/**
	 * <p>描述：null --> 指定的整数值</p>
	 * <pre>
	 *    整数null转换为指定的整数值
	 * </pre>
	 * @param integer
	 * @param idefault
	 * @return
	 */
	public static int nullToDefault(Integer integer, int idefault)
	{
		int iResult = idefault;
		if (null != integer)
			iResult = integer.intValue();
		return iResult;
	}
}
