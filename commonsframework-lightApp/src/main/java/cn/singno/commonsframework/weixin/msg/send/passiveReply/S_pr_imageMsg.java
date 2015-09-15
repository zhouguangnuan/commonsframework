package cn.singno.commonsframework.weixin.msg.send.passiveReply;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.singno.commonsframework.weixin.msg.send.SendMsg;

/**
 * <p>名称：S_pr_imageMsg.java</p>
 * <p>描述：回复图片消息</p>
 * <pre>
 *    被动回复用户消息
 * </pre>
 * @author 周光暖
 * @date 2015-5-27 上午10:20:41
 * @version 1.0.0
 */
public class S_pr_imageMsg extends SendMsg {

	private String mediaId;// 通过上传多媒体文件，得到的id。
	
	@Override
	public Document toDocument() {
		Document doc = super.toDocument();
		Element xml = doc.getRootElement();
		Element Image = xml.addElement("Image");
		Image.addElement("MediaId").setText("<![CDATA[" + getMediaId() + "]]>");
		return doc;
	}

	public String getMediaId()
	{
		return mediaId;
	}

	public void setMediaId(String mediaId)
	{
		this.mediaId = mediaId;
	}
}
