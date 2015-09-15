package cn.singno.commonsframework.weixin.msg.receive.common;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_c_imageMsg.java</p>
 * <p>描述：图片消息</p>
 * <pre>
 *     接收普通消息
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:59:58
 * @version 1.0.0
 */
public class R_c_imageMsg extends ReceiveMsg {
	private String picUrl;// 图片链接
	private String mediaId;// 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
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
