package cn.singno.commonsframework.utils;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import cn.singno.commonsframework.constants.DefaultDescribableEnum;
import cn.singno.commonsframework.exception.ApiCheckParameterException;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;

/**
 * 接口工具类
 * @author 周光暖
 */
public class ApiUtils
{
	private static final String MESSAGE_LINK = ":";
	
	public static final String NONCE = "nonce";
	public static final String SIGN = "sign";
	public static final String TIME_STAMP = "timestamp";
	
	public static void checkParameter(HttpServletRequest request) throws ApiCheckParameterException
	{
		if(null == request)
		{
			throw new ApiCheckParameterException(DefaultDescribableEnum.NUL_ERROR);
		}
		
		Map<String, String[]> params = request.getParameterMap();
		if (null == params || params.isEmpty())
                {
	                throw new ApiCheckParameterException(DefaultDescribableEnum.NUL_ERROR);
                }
		
		String sign = request.getHeader(SIGN);
		String timestamp = request.getHeader(TIME_STAMP);
		if (StringUtils.isBlank(sign) || StringUtils.isBlank(timestamp)) throw new ApiCheckParameterException(DefaultDescribableEnum.ILLEGAL_REQUEST);
		
		// 与客户端约定的随机数秘钥
		String nonce = request.getHeader(NONCE);
		// 客户端提供加密后的秘钥（服务端未与客户端约定好秘钥）
		if (StringUtils.isNotBlank(nonce))
                {
	                // RSA 解密
			byte[] plaintext = CryptoUtils.RSAdecrypt(nonce.getBytes(Charsets.UTF_8), getPrivateKey());
			if(null == plaintext) throw new ApiCheckParameterException(DefaultDescribableEnum.ILLEGAL_REQUEST);
			nonce = new String(plaintext, Charsets.UTF_8);
			saveNonce(nonce, request);
                }
		// 在会话中获取秘钥（服务端已与客户端约定好秘钥）
		else 
		{
			nonce = getNonce(request);
			if(StringUtils.isBlank(nonce)) throw new ApiCheckParameterException(DefaultDescribableEnum.ILLEGAL_REQUEST);
		}
		
		ArrayList<String> array_name_value = Lists.newArrayList();
		for (Map.Entry<String, String[]> entry : params.entrySet())
                {
			String key = StringUtils.isNotBlank(entry.getKey()) ? entry.getKey() : "";
			if (ArrayUtils.isNotEmpty(entry.getValue()))
                        {
	                        for (String value : entry.getValue())
                                {
	                        	value = StringUtils.isBlank(value) ? "" : value;
	                        	array_name_value.add(key + MESSAGE_LINK + value);
                                }
                        }
			else {
				array_name_value.add(key + MESSAGE_LINK + "");
			}
                }
		array_name_value.add(TIME_STAMP + MESSAGE_LINK + timestamp);
		array_name_value.add(NONCE + MESSAGE_LINK + nonce);
		
		Collections.sort(array_name_value);
		String digest = JSON.toJSONString(array_name_value);
		String sign_2 = CryptoUtils.HASHencrypt(digest, CryptoUtils.ALGORITHM.HASH.SHA_1);
		
		if (!StringUtils.equalsIgnoreCase(sign, sign_2))
                {
			throw new ApiCheckParameterException(DefaultDescribableEnum.API_ERROR_SIGN_ISERROR);
                }
	}
	
	public static PrivateKey getPrivateKey()
	{
		return null;
	}
	
	/**
	 * 获取当前用户约定的随机数秘钥
	 * @return
	 */
	public static String getNonce(HttpServletRequest request)
	{
		Object nonce = request.getSession(Boolean.TRUE).getAttribute(NONCE);
		if(null != nonce)
		{
			return nonce.toString();
		}	
		return null;
	}
	
	/**
	 * 保存当前用户约定的随机数秘钥
	 * @return
	 */
	public static void saveNonce(String nonce, HttpServletRequest request)
	{
		if (StringUtils.isNotBlank(nonce))
                {
			request.getSession(Boolean.TRUE).setAttribute(NONCE, nonce);
                }
	}
}
