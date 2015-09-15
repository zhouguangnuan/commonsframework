/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.module.app.controller</p>
 * <p>文件名：LoginController.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-26-上午10:47:40</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.module.app.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.singno.commonsframework.generic.GenericController;

/**<p>名称：LoginController.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-26 上午10:47:40
 * @version 1.0.0
 */
@Controller
public class LoginController extends GenericController
{
	@RequestMapping(value = "/login")
	public String showLoginForm(HttpServletRequest request, Model model)
	{
		Exception error = (Exception) request.getAttribute("shiroLoginFailure");
		if (error instanceof UnknownAccountException)
		{
			error = new UnknownAccountException("用户名/密码错误");
		} 
		else if (error instanceof IncorrectCredentialsException)
		{
			error = new IncorrectCredentialsException("用户名/密码错误");
		} 
		model.addAttribute("error", error);
		return "login";
	}
}
