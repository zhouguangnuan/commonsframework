package cn.singno.commonsframework.weixin.msg;

/**
 * <p>名称：WxMsg.java</p>
 * <p>描述：微信消息抽象类</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-5-25 下午5:31:34
 * @version 1.0.0
 */
public abstract class WxMsg {
	private String toUserName;
	private String fromUserName;
	private String msgType; // 消息类型
	private Long createTime; // 消息创建时间 （整型）

	public String getToUserName()
	{
		return toUserName;
	}

	public void setToUserName(String toUserName)
	{
		this.toUserName = toUserName;
	}

	public String getFromUserName()
	{
		return fromUserName;
	}

	public void setFromUserName(String fromUserName)
	{
		this.fromUserName = fromUserName;
	}

	public String getMsgType()
	{
		return msgType;
	}

	public void setMsgType(String msgType)
	{
		this.msgType = msgType;
	}

	public Long getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}
}
