package com.quartz.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.quartz.job.HelloJob;

public class TestQuartz {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		
//		定义jobdetail
		JobDetail jb = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myjob", "group1")
				.usingJobData("message", "hello,Myjobdetail")
				.build();
		
		System.out.println("jobDetail's name:  "+jb.getKey().getName());
		System.out.println("jobDetail's group:  "+jb.getKey().getGroup());
		System.out.println("jobDetail's jobclass:  "+jb.getJobClass().getName());
		
//		定义trigger
		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("mytrigger","group1")
				.startNow()
				.usingJobData("math", 3.15f)
//				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
				.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
				.build();
		
//		创建scheduler
		StdSchedulerFactory ssf = new StdSchedulerFactory();
		Scheduler sc = ssf.getScheduler();
		sc.start();
		sc.scheduleJob(jb, trigger);
		
//		两秒挂起
		Thread.sleep(2000L);
		sc.standby();
		
		Thread.sleep(2000L);
		sc.start();
		
//		sc.shutdown(false); //直接关闭Scheduler
//		sc.shutdown(true);  //执行完所有的job后关闭Scheduler
		
		
	}
}
