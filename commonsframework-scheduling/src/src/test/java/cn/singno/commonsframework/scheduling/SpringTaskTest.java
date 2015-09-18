package cn.singno.commonsframework.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTaskTest
{
	@Scheduled(cron = "* 0/10 * * * ? ")// 每十分钟 
	public synchronized void job() throws Exception {
		System.out.println("===================== 执行调度任务（" + System.currentTimeMillis() + "） =====================");
	}
}