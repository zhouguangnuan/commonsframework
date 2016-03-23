package cn.singno.commonsframework.controller.common;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.singno.commonsframework.bean.JsonResult;
import cn.singno.commonsframework.constants.DefaultDescribableEnum;
import cn.singno.commonsframework.utils.CryptoUtils;
import cn.singno.commonsframework.utils.PublicKeyMap;
import cn.singno.commonsframework.utils.RSAUtils;

import com.google.common.collect.Maps;

/**
 * 通用接口
 */
@Controller
@RequestMapping("/common/testRSA")
public class TestRSAController
{
	private static final String PUBLIC_KEY_MAP = "PUBLIC_KEY_MAP";
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view() throws Exception
	{
		return "common/testRSA";
	}
	
	@RequestMapping(value="/keyPair", method=RequestMethod.GET)
	@ResponseBody
	public Object keyPair(HttpServletRequest request) throws Exception
	{
		PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap();
		System.out.println(publicKeyMap);
		
		JsonResult result = new JsonResult(DefaultDescribableEnum.SUCCESS);
		Map<String, String> data = Maps.newHashMap();
		data.put("modulus", publicKeyMap.getModulus());
		data.put("exponent", publicKeyMap.getExponent());
		result.setData(data);
		return result;
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public Object login(String password) throws Exception
	{
		String pwd = RSAUtils.decryptStringByJs(password);
		pwd = URLDecoder.decode(pwd, CharEncoding.UTF_8);
		System.out.println(pwd);

		JsonResult result = new JsonResult(DefaultDescribableEnum.SUCCESS);
		result.setData(Maps.newHashMap());
		result.getData().put("pwd", pwd);
		return result;
	}
	
	@RequestMapping(value="/login2", method=RequestMethod.POST)
	@ResponseBody
	public Object login2(String password) throws Exception
	{
		byte[] byteCiphertext = Hex.decodeHex(password.toCharArray());
		byte[] pwd = CryptoUtils.RSAdecrypt(byteCiphertext, RSAUtils.getKeyPair().getPrivate());
		
		String pwdStr = StringUtils.reverse(new String(pwd));
		pwdStr = URLDecoder.decode(pwdStr, CharEncoding.UTF_8);
		System.out.println(pwdStr);

		JsonResult result = new JsonResult(DefaultDescribableEnum.SUCCESS);
		result.setData(Maps.newHashMap());
		result.getData().put("pwd", pwdStr);
		return result;
	}
}
