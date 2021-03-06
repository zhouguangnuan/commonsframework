package cn.singno.commonsframework.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * 管网接口
 */
@ApiIgnore
@Controller
@RequestMapping("/web")
public class TestWebController
{
	/**
	 * 测试
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/test1", method=RequestMethod.GET)
	public String test1(HttpServletRequest request, HttpServletResponse response)
	{
		return "wgw";
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
