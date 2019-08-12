package com.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

	/*
	 * set方法
	 * */
	private String message;
	
	private Float math;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Float getMath() {
		return math;
	}


	public void setMath(Float math) {
		this.math = math;
	}


	@Override
	public void execute(JobExecutionContext context)throws JobExecutionException {
		
		/*String mes = (String) context.getJobDetail().getJobDataMap().get("message");
		System.out.println("jobdetail's message:  "+mes);
		
		Float ms = (Float) context.getTrigger().getJobDataMap().get("math");
		System.out.println("trigger's math:  "+ms);*/
		
		
		System.out.println("jobdetail's message:  "+message);
		System.out.println("trigger's math:  "+math);
		System.out.println("业务逻辑处理中...");
	}
}
