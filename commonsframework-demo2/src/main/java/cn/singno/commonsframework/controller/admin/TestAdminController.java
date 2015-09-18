package cn.singno.commonsframework.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台管理接口
 */
@Controller
@RequestMapping("/admin")
public class TestAdminController
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
}
