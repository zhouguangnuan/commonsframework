package cn.singno.commonsframework.module.app.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.singno.commonsframework.bind.annotation.CurrentUser;
import cn.singno.commonsframework.module.app.entity.User;
import cn.singno.commonsframework.module.app.service.UserService;

@Controller
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequiresPermissions("user:view")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String list(@CurrentUser User user){ 
		return "/user/list";
	};
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User addUser(User user){ 
		return userService.save(user);
	};
	
	@RequestMapping(value = "/test/1", method = RequestMethod.POST)
	@ResponseBody
	public User testVerification(@Valid User user){ 
		return userService.save(user);
	};
	
	@RequestMapping(value = "/test/2", method = RequestMethod.POST)
	@ResponseBody
	public User testVerification2(User user){ 
		return userService.save(user);
	};
	
	@RequestMapping(value = "/test/3", method = RequestMethod.POST)
	@ResponseBody
	public User testVerification3(String refrenceId){ 
		return userService.update(refrenceId);
	};
}
