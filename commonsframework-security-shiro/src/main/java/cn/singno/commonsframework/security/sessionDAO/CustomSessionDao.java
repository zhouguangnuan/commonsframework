/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.security.sessionDAO</p>
 * <p>文件名：CustomSessionDao.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-4-26-下午10:04:05</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.security.sessionDAO;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

/**<p>名称：CustomSessionDao.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-4-26 下午10:04:05
 * @version 1.0.0
 */
public class CustomSessionDao extends EnterpriseCacheSessionDAO
{
	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO#doCreate(org.apache.shiro.session.Session)
	 */
	@Override
	protected Serializable doCreate(Session session)
	{
		System.out.println("doCreate：" + session.getId());
		return super.doCreate(session);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO#doDelete(org.apache.shiro.session.Session)
	 */
	@Override
	protected void doDelete(Session session)
	{
		System.out.println("doDelete：" + session.getId());
		super.doDelete(session);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO#doReadSession(java.io.Serializable)
	 */
	@Override
	protected Session doReadSession(Serializable sessionId)
	{
		System.out.println("doReadSession：" +sessionId);
		return super.doReadSession(sessionId);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO#doUpdate(org.apache.shiro.session.Session)
	 */
	@Override
	protected void doUpdate(Session session)
	{
		System.out.println("doUpdate：" + session.getId());
		super.doUpdate(session);
	}
}
