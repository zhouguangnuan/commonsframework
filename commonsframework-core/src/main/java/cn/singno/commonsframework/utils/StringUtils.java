package cn.singno.commonsframework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;


/**
 * <p>名称：StringUtils.java</p>
 * <p>描述：字符相关的方法</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2014-11-11 下午11:37:51
 * @version 1.0.0
 */
public class StringUtils {

	private static final String REGEX_IS_CHINESE = "[\u4e00-\u9fa5]";
	
	/**
	 * 是否包含中文
	 * <pre></pre>
	 * @param str
	 * @return
	 */
	public static Boolean isContainsChinese(String str)
	{
		if (org.apache.commons.lang3.StringUtils.isBlank(str))
		{
			return false;
		}
		boolean isContainsChinese = false;
		Pattern pat = Pattern.compile(REGEX_IS_CHINESE);
		Matcher matcher = pat.matcher(str);
		if (matcher.find())    {
			isContainsChinese = true;
		}
		return isContainsChinese;
	}
	
      /**  
     	* 半角转全角  
     	* @param input 要转换的字符串
     	* @return String 转换后的字符串
     	*/
	public static String banToQuan(String input)
	{
		if (null == input)
			return null;
		else
		{
			char c[] = input.toCharArray();
			for (int i = 0; i < c.length; i++)
			{
				if (c[i] == ' ')
				{
					c[i] = '\u3000';
				} else if (c[i] < '\177')
				{
					c[i] = (char) (c[i] + 65248);

				}
			}
			return new String(c);
		}
	}

    	/**  
    	 * 全角转半角 
    	 * @param input 要转换的字符串  
    	 * @return String 转换后的字符串
    	 */
	public static String quanToBan(String input)
	{
		if (null == input)
			return null;
		else
		{
			char c[] = input.toCharArray();
			for (int i = 0; i < c.length; i++)
			{
				if (c[i] == '\u3000')
				{
					c[i] = ' ';
				} else if (c[i] > '\uFF00' && c[i] < '\uFF5F')
				{
					c[i] = (char) (c[i] - 65248);

				}
			}
			String returnString = new String(c);
			return returnString;
		}
	}
}
