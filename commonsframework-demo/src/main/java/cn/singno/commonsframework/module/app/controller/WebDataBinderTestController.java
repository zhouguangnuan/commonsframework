//package cn.singno.commonsframework.module.app.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import cn.singno.commonsframework.bind.annotation.EnumConst;
//import cn.singno.commonsframework.constants.SexEnum;
//import cn.singno.commonsframework.module.app.entity.User;
//import cn.singno.commonsframework.module.app.model.TestModel;
//import cn.singno.commonsframework.utils.NetworkUtils;
//
//import com.google.common.collect.Lists;
//
//@Controller
//public class WebDataBinderTestController<T>
//{
//	
//	@RequestMapping(value = "/test/binder", method = RequestMethod.GET)
//	@ResponseBody
//	public Object test(String str, HttpServletRequest request, Boolean bool, User user,  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date date)
//	{ 
//		String LocalIp = NetworkUtils.getLocalIp();
//		String NetIp = NetworkUtils.getNetIp();
//		String RemortIp= NetworkUtils.getRemortIpStr(request);
//		System.out.println("LocalIp：" + LocalIp);
//		System.out.println("NetIp：" + NetIp);
//		System.out.println("RemortIp：" + RemortIp);
//		
//		System.out.println(date);
//		
//		int ip = NetworkUtils.getRemortIpInt(request);
//		String ipStr = NetworkUtils.getRemortIpStr(request);
//		List<Object> list = Lists.newArrayList();
//		list.add(ip);
//		list.add(ipStr);
//		list.add(str);
//		list.add(bool);
//		list.add(date);
//		return list;
//	};
//	
//	@RequestMapping(value = "/test/binder2", method = RequestMethod.POST)
//	@ResponseBody
//	public Object test(ArrayList<String> list, String[] strs, HashMap<String, String> map)
//	{ 
//		System.out.println(map.get("key"));
//		return list;
//	};
//	
//	@RequestMapping(value = "/test/enum", method = RequestMethod.GET)
//	@ResponseBody
//	public Object test2(@EnumConst SexEnum sexEnum)
//	{
//		
//		return sexEnum;
//	}
//	
//	@RequestMapping(value = "/test/enum2", method = RequestMethod.GET)
//	@ResponseBody
//	public Object test3(User user)
//	{
//		SexEnum sexEnum = user.getSex();
//		 
//		return sexEnum;
//	}
//	
//	@RequestMapping(value = "/test/map", method = RequestMethod.POST)
//	@ResponseBody
//	public Object test4(TestModel test)
//	{
//		System.out.println(test.getMap().get("key"));
//		return test;
//	}
//	
//	@RequestMapping(value = "/test/map2", method = RequestMethod.POST)
//	@ResponseBody
//	public Object test44(@RequestBody TestModel test)
//	{
//		System.out.println(test.getMap().get("key"));
//		return test;
//	}
//
//	@RequestMapping(value = "/test/mvc"/*, method = RequestMethod.POST*/)
//	@ResponseBody
//	public Object test5(/*Class<T> enumType, String v1, Short v3, Boolean v4, Long v5, Float v6, Double v7*/ Integer v2)
//	{
//		System.out.println("===================");
//		return v2;
//	}
//}
