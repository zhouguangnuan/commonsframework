/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework</p>
 * <p>文件名：WeiXinUtilsTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-27-下午1:05:28</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

import net.sf.json.JSONObject;

import org.junit.Test;

import cn.singno.commonsframework.utils.WeiXinUtils;
import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;
import cn.singno.commonsframework.weixin.msg.receive.common.R_c_textMsg;
import cn.singno.commonsframework.weixin.msg.send.passiveReply.S_pr_textMsg;

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
}
