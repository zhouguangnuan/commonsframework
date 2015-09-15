package cn.singno.commonsframework.task;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.singno.commonsframework.module.app.dao.RoleDao;
import cn.singno.commonsframework.module.app.entity.Role;
import cn.singno.commonsframework.utils.SpringUtils;

public class QuartTestTask extends QuartzJobBean{  
	
	private static final String timeFormate = "yyyy-MM-dd mm:hh:ss";

	@Autowired
	private RoleDao roleDao;

	protected synchronized void executeInternal(JobExecutionContext context) throws JobExecutionException
	{

		roleDao = SpringUtils.getBean(RoleDao.class);
		Role role = new Role();
		role.setName("admin");
		role.setDescription("超級管理員");
		roleDao.save(role);

		System.out.println("===================== 执行调度任务（"
				+ DateTime.now().toString(timeFormate)
				+ "） =====================");
	}
}