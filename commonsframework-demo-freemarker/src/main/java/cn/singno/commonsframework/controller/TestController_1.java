package cn.singno.commonsframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 通用接口
 */
@Controller
public class TestController_1
{
	@RequestMapping(value="/test1", method=RequestMethod.GET)
	public String test1(Model model)
	{
	        System.out.println("=========  执行了  test1 =========");
		
		model.addAttribute("name", "singno");
		model.addAttribute("isComeFromCancelPay", Boolean.TRUE);
		
		
//		model.addAttribute("name2", "");
		
		return "test1"; 
	}
	
	@RequestMapping(value="/test1.html", method=RequestMethod.GET)
        public String test12(Model model)
        {
                System.out.println("=========  执行了  test1.html =========");
                
                model.addAttribute("name", "singno");
                model.addAttribute("isComeFromCancelPay", Boolean.TRUE);
                
                
//              model.addAttribute("name2", "");
                
                return "test1"; 
        }
	
	@RequestMapping(value="/test2/{page}", method=RequestMethod.GET)
	public String test2(@PathVariable String page)
	{
		System.out.println("=============== 刷新了页面 ===============");
		return "test2";
	}
}
