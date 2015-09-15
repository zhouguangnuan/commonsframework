package cn.singno.commonsframework.module.app.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/test")
public class XmlTestController {

	@RequestMapping(value="/xml", method=RequestMethod.POST)
	public String test(HttpServletRequest request, HttpServletResponse response, MultipartFile testFile) throws Exception{ 
		
		SAXReader reader = new SAXReader(); 
//		InputStream in = request.getInputStream();  222
		InputStream in = testFile.getInputStream();
		Document dom = reader.read(in);
		System.out.println(JSON.toJSONString(dom));
		
		return null;
	};
}
