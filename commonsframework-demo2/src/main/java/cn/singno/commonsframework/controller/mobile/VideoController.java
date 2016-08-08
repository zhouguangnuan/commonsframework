package cn.singno.commonsframework.controller.mobile;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mangofactory.swagger.annotations.ApiIgnore;

import cn.singno.commonsframework.utils.MultipartUtils;
import cn.singno.commonsframework.utils.SpringUtils;

/**
 * 移动端接口
 * @author Administrator
 */
@ApiIgnore
@Controller
@RequestMapping("/mobile/video")
public class VideoController
{
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String view(HttpServletRequest request, HttpServletResponse response)
	{
		return "mobile/video/view";
	}
	
    @RequestMapping(value="download", method=RequestMethod.GET)
    public Object download(String path, String fileName, ModelMap modelMap) throws Exception{
    	String filePath = SpringUtils.getRootPath() + path;
    	File file = FileUtils.getFile(filePath);
    	ResponseEntity<byte[]> result = MultipartUtils.downFile(file, fileName);

//		URL url = new URL("http://localhost:8080" + path);
//		ResponseEntity<byte[]> result = MultipartUtils.downFile(url, "http://www.spasvo.com/ckfinder/userfiles/images/2014_5_8_01.jpg");

		return result;
    };
}
