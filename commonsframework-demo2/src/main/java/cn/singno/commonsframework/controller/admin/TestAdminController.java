package cn.singno.commonsframework.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * 后台管理接口
 */
@Controller
@RequestMapping("/admin")
public class TestAdminController extends AdminBaseController
{
	@RequestMapping(value="/test1", method=RequestMethod.GET)
//	@ResponseBody
	public String test1(String str)
	{
		return "admin/test1";
	}
	
	@RequestMapping(value="/test1", method=RequestMethod.POST)
	@ResponseBody
	public Object test1_post(HttpServletRequest request, String str)
	{
		return str;
	}
	
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	@ResponseBody
	public Object test2(HttpServletRequest request, String str)
	{
		return str;
	}
	
	@RequestMapping(value="/user/test2", method=RequestMethod.GET)
	@ResponseBody
	public Object user_test2(HttpServletRequest request, String str)
	{
		System.out.println("Accept: " + request.getHeader("Accept") + "<br>");
		System.out.println("Host: " + request.getHeader("Host") + "<br>");
		System.out.println("Referer : " + request.getHeader("Referer") + "<br>");
		System.out.println("Accept-Language : " + request.getHeader("Accept-Language") + "<br>");
		System.out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding") + "<br>");
		System.out.println("User-Agent : " + request.getHeader("User-Agent") + "<br>");
		System.out.println("Connection : " + request.getHeader("Connection") + "<br>");
		System.out.println("Cookie : " + request.getHeader("Cookie") + "<br>");
		
		
		return str;
	}
}
