package cn.singno.commonsframework.weixin.msg.receive.event;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_e_subscribeMsg.java</p>
 * <p>描述：自定义菜单事件</p>
 * <pre>
 *    接收事件推送
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:57:56
 * @version 1.0.0
 */
public class R_e_customizeMenusMsg extends ReceiveMsg {
	
//	 点击菜单点击时的事件推送：CLICK
//	点击菜单跳转链接时的事件推送：VIEW
	private String event;// 事件类型
	
//	 点击菜单点击时的事件推送：与自定义菜单接口中KEY值对应
//	点击菜单跳转链接时的事件推送：设置的跳转URL
	private String eventKey;// 事件KEY值

	public String getEvent()
	{
		return event;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}

	public String getEventKey()
	{
		return eventKey;
	}

	public void setEventKey(String eventKey)
	{
		this.eventKey = eventKey;
	}
}
