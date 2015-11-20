/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.utils</p>
 * <p>文件名：HttpUtilsTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-7-16-上午11:41:40</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.collect.Maps;

/**
 * <p>名称：HttpUtilsTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-7-16 上午11:41:40
 * @version 1.0.0
 */
public class HttpUtilsTest
{
	@Test
	public void testname() throws Exception
	{
		String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=1&rsv_pq=f3728905000112c4&rsv_t=4400vpPnOafoXoq5ry3w9iwhu8%2BKNlJ4VRqKp%2FHWNOxWmQ004Gn9yoZMaCc&rsv_enter=1&rsv_sug3=1&rsv_sug1=1&rsv_sug2=0&inputT=296&rsv_sug4=297";
//		String url = "http://www.baidu.com";
		String result = HttpUtils.get(url, "GBK");
		System.out.println(result);
	}
	
	@Test
	public void testname2() throws Exception
	{
		String url = "https://app.nbmzyy.com/ws/getKspbByDpCode?version=1.1.4&ksdm=220";
		String result = HttpUtils.get(url, "UTF-8");
		System.out.println(result);
	}
	
	@Test
	public void testname3() throws Exception
	{
		String url = "http://app.nbmzyy.com/ws/getKspbByDpCode";
//		?version=1.1.4&ksdm=220
		Map<String, String> map = Maps.newHashMap();
		map.put("version", "1.1.4");
		map.put("ksdm", "220");
		String result = HttpUtils.post(url, map);
		System.out.println(result);
	}
	
	@Test
	public void testname4() throws Exception
	{
		String url = "http://123.56.162.129/demo2/common/test6";
		String result = HttpUtils.get(url);
		System.out.println(result);
	}
	
	@Test
        public void testname5() throws Exception
        {
                String url = "http://localhost:8080/commonsframework-demo2/common/upload";
                File file = FileUtils.getFile("C:\\Users\\Administrator\\Desktop\\白帽子讲Web安全.pdf");
                String result = HttpUtils.postMultipart(url, null, null, "file", file);
                System.out.println(result);
        }
	
	@Test
        public void testName() throws Exception
        {
	        File file = FileUtils.getFile("C:\\Users\\Administrator\\Desktop\\白帽子讲Web安全阅读笔记.rar");
	        System.out.println(file.getName());
        }
}
