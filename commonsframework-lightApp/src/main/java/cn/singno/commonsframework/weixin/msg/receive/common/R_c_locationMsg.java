package cn.singno.commonsframework.weixin.msg.receive.common;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_c_locationMsg.java</p>
 * <p>描述：地理位置消息</p>
 * <pre>
 *    接收普通消息
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:52:27
 * @version 1.0.0
 */
public class R_c_locationMsg extends ReceiveMsg {
	private Double location_X;// 地理位置维度
	private Double location_Y;// 地理位置经度
	private Integer Scale;// 地图缩放大小
	private String Label;// 地理位置信息
	
	public Double getLocation_X()
	{
		return location_X;
	}
	public void setLocation_X(Double location_X)
	{
		this.location_X = location_X;
	}
	public Double getLocation_Y()
	{
		return location_Y;
	}
	public void setLocation_Y(Double location_Y)
	{
		this.location_Y = location_Y;
	}
	public Integer getScale()
	{
		return Scale;
	}
	public void setScale(Integer scale)
	{
		Scale = scale;
	}
	public String getLabel()
	{
		return Label;
	}
	public void setLabel(String label)
	{
		Label = label;
	}
}
