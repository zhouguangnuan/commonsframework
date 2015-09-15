package cn.singno.commonsframework.weixin.msg.receive.event;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_e_subscribeMsg.java</p>
 * <p>描述：取消关注事件</p>
 * <pre>
 *    接收事件推送
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:57:56
 * @version 1.0.0
 */
public class R_e_unsubscribeMsg extends ReceiveMsg {
	private String event;// unsubscribe(取消订阅)

	public String getEvent()
	{
		return event;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}
}
