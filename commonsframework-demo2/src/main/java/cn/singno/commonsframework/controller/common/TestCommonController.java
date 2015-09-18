package cn.singno.commonsframework.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.singno.commonsframework.utils.NetworkUtils;

import com.alibaba.druid.support.http.util.IPAddress;

/**
 * 通用接口
 */
@Controller
@RequestMapping("/common")
public class TestCommonController
{
	@RequestMapping(value="/test1", method=RequestMethod.GET)
	@ResponseBody
	public Object test1(String str)
	{
		return str;
	}
	
	@RequestMapping(value="/user/test2", method=RequestMethod.GET)
	@ResponseBody
	public Object test2(String str)
	{
		return str;
	}
	
	@RequestMapping(value="/ip", method=RequestMethod.GET)
	@ResponseBody
	public Object ip(HttpServletRequest request)
	{	
//		localIp		10.172.17.249
//		netIp		123.56.162.129
//		remortIpStr	115.231.212.10
//     
		
		String localIp =  NetworkUtils.getLocalIp();
		String netIp = NetworkUtils.getNetIp();
		String remortIpStr = NetworkUtils.getRemortIpStr(request);
		
		System.out.println("localIp：" + localIp);
		System.out.println("netIp：" + netIp);
		System.out.println("remortIpStr：" + remortIpStr);
		
		return "SUCCESS";
	}
}
