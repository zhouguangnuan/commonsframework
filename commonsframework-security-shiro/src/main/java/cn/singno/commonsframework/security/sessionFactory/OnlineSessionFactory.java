/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.security.sessionFactory</p>
 * <p>文件名：OnlineSessionFactory.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-28-上午10:37:34</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.security.sessionFactory;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import cn.singno.commonsframework.security.sssion.OnlineSession;

/**<p>名称：OnlineSessionFactory.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-28 上午10:37:34
 * @version 1.0.0
 */
public class OnlineSessionFactory implements SessionFactory {
	
	@Override
	public Session createSession(SessionContext initData)
	{
		OnlineSession session = new OnlineSession();
		if (initData != null && initData instanceof WebSessionContext)
		{
			WebSessionContext sessionContext = (WebSessionContext) initData;
			HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
			if (request != null)
			{
				session.setUserAgent(request.getHeader("User-Agent"));
				session.setSystemHost(request.getLocalAddr() + ":" + request.getLocalPort());
			}
		}
		
		session.setAttribute("key", "sdfdsfdf");
		return session;
	}
}
