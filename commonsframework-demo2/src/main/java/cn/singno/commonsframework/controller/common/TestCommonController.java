package cn.singno.commonsframework.controller.common;

import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cn.singno.commonsframework.bean.CurrentRequestHolder;
import cn.singno.commonsframework.bean.CurrentRequestHolder2;
import cn.singno.commonsframework.bean.FileUploadResult;
import cn.singno.commonsframework.bean.MultipartFiles;
import cn.singno.commonsframework.utils.HttpServletUtils;
import cn.singno.commonsframework.utils.MultipartUtils;
import cn.singno.commonsframework.utils.NetworkUtils;
import cn.singno.commonsframework.utils.SessionUtils;

/**
 * 通用接口
 */
@Controller
@RequestMapping("/common")
public class TestCommonController
{
        @Autowired
        private CurrentRequestHolder2 currentRequestHolder2;
        
	@RequestMapping(value="/test1", method=RequestMethod.GET)
	@ResponseBody
	public Object test1(String str)
	{
		return str;
	}
	
	@RequestMapping(value="/user/test2", method=RequestMethod.GET)
	@ResponseBody
	public Object test2(String str)
	{
		return str;
	}
	
	@RequestMapping(value="/test3", method=RequestMethod.GET)
        @ResponseBody
        public Object test3()
        {
	        Map<String, String[]> params = HttpServletUtils.getRequest().getParameterMap();
	        System.out.println(JSON.toJSON(params));
	        
	        
	        HttpServletResponse response = HttpServletUtils.getResponse();
	        
                return "SUCCESS";
        }
	
	@RequestMapping(value="/ip", method=RequestMethod.GET)
	@ResponseBody
	public Object ip(HttpServletRequest request)
	{	
//		localIp		10.172.17.249
//		netIp		123.56.162.129
//		remortIpStr	115.231.212.10
//     
		
		String localIp =  NetworkUtils.getLocalIp();
		String netIp = NetworkUtils.getNetIp();
		String remortIpStr = NetworkUtils.getRemortIpStr(request);
		
		System.out.println("localIp：" + localIp);
		System.out.println("netIp：" + netIp);
		System.out.println("remortIpStr：" + remortIpStr);
		
		return "SUCCESS";
	}
	
	/**
         * 当文件上传
         * @param file
         * @param modelMap
         * @return
         * @throws Exception
         * @author 周光暖
         */
        @RequestMapping(value="/upload", method=RequestMethod.POST)
        public String upload(MultipartFile file, ModelMap modelMap) throws Exception{ 
                if (null!=file && !file.isEmpty())
                {
                        FileUploadResult uploadResult = MultipartUtils.uploadCommon(file);
                        modelMap.put("uploadResult", uploadResult);
                }
                return "wgw";
        };
        
        /**
         * 批量文件上传
         * @param fileList
         * @param modelMap
         * @return
         * @throws Exception
         * @author 周光暖
         */
        @RequestMapping(value="/uploads", method=RequestMethod.POST)
        public String uploads(MultipartFiles fileList, ModelMap modelMap) throws Exception{ 
                if (null!=fileList && !fileList.isEmpty())
                {
                        List<FileUploadResult> resultList = MultipartUtils.uploadCommon(fileList);
                        modelMap.put("list", resultList);
                }
                return "wgw";
        };
        
        /**
         * 文件下载
         * @param filePath
         * @param fileName
         * @param modelMap
         * @return
         * @throws Exception
         * @author 周光暖
         */
        @RequestMapping(value="/download", method=RequestMethod.POST)
        public Object download(String path, String fileName, ModelMap modelMap) throws Exception{ 
//              File file = FileUtils.getFile(SpringUtils.getRootPath() + path);
//              ResponseEntity<byte[]> result = MultipartUtils.downFile(file, fileName);
                
                URL url = new URL("http://localhost:8080" + path);
                ResponseEntity<byte[]> result = MultipartUtils.downFile(url, "http://www.spasvo.com/ckfinder/userfiles/images/2014_5_8_01.jpg");
                
                return result;
        };
        
        @RequestMapping(value="/request", method=RequestMethod.GET)
        public Object request(HttpServletRequest request, HttpServletResponse response) throws Exception
        { 
                HttpServletRequest request2 = CurrentRequestHolder.getCurrentRequest();
                
                System.out.println("request：" + JSON.toJSONString(request.getParameterMap()));
                System.out.println("request2：" + JSON.toJSONString(request2.getParameterMap()));
                
                System.out.println("request thread：" + Thread.currentThread().getId());
                System.out.println("request2 thread：" + Thread.currentThread().getId());
                
                System.out.println(ObjectUtils.equals(request, request2));
                
                
                System.out.println(response.getHeader("name"));
                response.setHeader("name", "name2");
                
                
                SessionUtils.test();
                
                return null;
        };
        
        @RequestMapping(value="/request2", method=RequestMethod.GET)
        public Object request2(HttpServletRequest request, HttpServletResponse response) throws Exception
        { 
                HttpServletRequest request2 = currentRequestHolder2.getRequest();
                HttpServletResponse response2 = currentRequestHolder2.getResponse();
                
                System.out.println("request：" + JSON.toJSONString(request.getParameterMap()));
                System.out.println("request2：" + JSON.toJSONString(request2.getParameterMap()));
                
                System.out.println("request thread：" + Thread.currentThread().getId());
                System.out.println("request2 thread：" + Thread.currentThread().getId());
                
                System.out.println(ObjectUtils.equals(request, request2));
                System.out.println(ObjectUtils.equals(response, response2));
                
                
                System.out.println(response.getHeader("name"));
                response.setHeader("name", "name2");
                
                
                SessionUtils.test2();
                
                return null;
        };
        
        @RequestMapping(value="/request/params", method=RequestMethod.GET)
        public Object request_params_get(String a1, Integer a2, Boolean a3) throws Exception
        { 
                System.out.println("a1：" + a1);
                System.out.println("a2：" + a2);
                System.out.println("a3：" + a3);
                
                return null;
        };
        
        @RequestMapping(value="/request/params", method=RequestMethod.POST)
        public Object request_params_post(String a1, Integer a2, Boolean a3) throws Exception
        { 
                System.out.println("a1：" + a1);
                System.out.println("a2：" + a2);
                System.out.println("a3：" + a3);
                
                return null;
        };
}
