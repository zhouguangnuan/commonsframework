/**<p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.bean</p>
 * <p>文件名：MyQuartzSessionValidationScheduler.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-3-27-下午4:34:31</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.bean;

import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**<p>名称：MyQuartzSessionValidationScheduler.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2015-3-27 下午4:34:31
 * @version 1.0.0
 */
public class MyQuartzSessionValidationScheduler extends QuartzSessionValidationScheduler
{
	private SchedulerFactoryBean schedulerFactoryBean;
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler#getScheduler()
	 */
	@Override
	protected Scheduler getScheduler() throws SchedulerException
	{
		return schedulerFactoryBean.getScheduler();
	}

	/**
	 * @return the schedulerFactoryBean
	 */
	public SchedulerFactoryBean getSchedulerFactoryBean()
	{
		return schedulerFactoryBean;
	}

	/**
	 * @param schedulerFactoryBean the schedulerFactoryBean to set
	 */
	public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean)
	{
		this.schedulerFactoryBean = schedulerFactoryBean;
	}
}
