package cn.singno.commonsframework.weixin.msg.receive.event;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_e_subscribeMsg.java</p>
 * <p>描述：上报地理位置事件</p>
 * <pre>
 *    接收事件推送
 *    
 *    用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置。
 *    上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:57:56
 * @version 1.0.0
 */
public class R_e_reportLocationMsg extends ReceiveMsg {
	private String event;// 事件类型（LOCATION）
	private Double latitude;// 地理位置纬度
	private Double longitude;// 地理位置经度
	private Double Precision;// 地理位置精度
	
	public String getEvent()
	{
		return event;
	}
	public void setEvent(String event)
	{
		this.event = event;
	}
	public Double getLatitude()
	{
		return latitude;
	}
	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}
	public Double getLongitude()
	{
		return longitude;
	}
	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}
	public Double getPrecision()
	{
		return Precision;
	}
	public void setPrecision(Double precision)
	{
		Precision = precision;
	}
}
