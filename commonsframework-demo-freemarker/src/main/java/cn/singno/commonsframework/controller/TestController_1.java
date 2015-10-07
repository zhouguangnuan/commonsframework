package cn.singno.commonsframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 通用接口
 */
@Controller
public class TestController_1
{
	@RequestMapping(value="/test1", method=RequestMethod.GET)
	public String test2(Model model)
	{
		
		model.addAttribute("name", "singno");
		
		return "test1";
	}
}
