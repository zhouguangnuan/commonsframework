package cn.singno.commonsframework.controller.mobile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * 移动端接口
 * @author Administrator
 */
@ApiIgnore
@Controller
@RequestMapping("/mobile/upload")
public class UploadController
{
	@RequestMapping(value="{view}", method=RequestMethod.GET)
	public String view(@PathVariable String view)
	{
		return "mobile/upload/" + view;
	}
}
