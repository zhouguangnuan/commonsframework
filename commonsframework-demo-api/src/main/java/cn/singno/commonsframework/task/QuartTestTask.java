package cn.singno.commonsframework.task;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartTestTask extends QuartzJobBean{  
	
	private static final String timeFormate = "yyyy-MM-dd mm:hh:ss";

	protected synchronized void executeInternal(JobExecutionContext context) throws JobExecutionException
	{
	        System.out.println("===================== 执行调度任务二 START =====================");
		
		for (int i = 0; i < 20; i++)
                {
		        System.out.println("===================== 执行调度任务二（" + 20 + "###" + DateTime.now().toString(timeFormate) + "） =====================");
                }
		
		System.out.println("===================== 执行调度任务二 END =====================");
	}
}