/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.utils</p>
 * <p>文件名：EncodeUtilsTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-28-下午7:26:03</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.utils;

import javax.xml.ws.RequestWrapper;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.CharEncoding;
import org.junit.Test;

/**<p>名称：EncodeUtilsTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-28 下午7:26:03
 * @version 1.0.0
 */
public class EncodeUtilsTest
{
	@Test
	public void testStringToBase64() throws Exception
	{
//		System.out.println(EncodeUtils.stringToBase64("你好"));
		System.out.println(EncodeUtils.stringToBase64("f6eb8071281cef66bc3a7477:c802c064dbb804be5a6962da"));
	}
	
	@Test
	public void testname() throws Exception
	{
		System.out.println(EncodeUtils.Base64ToString("5L2g5aW9"));
	}
	
	@Test
	public void testgetXorString() throws Exception
	{
		System.out.println(EncodeUtils.getXorString("1", CharEncoding.UTF_8));
	}
	
	@Test
	public void testStringHexToInt() throws Exception
	{
		int str = EncodeUtils.StringHexToInt('a' );
		System.out.println(str);
	}
	
	@Test
	public void testStringToHex() throws Exception
	{
		String str = EncodeUtils.stringToHex("你好", CharEncoding.UTF_8);
		System.out.println(str);// E4BDA0E5A5BD
	}
	
	@Test
	public void testHexToString() throws Exception
	{
		String str = EncodeUtils.hexToString("E4BDA0E5A5BD", CharEncoding.UTF_8);
		System.out.println(str);// 你好
	}
	
}
