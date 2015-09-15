/*
 * @(#)MultipartController.java 2015-3-1 下午3:36:35
 * Copyright 2015 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.module.app.controller;

import java.net.URL;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import cn.singno.commonsframework.bean.FileUploadResult;
import cn.singno.commonsframework.bean.MultipartFiles;
import cn.singno.commonsframework.generic.GenericController;
import cn.singno.commonsframework.utils.MultipartUtils;

/**
 * <p>File：MultipartController.java</p>
 * <p>Title: 文件上传下载控制器</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-1 下午3:36:35</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Controller
@RequestMapping("/multipart")
public class MultipartController extends GenericController
{
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String upload() throws Exception{ 
		return "/multipartTest";
	};
	
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
		return "/multipartTest";
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
		return "/multipartTest";
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
//		File file = FileUtils.getFile(SpringUtils.getRootPath() + path);
//		ResponseEntity<byte[]> result = MultipartUtils.downFile(file, fileName);
		
		URL url = new URL("http://localhost:8080" + path);
		ResponseEntity<byte[]> result = MultipartUtils.downFile(url, "http://www.spasvo.com/ckfinder/userfiles/images/2014_5_8_01.jpg");
		
		return result;
	};
}
