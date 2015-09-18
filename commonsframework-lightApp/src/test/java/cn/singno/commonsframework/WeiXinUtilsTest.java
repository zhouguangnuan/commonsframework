/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework</p>
 * <p>文件名：WeiXinUtilsTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-27-下午1:05:28</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework;


import org.apache.log4j.Logger;
import org.junit.Test;

import cn.singno.commonsframework.utils.WeiXinUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>名称：WeiXinUtilsTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-5-27 下午1:05:28
 * @version 1.0.0
 */
public class WeiXinUtilsTest
{
	
	@Test
	public void testParse() throws Exception
	{
//		String xml = " <xml> <ToUserName><![CDATA[toUser]]></ToUserName> <FromUserName><![CDATA[fromUser]]></FromUserName>  <CreateTime>1348831860</CreateTime> <MsgType>event</MsgType> <Content><![CDATA[this is a test]]></Content> <MsgId>1234567890123456</MsgId> </xml>";
//		InputStream in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
//		ReceiveMsg receiveMsg = WeiXinUtils.parse(in);
//		System.out.println(receiveMsg.getMsgType());
	}
	
	@Test
	public void testToXmlStr() throws Exception
	{
//		S_pr_textMsg msg = new S_pr_textMsg();
//		msg.setContent("content");
//		msg.setCreateTime(new Date().getTime());
//		msg.setFromUserName("fromUserName");
//		msg.setMsgType("msgType");
//		msg.setToUserName("toUserName");
//		
//		String xmlStr = WeiXinUtils.toXmlStr(msg);
//		System.out.println(xmlStr);
	}
	
	@Test
	public void testGetAccessToken() throws Exception {
		System.out.println(WeiXinUtils.getAccessToken());
	}
	
	@Test
	public void testGetAuthToken() throws Exception 
	{
		String code = "031ac79df644d47b07d66fa2268d697e"; 
		JSONObject jsonObject = WeiXinUtils.getAuthToken(code);
		System.out.println(jsonObject.toString());
	}
	
	@Test
	public void testGetUserinfo() throws Exception {
		String access_token = "OezXcEiiBSKSxW0eoylIeOYkSU4hgXa-HbNLTsIHnBhDXGtQ9E6BUsRwawqijxf4rSvdGAXUj07f6STxXD13kwbypn2MwuLZ24cW9XWYuTGzYqj_zjmQIrOHz6Iu5We58E45934SPvozL7P9AVErIA"; 
		String openid = "oJ6pEw7ML27fvjNuSEAncgyh1f70"; 
		JSONObject jsonObject = WeiXinUtils.getUserInfo(access_token, openid);
		System.out.println(jsonObject.toString()); 
	}
	
	@Test
	public void testname() throws Exception {
		String scene_str = "abc123";
		String ticket = WeiXinUtils.creaetSceneQRcode(scene_str);
		System.out.println(ticket); 
	}
}
