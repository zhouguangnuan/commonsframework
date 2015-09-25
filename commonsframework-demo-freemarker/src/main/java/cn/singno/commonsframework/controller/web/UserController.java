package cn.singno.commonsframework.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 管网接口
 */
@Controller
@RequestMapping("/user")
public class UserController 
{
	/**
	 * 测试
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/view/${id}", method=RequestMethod.GET)
	public String urse(@PathVariable String id)
	{
		System.out.println("1111111111111111111");
		return "user";
	}
	
	/**
	 * 测试
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/test2", method=RequestMethod.GET)
	public String test2(HttpServletRequest request, HttpServletResponse response)
	{
		return "wgw";
	}
}
