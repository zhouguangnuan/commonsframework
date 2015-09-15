package cn.singno.commonsframework.weixin.msg.send;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.singno.commonsframework.weixin.msg.WxMsg;

public abstract class SendMsg extends WxMsg {
	
	/**
	 * <p>描述：将消息bean转为Document</p>
	 * <pre>
	 *    
	 * </pre>
	 * @return
	 */
	public Document toDocument() {
		Document document = DocumentHelper.createDocument();
		Element xml = document.addElement("xml");
		xml.addElement("ToUserName").setText("<![CDATA[" + getToUserName() + "]]>");
		xml.addElement("FromUserName").setText("<![CDATA[" + getFromUserName() + "]]>");
		xml.addElement("MsgType").setText("<![CDATA[" + getMsgType() + "]]>");
		xml.addElement("CreateTime").setText(getCreateTime().toString());
		return document;
	}
}
