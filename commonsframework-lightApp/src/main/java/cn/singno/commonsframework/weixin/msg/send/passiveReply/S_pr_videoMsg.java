package cn.singno.commonsframework.weixin.msg.send.passiveReply;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.singno.commonsframework.weixin.msg.send.SendMsg;

/**
 * <p>名称：S_pr_videoMsg.java</p>
 * <p>描述：回复视频消息</p>
 * <pre>
 *    被动回复用户消息
 * </pre>
 * @author 周光暖
 * @date 2015-5-27 上午10:20:41
 * @version 1.0.0
 */
public class S_pr_videoMsg extends SendMsg {

	private String mediaId;// 通过上传多媒体文件，得到的id
	private String title;// 视频消息的标题
	private String description;// 视频消息的描述
	
	@Override
	public Document toDocument() {
		Document doc = super.toDocument();
		Element xml = doc.getRootElement();
		Element Video = xml.addElement("Video");
		Video.addElement("MediaId").setText("<![CDATA[" + getMediaId() + "]]>");
		Video.addElement("<![CDATA[" + "Title").setText(getTitle() + "]]>");
		Video.addElement("Description").setText("<![CDATA[" + getDescription() + "]]>");
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

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
