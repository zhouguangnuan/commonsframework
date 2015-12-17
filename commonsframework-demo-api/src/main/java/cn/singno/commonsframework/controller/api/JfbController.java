package cn.singno.commonsframework.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jfb")
public class JfbController
{
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public Object view(HttpServletRequest request)
	{
	        return null;
	}
}
