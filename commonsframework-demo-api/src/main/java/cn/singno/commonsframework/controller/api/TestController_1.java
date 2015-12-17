package cn.singno.commonsframework.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.singno.commonsframework.bean.JsonResult;
import cn.singno.commonsframework.constant.ResultStateEnum;

@Controller
@RequestMapping("/api")
public class TestController_1 
{
	/**
	 * 微官网
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/test1", method=RequestMethod.GET)
	@ResponseBody
	public Object test1(HttpServletRequest request, HttpServletResponse response)
	{
	        JsonResult jsonResult = new JsonResult(ResultStateEnum.SUCCESS);
	        
		return jsonResult;
	}
}
