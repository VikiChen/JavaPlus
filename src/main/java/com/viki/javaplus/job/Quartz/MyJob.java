package com.viki.javaplus.job.Quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class MyJob implements Job {
    // 执行任务调度方法
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("quartz MyJob date:" + new Date().getTime());
    }

}

