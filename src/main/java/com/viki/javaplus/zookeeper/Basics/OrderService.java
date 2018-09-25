package com.viki.javaplus.zookeeper.Basics;

import com.viki.javaplus.zookeeper.Lock.ZookeeperDistrbuteLock;

//这个写法会有很多漏洞
public class OrderService implements Runnable {

   OrderNumGenerator orderNumGenerator= new OrderNumGenerator();
   //使用重入锁
  // private Lock lock=new ReentrantLock();
    //使用自定义分布式锁
    private com.viki.javaplus.zookeeper.Lock.Lock lock =new ZookeeperDistrbuteLock();
    @Override
    public void run() {
//        synchronized (this){
//            //模拟用户生成订单
//            getNumber();
////        }

//        lock.lock();
//        try {
//            getNumber();
//        } finally {
//            lock.unlock();
//        }
        try {
            lock.getLock();
            getNumber();
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            lock.unLock();
        }
    }

    public void getNumber(){
        String number =orderNumGenerator.getNumber();
        System.out.println(Thread.currentThread().getName()+"生成唯一订单号number："+number);

    }

    public static void main(String[] args) {
        System.out.println("模拟生成订单号开始");
        for (int i=0;i<100;i++){
            OrderService orderService=new OrderService();
            new Thread(orderService).start();
        }
    }
}
