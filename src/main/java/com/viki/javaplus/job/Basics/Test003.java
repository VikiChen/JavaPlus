package com.viki.javaplus.job.Basics;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test003 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                // task to run goes here
                System.out.println("Hello !!");
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);

    }
}
