/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.security.listener</p>
 * <p>文件名：MySessionListener.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-28-上午10:09:23</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.security.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**<p>名称：MySessionListener.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-28 上午10:09:23
 * @version 1.0.0
 */
public class MySessionListener extends SessionListenerAdapter
{
	/* (non-Javadoc)
	 * @see org.apache.shiro.session.SessionListenerAdapter#onStart(org.apache.shiro.session.Session)
	 */
	@Override
	public void onStart(Session session)
	{
		System.out.println("会话创建：" + session.getId());
	}
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.session.SessionListenerAdapter#onStop(org.apache.shiro.session.Session)
	 */
	@Override
	public void onStop(Session session)
	{
		System.out.println("会话过期：" + session.getId());
	}
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.session.SessionListenerAdapter#onExpiration(org.apache.shiro.session.Session)
	 */
	@Override
	public void onExpiration(Session session)
	{
		System.out.println("会话停止：" + session.getId());
	}
}
