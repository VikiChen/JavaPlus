package com.viki.javaplus.zookeeper.Lock;


import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

//重构重复代码  重复代码交给子类执行
public abstract class ZookeeperAbstractLock implements Lock {
    //zk连接地址
    private static  final  String CONNECTSTRING="127.0.0.1:2181";
    //创建zk 连接
    protected ZkClient zkClient=new ZkClient(CONNECTSTRING);
    protected static final String PATH="/lock";
    protected CountDownLatch countDownLatch=null;
    @Override
    public void getLock() {
        if(tryLock()){
            System.out.println("获取锁成功");
        }else {
            //等待
            waitLock();
            //重新获取锁
            getLock();
        }
    }

    //是否获取锁成功 成功返回true 失败返回false
    abstract Boolean tryLock();
    abstract void waitLock();
    @Override
    public void unLock() {
        if (zkClient!=null){
            zkClient.close();
            System.out.println("关闭连接");
        }
    }
}
