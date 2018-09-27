package com.viki.javaplus.job.Quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class App {
    public static void main(String[] args) throws SchedulerException {

        // 1.创建Scheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();
        // 2.从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();

        // 3.创建JobDetail
        JobDetail jb = JobBuilder.newJob(MyJob.class).withDescription("this is a ram job") // job的描述
                .withIdentity("ramJob", "ramGroup") // job 的name和group
                .build();

        // 任务运行的时间，SimpleSchedle类型触发器有效
        long time = System.currentTimeMillis() + 3 * 1000L; // 3秒后启动任务
        Date statTime = new Date(time);

        // 4.创建Trigger
        // 使用SimpleScheduleBuilder或者CronScheduleBuilder
        Trigger t = TriggerBuilder.newTrigger().withDescription("").withIdentity("ramTrigger", "ramTriggerGroup")
                // .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .startAt(statTime) // 默认当前时间启动
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")) // 两秒执行一次
                .build();

        // 5.注册任务和定时器
        scheduler.scheduleJob(jb, t);

        // 6.启动 调度器
        scheduler.start();

    }
}
