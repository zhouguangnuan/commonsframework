package cn.singno.commonsframework.module.app.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookie")
public class CookieTestController {

	@RequestMapping(value="/test"/*, method=RequestMethod.POST*/)
	public String test(HttpServletRequest request, HttpServletResponse response){ 
		
		Cookie[] cookies = request.getCookies();
		
		HttpSession session = request.getSession();
		if (session != null)
		{
			boolean isNew = session.isNew();
			System.out.println("isNew：" + isNew);
			if (isNew)
			{
				session.setAttribute("session", session.getId());
			}
			System.out.println("===========================================");
		}
		
		Cookie cookie1 = new Cookie("test1", URLEncoder.encode("value1中国人"));
		cookie1.setHttpOnly(true);
		Cookie cookie2 = new Cookie("test2", URLEncoder.encode("value2中国人"));
		cookie2.setHttpOnly(true);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		String ss = request.getHeader("test");
		
//		System.out.println("进来了");
		
		return "cookieTest";
	};
}
