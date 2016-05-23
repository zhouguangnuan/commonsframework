package cn.singno.commonsframework.controller.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.singno.commonsframework.utils.WeiXinUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 移动端接口
 * @author Administrator
 */
@Controller
@RequestMapping("/mobile")
public class TestMobileController extends MobileBaseController
{
	/**
	 * 微官网
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value={
		"/web/wgw_view",
	})
	public String wgw_view(HttpServletRequest request, HttpServletResponse response)
	{
		// 验证消息来自微信
//		if (!WeiXinUtils.comeFromWxBrowser(request))
//		{
//			return ""; 
//		}
		
		return "wgw";
	}
	
	/**
	 * 医院简介
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value={
		"/web/yyjj_view",
	})
	public String yyjj_view(HttpServletRequest request, HttpServletResponse response)
	{
		return "yyjj";
	}
	
	/**
	 * 名医推荐
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value={
		"/web/mytj_view",
	})
	public String mytj_view(HttpServletRequest request, HttpServletResponse response)
	{
		return "mytj";
	}
	
	/**
	 * 我要挂号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value={
		"/web/yygh_view",
	})
	public String yygh_view(HttpServletRequest request, HttpServletResponse response)
	{
		return "yygh";
	}
	
	/**
	 * 到院签到
	 * @param request
	 * @param response
	 * @return
	 */ 
	@RequestMapping(method=RequestMethod.GET, value={
		"/web/dyqd_view",
	})
	public String dyqd_view(HttpServletRequest request, Model model)
	{
		String code = request.getParameter("code");
		JSONObject jsonObject = WeiXinUtils.getAuthToken(code);
		model.addAttribute("jsonObject1", jsonObject);
		if(null != jsonObject){
			String access_token = jsonObject.getString("access_token");
			String openid = jsonObject.getString("openid");
			jsonObject = WeiXinUtils.getUserInfo(access_token, openid);
			model.addAttribute("jsonObject2", jsonObject);
		} 
		return "dyqd";
	}
	
	/**
	 * 我的排队
	 * @param request
	 * @param response
	 * @return
	 */ 
	@RequestMapping(method=RequestMethod.GET, value={
		"/web/wdpd_view",
	})
	public String wdpd_view(HttpServletRequest request, Model model)
	{
		String code = request.getParameter("code");
		JSONObject jsonObject = WeiXinUtils.getAuthToken(code);
		model.addAttribute("jsonObject1", jsonObject);
		if(null != jsonObject){
			String access_token = jsonObject.getString("access_token");
			String openid = jsonObject.getString("openid");
			jsonObject = WeiXinUtils.getUserInfo(access_token, openid);
			model.addAttribute("jsonObject2", jsonObject);
		} 
		return "wdpd";
	}
	
	/**
	 * 我要付钱
	 * @param request
	 * @param response
	 * @return
	 */ 
	@RequestMapping(method=RequestMethod.GET, value={
		"/web/wyfq_view",
	})
	public String wyfq_view(HttpServletRequest request, HttpServletResponse response)
	{
		return "wyfq";
	}
	
	/**
	 * 报告查询
	 * @param request
	 * @param response
	 * @return
	 */ 
	@RequestMapping(method=RequestMethod.GET, value={
		"/web/bgcx_view"
	})
	public String bgcx_view(HttpServletRequest request, HttpServletResponse response)
	{
		return "bgcx";
	}
	
	/**
	 * 就诊历史
	 * @param request
	 * @param response
	 * @return
	 */ 
	@RequestMapping(method=RequestMethod.GET, value={
		"/web/jzls_view"
	})
	public String jzls_view(HttpServletRequest request, HttpServletResponse response)
	{
		return "jzls";
	}
	
	/**
	 * 就诊人管理
	 * @param request
	 * @param response
	 * @return
	 */  
	@RequestMapping(method=RequestMethod.GET, value={
		"/app/jzrlb_view",
		"/web/jzrlb_view",
	})
	public String jzrlb_view(HttpServletRequest request, HttpServletResponse response)
	{
		return "jzrlb";
	}
	
	/**
	 * 我的满意度
	 * @param request
	 * @param response
	 * @return
	 */  
	@RequestMapping(method=RequestMethod.GET, value={
		"/web/user/wdmyd_view",
		"/app/user/wdmyd_view",
	})
	public String wdmyd_view(HttpServletRequest request, HttpServletResponse response)
	{
		return "wdmyd";
	}
	
}
