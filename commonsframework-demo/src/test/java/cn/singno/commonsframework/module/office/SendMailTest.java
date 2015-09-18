/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.module.office</p>
 * <p>文件名：SendMailTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-31-下午10:23:59</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.module.office;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import cn.singno.commonsframework.generic.GenericTest;
import cn.singno.commonsframework.utils.SendMailHelper;

/**<p>名称：SendMailTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-31 下午10:23:59
 * @version 1.0.0
 */
public class SendMailTest extends GenericTest
{
	@Autowired
	private SendMailHelper sendMailHelper;
	
	private String subject = "邮件主题";
	
	private String to = "singno_java@126.com";
	
	private String[] tos = new String[]{"singno_java@126.com", "375185528@qq.com"};
	
	private String text = "<font color='red'>一二三四五六七八九十！</font>";
	
	private boolean html = true;
	
	private File file=new File("C:\\Users\\Administrator\\Desktop\\思路.txt");
	
	@Test
	public void testname1() throws Exception
	{
		sendMailHelper.SendMail(subject, to, text, html, file);
	}
	
	@Test
	public void testname2() throws Exception
	{
		sendMailHelper.SendMail(subject, tos, text, file);
	}
	
	@Test
	public void testname3() throws Exception
	{
		MimeMessageHelper helper = sendMailHelper.buildMimeMessageHelper();
		helper.setSubject("测试3");
		helper.setTo(tos);
		helper.setText(text);
		sendMailHelper.send();
	}
}
