package cn.singno.commonsframework.scheduling;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartTaskTest extends QuartzJobBean{  
	
	protected synchronized void executeInternal(JobExecutionContext context) throws JobExecutionException
	{
		System.out.println("===================== 执行调度任务（" + System.currentTimeMillis() + "） =====================");
	}
}