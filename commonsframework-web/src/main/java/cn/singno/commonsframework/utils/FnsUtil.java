package cn.singno.commonsframework.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * <p>File：FnsUtil.java</p>
 * <p>Title: el函数调用的工具类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-1 下午3:28:21</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class FnsUtil
{
	/**
	 * 将精确到秒的Unix时间戳转化为指定格式的日期字符串，参数：Unix时间戳，字符串格式
	 * @param time
	 * @param format
	 * @return
	 * @author 周光暖
	 */
	public static String getTimeFromLong(long time, String format)
	{
		return DateFormatUtils.format(time, format);
	}
}
