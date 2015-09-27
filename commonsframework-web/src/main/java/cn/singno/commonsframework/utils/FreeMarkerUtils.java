/*
 * @(#)FreeMarker.java 2014-1-8 下午1:32:59
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.CharEncoding;
import org.apache.log4j.Logger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * <p>名称：FreeMarker.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-4-2 上午10:53:36
 * @version 1.0.0
 */
public class FreeMarkerUtils
{
	private static final Logger logger = Logger.getLogger(FreeMarkerUtils.class);

	private static FreeMarkerConfigurer freeMarkerConfigurer = SpringUtils.getBean("freeMarkerConfigurer");

	/**
	 * 根据Freemarker模板名称及容器对象获取模板内容
	 * 
	 * @param templateName 	模板文件名称
	 * @param dataMap 		数据对象
	 * @return String 			解析后的模板内容
	 */
	public static String loadTemplateContent(Map<String, Object> dataMap, String templateName)
	{
		try
		{
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			Template t = configuration.getTemplate(templateName);
			return FreeMarkerTemplateUtils.processTemplateIntoString(t, dataMap);
		} catch (TemplateException e)
		{
			logger.error("创建FreeMarker模板失败： ", e);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			logger.error("开启FreeMarker模板文件时失败： ", e);
		} catch (IOException e)
		{
			logger.error("生成FreeMarker内容时失败： ", e);
		}
		return null;
	}

	/**
	 * 根据对应模板生成静态文件
	 * 
	 * @param templateName 	ftl模版文件名
	 * @param targetHtmlPath 	生成静态页面的路径
	 * @param dataMap 		一个Map的数据结果集
	 */
	public static void createTemplate(String templateName, String targetHtmlPath, Map<String, Object> dataMap)
	{
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		try
		{
			// 指定模版路径
			Template template = configuration.getTemplate(templateName);
			// 静态页面路径
			File htmlFile = new File(targetHtmlPath);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), CharEncoding.UTF_8));
			// 处理模版
			template.process(dataMap, out);
			out.flush();
			out.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
