package cn.singno.commonsframework.weixin.msg.receive.common;

import cn.singno.commonsframework.weixin.msg.receive.ReceiveMsg;

/**
 * <p>名称：R_c_linkMsg.java</p>
 * <p>描述：链接消息</p>
 * <pre>
 *    接收普通消息
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:57:56
 * @version 1.0.0
 */
public class R_c_linkMsg extends ReceiveMsg {
	private String title;// 消息标题
	private String description;// 消息描述
	private String url;// 消息链接
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
}
