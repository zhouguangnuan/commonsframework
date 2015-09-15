/*
 * @(#)ParameterUtils.java 2014-2-25 下午4:25:35
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.singno.commonsframework.bean.ClientParameter;
import cn.singno.commonsframework.constants.DefaultDescribableEnum;
import cn.singno.commonsframework.constants.DefaultSystemConst;
import cn.singno.commonsframework.exception.BusinessException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

/**
 * <p>File：ParameterUtils.java</p>
 * <p>Title: API开发平台参数解析处理工具类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-4 下午5:04:36</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class ParameterUtils
{
	private static final Log logger = LogFactory.getLog(ParameterUtils.class);

	private static final String MESSAGE_FLAG = "0XFF";

	private static final String MESSAGE_ELAG = "0X00";

	private static final String MESSAGE_SPEC = ",";

	private static final String MESSAGE_LINK = ":";

	// 私有构造器
	private ParameterUtils()
	{
		super();
	}

	/**
	 * 参数校验
	 * 
	 * @param userDes 	异或校验码
	 * @param userKey 	客户端USERKEY
	 * @param dataLen 	数据长度
	 * @param data 		 数据
	 * @throws BusinessException
	 */
	public static void checkParameter(String userDes, String userKey, Integer dataLen, String data) throws BusinessException
	{
		if (StringUtils.isBlank(userDes)
				|| StringUtils.isBlank(userKey)
				|| dataLen == null)
		{
			throw new BusinessException(DefaultDescribableEnum.NUL_ERROR);
		}
		// 数据长度校验
		int len = cn.singno.commonsframework.utils.StringUtils.length(data);
		if (dataLen.intValue() != len)
		{
			throw new BusinessException(DefaultDescribableEnum.LEN_ERROR);
		}
		// 异或校验码校验
		userDes = StringUtils.trimToEmpty(userDes);
		userKey = StringUtils.trimToEmpty(userKey);
		String encrypt = getUserDes(userKey, dataLen);
		if (!userDes.equalsIgnoreCase(encrypt))
		{
			throw new BusinessException(DefaultDescribableEnum.DES_ERROR);
		}
		// userkey 校验
		if (!userKey.equals(DefaultSystemConst.USER_KEY))
		{
			throw new BusinessException(DefaultDescribableEnum.KEY_ERROR);
		}
		// data 参数里是否包含emoji表情
		for (int i = 0; i < dataLen; i++)
		{
			if (!NotEmojiCharacter(data.charAt(i)))
			{
				throw new BusinessException(DefaultDescribableEnum.EXISTS_EMOJI);
			}
		}
	}

	/**
	 * 从ClientParameter对象获取参数键值对，返回Map<String, String>
	 * 
	 * @param parameter 			ClientParameter
	 * @return Map<String, String> 	Map<String, String>
	 */
	public static Map<String, String> getMapFromParameter(ClientParameter parameter)
	{
		String data = null;
		if (null != parameter)
			data = parameter.getData();
		return getMapFromData(data);
	}

	/**
	 * 接收并解析消息体：根据消息体内容，取得Map<String,String>键值对
	 * 
	 * @param data 				消息体内容
	 * @return Map<String, String> 	Map<String, String>
	 */
	public static Map<String, String> getMapFromData(String data)
	{
		logger.info("接收到请求参数：" + data);
		Map<String, String> map = Maps.newHashMap();
		if (StringUtils.isNotBlank(data))
		{
			String[] strings = StringUtils.split(data, MESSAGE_SPEC);
			if (ArrayUtils.isNotEmpty(strings))
			{
				for (int i = 0; i < strings.length; i++)
				{
					String[] keyValue = StringUtils.split(strings[i], MESSAGE_LINK);
					int len = keyValue.length;
					if (null != keyValue && len > 0)
					{
						if (len == 2)
						{
							String valueString = StringUtils.trimToEmpty(keyValue[1]);
							valueString = StringUtils.replace(valueString, MESSAGE_FLAG, MESSAGE_SPEC);
							valueString = StringUtils.replace(valueString, MESSAGE_ELAG, MESSAGE_LINK);
							map.put(StringUtils.trimToEmpty(keyValue[0]), valueString);
						} else if (len == 1)
						{
							map.put(StringUtils.trimToEmpty(keyValue[0]), "");
						}
					}
				}
			}
		}
		return map;
	}

	/**
	 * 发送参数：将原始的map参数键值对转码并组合为data消息体
	 * 
	 * @param map 	参数键值对
	 * @return String 	消息体内容
	 */
	public static String getDataFromMap(Map<String, String> map)
	{
		String result = null;
		if (null != map && !map.isEmpty())
		{
			StringBuffer stringBuffer = new StringBuffer();
			for (Map.Entry<String, String> entry : map.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				value = StringUtils.replace(value, MESSAGE_SPEC, MESSAGE_FLAG);
				value = StringUtils.replace(value, MESSAGE_LINK, MESSAGE_ELAG);
				stringBuffer.append(key).append(MESSAGE_LINK);
				stringBuffer.append(value).append(MESSAGE_SPEC);
			}
			String tempString = stringBuffer.toString();
			result = StringUtils.substring(tempString, 0, tempString.length() - 1);
		}
		return StringUtils.trimToEmpty(result);
	}

	/**
	 * 根据协议格式计算异或校验码
	 * 
	 * @param userKey 	 异或校验码
	 * @param dataLen 	data参数值长度
	 * @return String 		异或校验码
	 */
	public static String getUserDes(String userKey, Integer dataLen)
	{
		String result = null;
		if (null != dataLen && StringUtils.isNotBlank(userKey))
		{
			StringBuffer stringBuffer = new StringBuffer(userKey);
			stringBuffer.append(Integer.toString(dataLen));
			result = EncodeUtils.getXorString(stringBuffer.toString(), DefaultSystemConst.DEFAULT_UNICODE);
		}
		return result;
	}

	/**
	 * 任意条件动态查询接口处理方法：将请求参数中的json查询参数转化为Map<String,Object>
	 * 如：{"userAge":"1","userName":"刘"}
	 * 
	 * @param json 				接收到的json查询参数
	 * @return Map<String, Object> 	Map<String, Object>该返回值可以直接传给GenericDao
	 */
	public static Map<String, Object> getMapFromJson(String json)
	{
		Map<String, Object> map = null;
		if (!ValidateUtils.isNull(json))
		{
			map = Maps.newHashMap();
			JSONObject jsonObject = JSON.parseObject(json);
			Iterator<Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
			while (iterator.hasNext())
			{
				Entry<String, Object> entry = iterator.next();
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}

	
	// ============================================================================================
	
	/**
	 * 是否包含emoji表情
	 * @param codePoint
	 * @return false 存在emoji字符，true 不是emoji字符
	 */
	private static boolean NotEmojiCharacter(char codePoint)
	{
		return (codePoint == 0x0)
				|| (codePoint == 0x9)
				|| (codePoint == 0xA)
				|| (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}
	
	
	public static void main(String[] args)
	{
		// String data = "userId:01A18EF53D8F4EC28AAE8A7A04D0448A";
		String data = "😄userId:5DA7D8C073744860925636A88B163063,wshopId:094E840EF9AD4C33AA92A6ADE1C42168";
		String userKey = DefaultSystemConst.USER_KEY;
		// String data =
		// "msgCate:1,userId:E09690D18E8240F28A157C9FE82ED8D4,msgText:测试测试测试,msgReciver:13967849277,userPassword:smsuserzttx12345678,userName:\u79FB\u52A8\u670D\u52A1\u5E73\u53F0";
		// String data = "userAccount:13058555555";
		// String data = "keyWord:13058555555";
		// String data = "userPassword:123456,userAccount:18069014851";
		// String userKey = ApplicationConst.TRADE_USER_KEY;
		int dataLen = cn.singno.commonsframework.utils.StringUtils.length(data);
		String userDes = getUserDes(userKey, dataLen);
		System.out.println("userDes：" + userDes);
		System.out.println("data：" + data);
		System.out.println("userKey：" + userKey);
		System.out.println("dataLen：" + dataLen);
	}
}
