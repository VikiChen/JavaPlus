package com.viki.javaplus.job.Basics;

import java.util.Timer;
import java.util.TimerTask;

public class Test002 {
    static  int  count=0;
    public static void main(String[] args) {
        TimerTask timerTask=	new TimerTask() {

            @Override
            public void run() {
                //执行任务代码
                System.out.println("我是第"+(++count));
            }
        };

        Timer timer = new Timer();
        // 天数
        long delay = 0;
        // 秒数
        long period = 1000;
        timer.scheduleAtFixedRate(timerTask, delay, period);

    }

}
