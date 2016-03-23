package cn.singno.commonsframework.task;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTaskTest2
{
//	@Scheduled(cron = "0/1 * * * * ? ")// 每十分钟 
	public synchronized void job() throws Exception {
		System.out.println("===================== 执行调度任务（" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")  + "） =====================");
	}
}