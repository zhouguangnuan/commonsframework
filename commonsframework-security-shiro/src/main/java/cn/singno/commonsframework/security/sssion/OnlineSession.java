/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.security.sssion</p>
 * <p>文件名：OnlineSession.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-28-上午10:38:27</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.security.sssion;

import org.apache.shiro.session.mgt.SimpleSession;

/**<p>名称：OnlineSession.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-28 上午10:38:27
 * @version 1.0.0
 */
public class OnlineSession extends SimpleSession {
	public static enum OnlineStatus
	{
		on_line("在线"), hidden("隐身"), force_logout("强制退出");
		private final String info;

		private OnlineStatus(String info)
		{
			this.info = info;
		}

		public String getInfo()
		{
			return info;
		}
	}

	private String userAgent; // 用户浏览器类型
	private OnlineStatus status = OnlineStatus.on_line; // 在线状态
	private String systemHost; // 用户登录时系统IP

	public String getUserAgent()
	{
		return userAgent;
	}

	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}

	public OnlineStatus getStatus()
	{
		return status;
	}
	
	public void setStatus(OnlineStatus status)
	{
		this.status = status;
	}
	
	public String getSystemHost()
	{
		return systemHost;
	}
	
	public void setSystemHost(String systemHost)
	{
		this.systemHost = systemHost;
	}
}
