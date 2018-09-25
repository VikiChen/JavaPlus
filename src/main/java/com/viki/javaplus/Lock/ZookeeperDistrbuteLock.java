package com.viki.javaplus.Lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {
    @Override
    Boolean tryLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    void waitLock() {
        //使用时间监听， 获取到节点被删除
       IZkDataListener iZkDataListener= new IZkDataListener() {
            //当节点被改变的时候
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
            }
            //当节点被删除的时候
            @Override
            public void handleDataDeleted(String s) throws Exception {
                //唤醒
                if (countDownLatch!=null){
                    countDownLatch.countDown();
                }
            }
        };
        //注册节点信息
        zkClient.subscribeDataChanges(PATH,iZkDataListener);
        if (zkClient.exists(PATH)) {
            //创建 信号量
            countDownLatch=new CountDownLatch(1);
            try {
                //等待
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //删除事件通知
        zkClient.unsubscribeDataChanges(PATH,iZkDataListener);
    }
}
