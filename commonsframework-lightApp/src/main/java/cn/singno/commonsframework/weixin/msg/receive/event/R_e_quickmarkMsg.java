package cn.singno.commonsframework.weixin.msg.receive.event;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_e_subscribeMsg.java</p>
 * <p>描述：扫描带参数二维码事件</p>
 * <pre>
 *    接收事件推送
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:57:56
 * @version 1.0.0
 */
public class R_e_quickmarkMsg extends ReceiveMsg {
	
//	用户未关注时，进行关注后的事件推送：subscribe
//	用户已关注时的事件推送：SCAN
	private String event;// 事件类型
	
//	用户未关注时，进行关注后的事件推送：qrscene_为前缀，后面为二维码的参数值
//	用户已关注时的事件推送：一个32位无符号整数，即创建二维码时的二维码scene_id（具体看微信api）
	private String eventKey;// 事件KEY值
	
	private String Ticket;// 二维码的ticket，可用来换取二维码图片
	
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
	public String getTicket()
	{
		return Ticket;
	}
	public void setTicket(String ticket)
	{
		Ticket = ticket;
	}
}
