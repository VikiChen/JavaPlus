package com.viki.javaplus.redis.Lock;


public class ThreadA extends Thread {
    private TestService service;

    public ThreadA(TestService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}

