package cn.singno.commonsframework.controller.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.mangofactory.swagger.annotations.ApiIgnore;

import cn.singno.commonsframework.utils.WeiXinUtils;

/**
 * 移动端接口
 * @author Administrator
 */
@ApiIgnore
@Controller
@RequestMapping("/mobile/video")
public class VideoController
{
	/**
	 * 微官网
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="{id}")
	public String view(HttpServletRequest request, HttpServletResponse response)
	{
		return "mobile/video/view";
	}
}
