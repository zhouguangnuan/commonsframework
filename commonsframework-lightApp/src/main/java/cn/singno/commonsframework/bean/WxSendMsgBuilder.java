package cn.singno.commonsframework.bean;

import java.util.Date;
import java.util.List;

import cn.singno.commonsframework.constants.WxConst;
import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;
import cn.singno.commonsframework.weixin.msg.send.SendMsg;
import cn.singno.commonsframework.weixin.msg.send.passiveReply.Item;
import cn.singno.commonsframework.weixin.msg.send.passiveReply.S_pr_imageMsg;
import cn.singno.commonsframework.weixin.msg.send.passiveReply.S_pr_musicMsg;
import cn.singno.commonsframework.weixin.msg.send.passiveReply.S_pr_newsMsg;
import cn.singno.commonsframework.weixin.msg.send.passiveReply.S_pr_textMsg;
import cn.singno.commonsframework.weixin.msg.send.passiveReply.S_pr_videoMsg;
import cn.singno.commonsframework.weixin.msg.send.passiveReply.S_pr_voiceMsg;

/**
 * 回复消息构建器
 */
public class WxSendMsgBuilder {
	
	/**
	 * 构建文本回复消息
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @return
	 */
	public static SendMsg buildTextMsg(String fromUserName, String toUserName, String content)
	{
		S_pr_textMsg textMsg = new S_pr_textMsg();
		textMsg.setFromUserName(fromUserName);
		textMsg.setToUserName(toUserName);
		textMsg.setContent(content);
		textMsg.setMsgType(WxConst.MsgType.text.name());
		textMsg.setCreateTime(new Date().getTime());
		return textMsg;
	}
	
	/**
	 * 构建文本回复消息
	 * @param receiveMsg
	 * @param content
	 * @return
	 */
	public static SendMsg buildTextMsg(ReceiveMsg receiveMsg, String content)
	{
		S_pr_textMsg textMsg = new S_pr_textMsg();
		textMsg.setFromUserName(receiveMsg.getToUserName());
		textMsg.setToUserName(receiveMsg.getFromUserName());
		textMsg.setContent(content);
		textMsg.setMsgType(WxConst.MsgType.text.name());
		textMsg.setCreateTime(new Date().getTime());
		return textMsg;
	}
	
	/**
	 * 构建图片回复消息
	 * @param fromUserName
	 * @param toUserName
	 * @param mediaId
	 * @return
	 */
	public static SendMsg buildImageMsg(String fromUserName, String toUserName, String mediaId)
	{ 
		S_pr_imageMsg imageMsg = new S_pr_imageMsg();
		imageMsg.setFromUserName(fromUserName);
		imageMsg.setToUserName(toUserName);
		imageMsg.setMediaId(mediaId);
		imageMsg.setMsgType(WxConst.MsgType.image.name());
		imageMsg.setCreateTime(new Date().getTime());
		return imageMsg;
	}
	
	/**
	 * 构建图片回复消息
	 * @param receiveMsg
	 * @param mediaId
	 * @return
	 */
	public static SendMsg buildImageMsg(ReceiveMsg receiveMsg, String mediaId)
	{ 
		S_pr_imageMsg imageMsg = new S_pr_imageMsg();
		imageMsg.setFromUserName(receiveMsg.getToUserName());
		imageMsg.setToUserName(receiveMsg.getFromUserName());
		imageMsg.setMediaId(mediaId);
		imageMsg.setMsgType(WxConst.MsgType.image.name());
		imageMsg.setCreateTime(new Date().getTime());
		return imageMsg;
	}
	
	/**
	 *  构建语音回复消息
	 * @param fromUserName
	 * @param toUserName
	 * @param mediaId
	 * @return
	 */
	public static SendMsg buildVoiceMsg(String fromUserName, String toUserName, String mediaId)
	{
		S_pr_voiceMsg voiceMsg = new S_pr_voiceMsg();
		voiceMsg.setFromUserName(fromUserName);
		voiceMsg.setToUserName(toUserName);
		voiceMsg.setMediaId(mediaId);
		voiceMsg.setMsgType(WxConst.MsgType.voice.name());
		voiceMsg.setCreateTime(new Date().getTime());
		return voiceMsg; 
	}
	
	/**
	 * 构建语音回复消息
	 * @param receiveMsg
	 * @param mediaId
	 * @return
	 */
	public static SendMsg buildVoiceMsg(ReceiveMsg receiveMsg, String mediaId)
	{ 
		S_pr_voiceMsg voiceMsg = new S_pr_voiceMsg();
		voiceMsg.setFromUserName(receiveMsg.getToUserName());
		voiceMsg.setToUserName(receiveMsg.getFromUserName());
		voiceMsg.setMediaId(mediaId);
		voiceMsg.setMsgType(WxConst.MsgType.voice.name());
		voiceMsg.setCreateTime(new Date().getTime());
		return voiceMsg;
	}
	
