/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.module.freeMarker</p>
 * <p>文件名：FreeMarkerUtilsTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-4-2-上午11:50:35</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.module.freeMarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.singno.commonsframework.generic.GenericTest;
import cn.singno.commonsframework.module.app.entity.User;
import cn.singno.commonsframework.utils.FreeMarkerUtils;

import com.google.common.collect.Maps;

/**<p>名称：FreeMarkerUtilsTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-4-2 上午11:50:35
 * @version 1.0.0
 */
public class FreeMarkerUtilsTest extends GenericTest
{
	@Test
	public void testLoadTemplateContent() throws Exception
	{
		String templateName = "index.ftl";
		Map<String, Object> dataMap = Maps.newHashMap();
		
		User user = new User();
		user.setName("user1");
		dataMap.put("user1", user);
		user = new User();
		user.setName("用户2");
		dataMap.put("user2", user);

		String str = FreeMarkerUtils.loadTemplateContent(dataMap, templateName);
		System.out.println(str);
	}
	
	@Test
	public void testCreateTemplate() throws Exception
	{
		String templateName = "index.ftl";
		String targetHtmlPath = "C:\\Users\\Administrator\\Desktop\\index333333.html";
		Map<String, Object> dataMap = Maps.newHashMap();
		
		User user = new User();
		user.setName("user1");
		dataMap.put("user1", user);
		user = new User();
		user.setName("用户2");
		dataMap.put("user2", user);
		
		FreeMarkerUtils.createTemplate(templateName, targetHtmlPath, dataMap);
	}
}
