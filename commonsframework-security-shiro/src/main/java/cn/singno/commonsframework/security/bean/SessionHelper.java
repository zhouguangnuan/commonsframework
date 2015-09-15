/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.utils</p>
 * <p>文件名：SessionManagerUtils.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-28-上午11:13:19</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.security.bean;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;

/**<p>名称：SessionManagerUtils.java</p>
 * <p>描述：session帮助类</p>
 * <pre>
 *    获得sessionFactory创建的session
 * </pre>
 * @author 周光暖
 * @date 2015-3-28 上午11:13:19
 * @version 1.0.0
 */
public class SessionHelper
{
	private SessionDAO sessionDAO;
	
	public Session getSession()
	{
		return getSession(false);
	}
	
	public Session getSession(boolean create)
	{
		Subject subject = SecurityUtils.getSubject();
	        Session session = sessionDAO.readSession(subject.getSession(create).getId());
		return session;
	}

	public void setSessionDAO(SessionDAO sessionDAO)
	{
		this.sessionDAO = sessionDAO;
	}
}
