package cn.singno.commonsframework.weixin.msg.send.passiveReply;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.singno.commonsframework.weixin.msg.send.SendMsg;

/**
 * <p>名称：S_pr_textMsg.java</p>
 * <p>描述：回复文本消息</p>
 * <pre>
 *    被动回复用户消息
 * </pre>
 * @author 周光暖
 * @date 2015-5-27 上午10:20:41
 * @version 1.0.0
 */
public class S_pr_textMsg extends SendMsg {

	private String content;//	回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	
	@Override
	public Document toDocument() {
		Document doc = super.toDocument();
		Element xml = doc.getRootElement();
		xml.addElement("Content").setText("<![CDATA[" + getContent() + "]]>");
		return doc;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}
