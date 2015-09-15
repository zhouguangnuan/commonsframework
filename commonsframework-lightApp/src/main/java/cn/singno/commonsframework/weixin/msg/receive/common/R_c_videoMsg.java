package cn.singno.commonsframework.weixin.msg.receive.common;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_c_videoMsg.java</p>
 * <p>描述：视频消息</p>
 * <pre>
 *    接收普通消息
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午6:05:03
 * @version 1.0.0
 */
public class R_c_videoMsg extends ReceiveMsg {
	private String mediaId;// 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
	private String ThumbMediaId;// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	
	public String getMediaId()
	{
		return mediaId;
	}
	public void setMediaId(String mediaId)
	{
		this.mediaId = mediaId;
	}
	public String getThumbMediaId()
	{
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId)
	{
		ThumbMediaId = thumbMediaId;
	}
}