	/**
	 * 构建视频回复消息
	 * @param fromUserName
	 * @param toUserName
	 * @param mediaId
	 * @param title			视频消息的标题      
	 * @param description	视频消息的描述  
	 * @return
	 */
	public static SendMsg buildVideoMsg(String fromUserName, String toUserName, String mediaId, String title, String description)
	{ 
		S_pr_videoMsg videoMsg = new S_pr_videoMsg();
		videoMsg.setFromUserName(fromUserName);
		videoMsg.setToUserName(toUserName);
		videoMsg.setMediaId(mediaId);
		videoMsg.setTitle(title);
		videoMsg.setDescription(description);
		videoMsg.setMsgType(WxConst.MsgType.video.name());
		videoMsg.setCreateTime(new Date().getTime());
		return videoMsg;
	}
	
	/**
	 * 构建视频回复消息
	 * @param receiveMsg
	 * @param mediaId
	 * @param title			视频消息的标题      
	 * @param description	视频消息的描述  
	 * @return
	 */
	public static SendMsg buildVideoMsg(ReceiveMsg receiveMsg, String mediaId, String title, String description)
	{ 
		S_pr_videoMsg videoMsg = new S_pr_videoMsg();
		videoMsg.setFromUserName(receiveMsg.getToUserName());
		videoMsg.setToUserName(receiveMsg.getFromUserName());
		videoMsg.setMediaId(mediaId);
		videoMsg.setTitle(title);
		videoMsg.setDescription(description);
		videoMsg.setMsgType(WxConst.MsgType.video.name());
		videoMsg.setCreateTime(new Date().getTime());
		return videoMsg;
	}
	
	/**
	 * 构建音乐回复消息
	 * @param fromUserName
	 * @param toUserName
	 * @param title				音乐标题    
	 * @param description		音乐描述      
	 * @param musicURL			音乐链接       
	 * @param hQMusicUrl		高质量音乐链接，WIFI环境优先使用该链接播放音乐   
	 * @param thumbMediaId 		缩略图的媒体id，通过上传多媒体文件，得到的id 
	 * @return
	 */
	public static SendMsg buildMusicMsg(String fromUserName, String toUserName, String title, 
			String description, String musicURL, String hQMusicUrl, String thumbMediaId)
	{ 
		S_pr_musicMsg musicMsg = new S_pr_musicMsg();
		musicMsg.setFromUserName(fromUserName);
		musicMsg.setToUserName(toUserName);
		musicMsg.setTitle(title);
		musicMsg.setDescription(description);
		musicMsg.setMusicURL(hQMusicUrl);
		musicMsg.sethQMusicUrl(hQMusicUrl);
		musicMsg.setThumbMediaId(thumbMediaId);
		musicMsg.setMsgType(WxConst.MsgType.music.name());
		musicMsg.setCreateTime(new Date().getTime());
		return musicMsg;
	}
	
	/**
	 * 构建音乐回复消息
	 * @param receiveMsg
	 * @param title				音乐标题    
	 * @param description		音乐描述      
	 * @param musicURL			音乐链接       
	 * @param hQMusicUrl		高质量音乐链接，WIFI环境优先使用该链接播放音乐   
	 * @param thumbMediaId 		缩略图的媒体id，通过上传多媒体文件，得到的id 
	 * @return
	 */
	public static SendMsg buildMusicMsg(ReceiveMsg receiveMsg, String title, 
			String description, String musicURL, String hQMusicUrl, String thumbMediaId)
	{ 
		S_pr_musicMsg musicMsg = new S_pr_musicMsg();
		musicMsg.setFromUserName(receiveMsg.getToUserName());
		musicMsg.setToUserName(receiveMsg.getFromUserName());
		musicMsg.setTitle(title);
		musicMsg.setDescription(description);
		musicMsg.setMusicURL(hQMusicUrl);
		musicMsg.sethQMusicUrl(hQMusicUrl);
		musicMsg.setThumbMediaId(thumbMediaId);
		musicMsg.setMsgType(WxConst.MsgType.music.name());
		musicMsg.setCreateTime(new Date().getTime());
		return musicMsg;
	}
	
	/**
	 * 构建图文回复消息
	 * @param fromUserName
	 * @param toUserName
	 * @param articleCoun		图文消息个数，限制为10条以内
	 * @param articles			多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	 * @return
	 */
	public static SendMsg buildNewsMsg(String fromUserName, String toUserName, Integer articleCount, List<Item> articles)
	{  
		S_pr_newsMsg newsMsg = new S_pr_newsMsg();
		newsMsg.setFromUserName(fromUserName);
		newsMsg.setToUserName(toUserName);
		newsMsg.setArticleCount(articleCount);
		newsMsg.setArticles(articles);
		newsMsg.setMsgType(WxConst.MsgType.news.name());
		newsMsg.setCreateTime(new Date().getTime());
		return newsMsg;
	}
	
	/**
	 * 构建图文回复消息
	 * @param receiveMsg
	 * @param articleCoun		图文消息个数，限制为10条以内
	 * @param articles			多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	 * @return
	 */
	public static SendMsg buildNewsMsg(ReceiveMsg receiveMsg, Integer articleCount, List<Item> articles)
	{  
		S_pr_newsMsg newsMsg = new S_pr_newsMsg();
		newsMsg.setFromUserName(receiveMsg.getToUserName());
		newsMsg.setToUserName(receiveMsg.getFromUserName());
		newsMsg.setArticleCount(articleCount);
		newsMsg.setArticles(articles);
		newsMsg.setMsgType(WxConst.MsgType.news.name());
		newsMsg.setCreateTime(new Date().getTime());
		return newsMsg;
	}
}
