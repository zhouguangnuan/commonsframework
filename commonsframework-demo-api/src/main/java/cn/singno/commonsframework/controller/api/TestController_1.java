package cn.singno.commonsframework.controller.api;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.singno.commonsframework.bean.JsonResult;
import cn.singno.commonsframework.constant.ResultStateEnum;
import cn.singno.commonsframework.constants.DefaultDescribableEnum;

import com.google.common.collect.Maps;

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
	
	@RequestMapping(value="/test2/{page}/data", method=RequestMethod.GET)
	@ResponseBody
	public Object test2(@PathVariable String page)
	{
//		System.out.println("执行了：/test2/" + page + "/data");
		JsonResult jsonResult = new JsonResult(DefaultDescribableEnum.SUCCESS);
                Map<String, Object> data = Maps.newHashMap();
                data.put("page", page);
                jsonResult.setData(data);
	        return jsonResult;
	}
	
	@RequestMapping(value="/test3", method=RequestMethod.GET)
	@ResponseBody
	public Object test3(HttpServletRequest request) throws IOException
	{
		String rootPath = request.getSession().getServletContext().getRealPath("/") ;
		String emailPath = rootPath + "WEB-INF/classes/email.properties";
		String content = FileUtils.readFileToString(FileUtils.getFile(emailPath), Charsets.UTF_8);
	        return content;
	}
}
