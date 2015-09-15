package cn.singno.commonsframework.weixin.msg.send.passiveReply;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.singno.commonsframework.weixin.msg.send.SendMsg;

/**
 * <p>名称：S_pr_musicMsg.java</p>
 * <p>描述：回复音乐消息</p>
 * <pre>
 *    被动回复用户消息
 * </pre>
 * @author 周光暖
 * @date 2015-5-27 上午10:20:41
 * @version 1.0.0
 */
public class S_pr_musicMsg extends SendMsg {

	private String title;// 音乐标题
	private String description;// 音乐描述
	private String musicURL;// 音乐链接
	private String hQMusicUrl;// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String thumbMediaId;// 缩略图的媒体id，通过上传多媒体文件，得到的id
	
	@Override
	public Document toDocument() {
		Document doc = super.toDocument();
		Element xml = doc.getRootElement();
		Element Music = xml.addElement("Music");
		Music.addElement("Title").setText("<![CDATA[" + getTitle() + "]]>");
		Music.addElement("Description").setText("<![CDATA[" + getDescription() + "]]>");
		Music.addElement("MusicUrl").setText("<![CDATA[" + getMusicURL() + "]]>");
		Music.addElement("HQMusicUrl").setText("<![CDATA[" + gethQMusicUrl() + "]]>");
		Music.addElement("ThumbMediaId").setText("<![CDATA[" + getThumbMediaId() + "]]>");
		return doc;
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

	public String getMusicURL()
	{
		return musicURL;
	}

	public void setMusicURL(String musicURL)
	{
		this.musicURL = musicURL;
	}

	public String gethQMusicUrl()
	{
		return hQMusicUrl;
	}

	public void sethQMusicUrl(String hQMusicUrl)
	{
		this.hQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId()
	{
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId)
	{
		this.thumbMediaId = thumbMediaId;
	}
}
