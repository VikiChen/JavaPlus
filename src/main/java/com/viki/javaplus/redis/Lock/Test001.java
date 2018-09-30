package com.viki.javaplus.redis.Lock;

public class Test001 {
    public static void main(String[] args) {
        TestService service = new TestService();
        for (int i = 0; i < 300; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }

}
