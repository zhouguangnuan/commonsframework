package cn.singno.commonsframework.weixin.msg.receive.common;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;


/**
 * <p>名称：R_c_textMsg.java</p>
 * <p>描述：文本消息</p>
 * <pre>
 *    接收普通消息
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午6:01:28
 * @version 1.0.0
 */
public class R_c_textMsg extends ReceiveMsg {
	private String content;// 文本消息内容

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}
