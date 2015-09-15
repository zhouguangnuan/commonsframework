package cn.singno.commonsframework.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * <p>File：EncodeUtils.java</p>
 * <p>Title: 封装各种格式的编码解码工具类</p>
 * <p>Description:主要功能为Html编解码、Xml编解码、Hex编解码、URL编解码、base62/64编解码</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-5 上午9:38:05</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class EncodeUtils
{
    private static final Logger logger     = Logger.getLogger(EncodeUtils.class);

    // 十六进制字符与数字转换表
    private static final String HEX_STRING = "0123456789ABCDEF";

    // 私有构造器，防止类的实例化
    public EncodeUtils()
    {
        super();
    }
    
    /**
     * 将字符串进行64进制转码
     * @param string 	要进行转码的字符中
     * @return String 	转码后的64进制字符串
     */
    public static String stringToBase64(String string)
    {
	    if(StringUtils.isNotBlank(string))
	    {
		    string = Base64.encodeBase64String(string.getBytes());
	    }
	    return string;
    }
    
    /**
     * 根据UTF-8编码将64进制字符串转化为字符串
     * @param base64Str 	64进制字符串
     * @return String 		转化后的字符串
     */
    public static String Base64ToString(String base64Str)
    {
	    return Base64ToString(base64Str, null);
    }
    
    /**
     * 根据指定的编码将64进制字符串转化为字符串
     * @param base64Str 	64进制字符串
     * @param charsetName 	指定编码名称
     * @return String 		转化后的字符串
     */
    public static String Base64ToString(String base64Str, String charsetName)
    {
	    String str = "";
	    if (StringUtils.isNotBlank(base64Str))
	    {
		if(StringUtils.isNotBlank(charsetName))
		{
			str = org.apache.commons.codec.binary.StringUtils.newString(Base64.decodeBase64(base64Str), charsetName);
		}
		else
		{
			str = org.apache.commons.codec.binary.StringUtils.newStringUtf8(Base64.decodeBase64(base64Str));
		}
	    }
	    return str;
    }

    /**
     * 将字符串进行十六进制转码
     * @param string 		要进行转码的字符中
     * @param charsetName 	编码格式
     * @return String 		转码后的十六进制字符串
     */
    public static String stringToHex(String string, String charsetName)
    {
        String result = null;
        if (StringUtils.isNotBlank(string))
        {
            byte[] bytes = null;
            if (StringUtils.isNotBlank(charsetName))
            {
                bytes = string.getBytes(Charset.forName(charsetName));
            }
            else
            {
                bytes = string.getBytes();
            }
            result = Hex.encodeHexString(bytes);
        }
        return result.toUpperCase();
    }
    
    /**
     * 根据指定的编码将十六进制字符串转化为字符串
     * @param hexString 		十六进制字符串
     * @param charsetName 	指定编码名称
     * @return String 		转化后的字符串
     */
    public static String hexToString(String hexString, String charsetName)
    {
        String result = null;
        if (StringUtils.isNotBlank(hexString))
        {
            try
            {
                byte[] bytes = Hex.decodeHex(hexString.toCharArray());
                if (StringUtils.isNotBlank(charsetName))
                {
                    result = new String(bytes, Charset.forName(charsetName));
                }
                else
                {
                    result = new String(bytes);
                }
            }
            catch (DecoderException e)
            {
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 将十六进制字符转换成数字。
     * @param c 	将转换的字符。
     * @return 	int	转换成的数字。
     * @throws 	NotByteException 
     */
    public static int StringHexToInt(char c)
    {
        char cc = Character.toUpperCase(c);
        int value = HEX_STRING.indexOf(cc);
        if (value == -1)
        {
            throw new IllegalArgumentException("必须是十六进制字符");
        }
        return value;
    }

    /**
     * 以指定的编码计算异或和
     * @param str 		计算异或和的字符串
     * @param charset 	编码方式
     * @return String 	异或校验码
     * @throws UnsupportedEncodingException
     */
    public static String getXorString(String str, String charset)
    {
        String result = null;
        byte[] bt = null;
        try
        {
            bt = str.getBytes(charset);
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error(e);
        }
        if (null != bt)
        {
            byte checkValue = bt[0];
            for (int i = 1; i < bt.length; i++)
            {
                checkValue ^= bt[i];
            }
            // 转换成16进制字符串
            // int是32位，byte是8位，不进行&0xff时会出现高24位补位误差，产生0xffffff
            result = Integer.toHexString(checkValue & 0xFF).toUpperCase();
            if (result.length() == 1)
            {
                result = "0" + result;
            }
        }
        return result;
    }
}
