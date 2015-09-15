package cn.singno.commonsframework.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.CharEncoding;
import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;

/**
 * <p>名称：SendMailHelper.java</p>
 * <p>描述：发送邮件的帮助类</p>
 * <pre>
 *    封装简化了邮件发送的aip调用
 * </pre>
 * @author 周光暖
 * @date 2015-4-6 上午11:14:26
 * @version 1.0.0
 */
public class SendMailHelper
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SendMailHelper.class);

	private static final String CHARSET = CharEncoding.UTF_8;

	private JavaMailSenderImpl mailSender;
	
	private MimeMessage message;
	
	private MimeMessageHelper helper;

	/**
	 * 构造器
	 */
	public SendMailHelper(JavaMailSenderImpl mailSender)
	{
		Assert.notNull(mailSender, "JavaMailSenderImpl not be null");
		this.mailSender = mailSender;
	}
	
	/**
	 * <p>描述：构建MimeMessageHelper</p>
	 * <pre>
	 *    通过调用 MimeMessageHelper 的方法，完成邮件相关设置。
	 * </pre>
	 * @return
	 * @throws MessagingException
	 */
	public MimeMessageHelper buildMimeMessageHelper() throws MessagingException
	{
		message = mailSender.createMimeMessage();
		helper = new MimeMessageHelper(message, true, CHARSET);
		helper.setFrom(mailSender.getUsername());
		return helper;
	}
	
	/**
	 * <p>描述：发送email</p>
	 */
	public void send()
	{
		mailSender.send(message);
	}
	
	/**
	 * 获取附件名称（主要是对中文的支持）
	 * 
	 * @param fileName 	附件名
	 * @return Stirng 		处理后的附件名（主要是对中文的支持）
	 */
	public String getFileName(String fileName)
	{
		// 附件名是否包含中文
		if (StringUtils.isContainsChinese(fileName))
		{
			try
			{
				fileName = MimeUtility.encodeWord(fileName);
			} catch (UnsupportedEncodingException e)
			{
				logger.error(e);
			}
		}
		return fileName;
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject		邮件主题
	 * @param to			收件人（singno_java@126.com）
	 * @param text		邮件内容
	 * @param file		邮件附件（保留原始附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String to, String text, File file) throws MessagingException
	{
		this.SendMail(subject, to, text, false, file);
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人（singno_java@126.com）
	 * @param text					邮件内容
	 * @param attachmentFilename		附件文件名
	 * @param file					邮件附件（指定附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String to, String text, String attachmentFilename, File file) throws MessagingException
	{
		this.SendMail(subject, to, text, false, attachmentFilename, file);
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人（singno_java@126.com）
	 * @param text					邮件内容
	 * @param attachmentFilename		附件文件名
	 * @param inputStreamSource		邮件附件输入流（指定附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String to, String text, String attachmentFilename, InputStreamSource inputStreamSource) throws MessagingException
	{
		this.SendMail(subject, to, text, false, attachmentFilename, inputStreamSource);
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人（singno_java@126.com）
	 * @param text					邮件内容
	 * @param html					是否是html（true：标签体被解析，false：标签体做文本显示）
	 * @param file					邮件附件（保留原始附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String to, String text, boolean html, File file) throws MessagingException
	{
		helper = this.buildMimeMessageHelper();
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(text, html);
		helper.addAttachment(this.getFileName(file.getName()), file);
		this.send();
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人（singno_java@126.com）
	 * @param text					邮件内容
	 * @param html					是否是html（true：标签体被解析，false：标签体做文本显示）
	 * @param attachmentFilename		附件文件名
	 * @param file					邮件附件（指定附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String to, String text, boolean html, String attachmentFilename, File file) throws MessagingException
	{
		helper = this.buildMimeMessageHelper();
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(text, html);
		helper.addAttachment(this.getFileName(attachmentFilename), file);
		this.send();
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人（singno_java@126.com）
	 * @param text					邮件内容
	 * @param html					是否是html（true：标签体被解析，false：标签体做文本显示）
	 * @param attachmentFilename		附件文件名
	 * @param inputStreamSource		邮件附件输入流（指定附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String to, String text, boolean html, String attachmentFilename, InputStreamSource inputStreamSource) throws MessagingException
	{
		helper = this.buildMimeMessageHelper();
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(text, html);
		helper.addAttachment(this.getFileName(attachmentFilename), inputStreamSource);
		this.send();
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人组（singno_java@126.com, xxx@mail.com）
	 * @param text					邮件内容
	 * @param file					邮件附件（保留原始附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String[] to, String text, File file) throws MessagingException
	{
		this.SendMail(subject, to, text, false, file);
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人组（singno_java@126.com, xxx@mail.com）
	 * @param text					邮件内容
	 * @param attachmentFilename		附件文件名
	 * @param file					邮件附件（指定附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String[] to, String text, String attachmentFilename, File file) throws MessagingException
	{
		this.SendMail(subject, to, text, false, attachmentFilename, file);
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人组（singno_java@126.com, xxx@mail.com）
	 * @param text					邮件内容
	 * @param attachmentFilename		附件文件名
	 * @param inputStreamSource		邮件附件输入流（指定附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String[] to, String text, String attachmentFilename, InputStreamSource inputStreamSource) throws MessagingException
	{
		this.SendMail(subject, to, text, false, attachmentFilename, inputStreamSource);
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人组（singno_java@126.com, xxx@mail.com）
	 * @param text					邮件内容
	 * @param html					是否是html（true：标签体被解析，false：标签体做文本显示）
	 * @param file					邮件附件（保留原始附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String[] to, String text, boolean html, File file) throws MessagingException
	{
		helper = this.buildMimeMessageHelper();
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(text, html);
		helper.addAttachment(this.getFileName(file.getName()), file);
		this.send();
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人组（singno_java@126.com, xxx@mail.com）
	 * @param text					邮件内容
	 * @param html					是否是html（true：标签体被解析，false：标签体做文本显示）
	 * @param attachmentFilename		附件文件名
	 * @param file					邮件附件（保留原始附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String[] to, String text, boolean html, String attachmentFilename, File file) throws MessagingException
	{
		helper = this.buildMimeMessageHelper();
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(text, html);
		helper.addAttachment(this.getFileName(attachmentFilename), file);
		this.send();
	}
	
	/**
	 * <p>描述：发送email</p>
	 * @param subject					邮件主题
	 * @param to						收件人组（singno_java@126.com, xxx@mail.com）
	 * @param text					邮件内容
	 * @param html					是否是html（true：标签体被解析，false：标签体做文本显示）
	 * @param attachmentFilename		附件文件名
	 * @param inputStreamSource		邮件附件输入流（指定附件名）
	 * @throws MessagingException
	 */
	public void SendMail(String subject, String[] to, String text, boolean html, String attachmentFilename, InputStreamSource inputStreamSource) throws MessagingException
	{
		helper = this.buildMimeMessageHelper();
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(text, html);
		helper.addAttachment(this.getFileName(attachmentFilename), inputStreamSource);
		this.send();
	}
}
