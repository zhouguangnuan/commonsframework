package cn.singno.commonsframework.weixin.msg.receive;

import cn.singno.commonsframework.weixin.msg.WxMsg;

/**
 * <p>名称：ReceiveMsg.java</p>
 * <p>描述：微信接收消息抽象类</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:40:32
 * @version 1.0.0
 */
public abstract class ReceiveMsg extends WxMsg {
	private String msgId; // 消息id
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
