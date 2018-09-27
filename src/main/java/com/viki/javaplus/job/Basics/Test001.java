package com.viki.javaplus.job.Basics;

public class Test001 {
    static  int  count=0;
    public static void main(String[] args) {
        //思考 实现定时任务，
        new Thread(new Runnable() {

            @Override
            public void run() {
                //监听任务调度
                while (true) {
                    // 每隔一秒时间实现定时任务
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    System.out.println("我是第"+(++count));
                }
            }
        }).start();
    }

}

