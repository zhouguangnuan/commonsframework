/*
 * @(#)MultipartControllerTest.java 2015-3-2 上午10:55:45
 * Copyright 2015 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.module.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.ResultActions;

import cn.singno.commonsframework.generic.GenericControllerTest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * <p>File：MultipartControllerTest.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-2 上午10:55:45</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class MultipartControllerTest extends GenericControllerTest
{
	@Test
	public void testUpload() throws Exception
	{
		String url = "/multipart/upload";
		
		Map<String, Object> params = Maps.newHashMap();
		
		Map<String, List<File>> files = Maps.newHashMap();
		
		List<File> list = Lists.newArrayList();
		list.add(FileUtils.getFile("C:\\Users\\txsb\\Desktop\\huitongkuaidi.doc"));
		files.put("file", list);
		
		ResultActions resultActions = super.getResultActions(url, params, HttpMethod.POST);
		logger.debug(resultActions.andReturn().getResponse().getContentAsString());
	}
}
