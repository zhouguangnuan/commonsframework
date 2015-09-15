package cn.singno.commonsframework.module.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.singno.commonsframework.security.bean.SessionHelper;

@Controller
public class IndexController {

	@Autowired
	private SessionHelper sessionHelper;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request){ 
		
		System.out.println(request.getHeader("Referer"));
		
		
//		Subject subject = SecurityUtils.getSubject();
//	        Session sessionOnline = sessionDAO.readSession(subject.getSession().getId());
//	        Session session = subject.getSession();
//		Object object = session.getAttribute("key");
//	        
//		System.out.println("sessionOnline：" + sessionOnline.getId());
//		System.out.println("session：" + session.getId());
		
		
		Session session = sessionHelper.getSession(true);
		
		
		
//		OnlineSession session = (OnlineSession) subject.getSession();
//		Object sessionId = session.getId();
		
		
		return "/index";
	};
}
