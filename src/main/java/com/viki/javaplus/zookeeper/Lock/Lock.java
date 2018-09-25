package com.viki.javaplus.zookeeper.Lock;


//lock 锁 定义分布式锁
public interface Lock {
    //获取锁
    public void getLock();
    //释放锁资源
    public void unLock();
}
