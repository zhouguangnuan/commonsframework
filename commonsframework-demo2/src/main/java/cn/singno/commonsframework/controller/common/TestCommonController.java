package cn.singno.commonsframework.controller.common;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mangofactory.swagger.annotations.ApiIgnore;

import cn.singno.commonsframework.bean.Config;
import cn.singno.commonsframework.bean.CurrentRequestHolder;
import cn.singno.commonsframework.bean.CurrentRequestHolder2;
import cn.singno.commonsframework.bean.FileUploadResult;
import cn.singno.commonsframework.bean.JsonResult;
import cn.singno.commonsframework.bean.MultipartFiles;
import cn.singno.commonsframework.constants.DefaultDescribableEnum;
import cn.singno.commonsframework.constants.DefaultSystemConst;
import cn.singno.commonsframework.exception.FileUploadException;
import cn.singno.commonsframework.utils.ApiUtils;
import cn.singno.commonsframework.utils.HttpServletUtils;
import cn.singno.commonsframework.utils.MultipartUtils;
import cn.singno.commonsframework.utils.NetworkUtils;
import cn.singno.commonsframework.utils.SerialUtils;
import cn.singno.commonsframework.utils.SessionUtils;
import cn.singno.commonsframework.utils.SpringUtils;

/**
 * 通用接口
 */
@ApiIgnore
@Controller
@RequestMapping("/common")
public class TestCommonController
{
        @Autowired
        private CurrentRequestHolder2 currentRequestHolder2;
        
        @RequestMapping(value="/testConfig")
        @ResponseBody
        public Object testConfig(HttpServletRequest request)
        {
                if (StringUtils.equalsIgnoreCase(request.getMethod(), "GET"))
                {
                        return Config.weixin.getInt("APP_ID");
                } else {
                        Config.weixin.setProperty("APP_ID", "3333333");  
                        try
                        {
                                Config.weixin.save();
                        } catch (ConfigurationException e)
                        {
                                e.printStackTrace();
                        }
                        return "success";
                }
        }
        
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

		
		String localIp =  NetworkUtils.getLocalIp();
		String netIp = NetworkUtils.getNetIp();
		String remortIpStr = NetworkUtils.getRemortIpStr(request);
		
		System.out.println("localIp：" + localIp);
		System.out.println("netIp：" + netIp);
		System.out.println("remortIpStr：" + remortIpStr);
		
		return "SUCCESS";
	}
	
	@RequestMapping(value="/prox", method=RequestMethod.GET)
	@ResponseBody
	public Object prox_get(HttpServletRequest request)
	{	
		return "SUCCESS";
	}
	
	@RequestMapping(value="/prox", method=RequestMethod.POST)
	@ResponseBody
	public Object prox_post(HttpServletRequest request)
	{	
		return "SUCCESS";
	}

	/**
     * 单文件上传
     * @param file
     * @param modelMap
     * @return
     * @throws Exception
     * @author 周光暖
     */
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    @ResponseBody
    public Object upload(MultipartFile file, HttpServletRequest request) throws Exception{ 
    	Map<String, Object> result = Maps.newHashMap();
		try {
//			
			System.out.println(JSON.toJSONString(request.getParameterMap()));
			// {"chunks":["1"],"fileId":["o_1apfaqtk0a00htd9am1q8pjrh8"],"chunk":["0"],"name":["1.txt"]}
			
			String fileId = request.getParameter("fileId");
			String name = request.getParameter("name");
			String chunk = request.getParameter("chunk");
			Integer chunks = NumberUtils.stringToInt(request.getParameter("chunks"), 1);
			
			if(chunks > 1){
				// 分割上传
				
				String savePath = new StringBuffer(SpringUtils.getRootPath())
						.append(DefaultSystemConst.SYS_SEPARATOR)
						.append("upload")
						.append(DefaultSystemConst.SYS_SEPARATOR)
						.append("parts")
						.append(DefaultSystemConst.SYS_SEPARATOR)
						.append(fileId)
						.append(DefaultSystemConst.SYS_SEPARATOR)
						.append(name + "." + chunk + ".part")
						.toString();
				File savedFile = FileUtils.getFile(savePath);
				File parentFile = savedFile.getParentFile();
				if (!parentFile.exists()){
					parentFile.mkdirs();
				}
	        	file.transferTo(savedFile);
			}else{
				// 单体上传
				
				FileUploadResult uploadResult = MultipartUtils.uploadCommon(file);
	            result.put("uploadResult", uploadResult);
			}
			
            result.put("code", 1);
            result.put("message", "上传成功");
		} catch (Exception e) {
			result.put("code", 2);
            result.put("message", e.getMessage());
		}
        return result;
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
    @ResponseBody
    public Object uploads(MultipartFiles files, HttpServletRequest request) throws Exception{ 
    	System.out.println(JSON.toJSONString(request.getParameterMap()));
    	// {"two":["2"],"name":["http.jpg"],"one":["1"],"three[a]":["4"],"four[]":["6","7","8"],"three[b]":["5"]}
    	
    	Map<String, Object> result = Maps.newHashMap();
    	try {
            List<FileUploadResult> resultList = MultipartUtils.uploadCommon(files);

            result.put("code", 1);
            result.put("message", "上传成功");
            result.put("uploadResult", resultList);
    	} catch (Exception e) {
			result.put("code", 2);
            result.put("message", e.getMessage());
		}
        return result;
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
//    	File file = FileUtils.getFile(SpringUtils.getRootPath() + path);
//    	ResponseEntity<byte[]> result = MultipartUtils.downFile(file, fileName);

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
		
	@RequestMapping(value="/apiTest", method=RequestMethod.POST)
    @ResponseBody
    public Object apiTest(HttpServletRequest request) throws Exception
    { 
    	ApiUtils.checkParameter(request);
            return new JsonResult(DefaultDescribableEnum.SUCCESS);
    };
}