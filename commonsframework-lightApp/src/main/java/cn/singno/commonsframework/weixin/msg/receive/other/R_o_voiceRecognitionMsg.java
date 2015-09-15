package cn.singno.commonsframework.weixin.msg.receive.other;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_e_subscribeMsg.java</p>
 * <p>描述：接收语音识别结果</p>
 * <pre>
 *    接收语音识别结果
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:57:56
 * @version 1.0.0
 */
public class R_o_voiceRecognitionMsg extends ReceiveMsg {
	private String msgID;// 消息id，64位整型
	private String mediaID;// 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
	private String format;// 语音格式：amr
	private String recognition;// 语音识别结果，UTF8编码
	
	public String getMsgID()
	{
		return msgID;
	}
	public void setMsgID(String msgID)
	{
		this.msgID = msgID;
	}
	public String getMediaID()
	{
		return mediaID;
	}
	public void setMediaID(String mediaID)
	{
		this.mediaID = mediaID;
	}
	public String getFormat()
	{
		return format;
	}
	public void setFormat(String format)
	{
		this.format = format;
	}
	public String getRecognition()
	{
		return recognition;
	}
	public void setRecognition(String recognition)
	{
		this.recognition = recognition;
	}
}
