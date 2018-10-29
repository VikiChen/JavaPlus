package com.viki.javaplus.socket.basics.NIO.basic;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test004 {

    @Test
    public void test1() throws IOException {
        long startTime =System.currentTimeMillis();
        //随机访问
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        //获取通道
        FileChannel channel = randomAccessFile.getChannel();
        //分配指定大小缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        //分散读取
        ByteBuffer[] bufs={buf1,buf2};
        channel.read(bufs);
        for (ByteBuffer byteBuffer:bufs){
            //切换成读模式
            byteBuffer.flip();
        }
        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
        System.out.println("分散读取");
        //随机访问
        RandomAccessFile randomAccessFile2 = new RandomAccessFile("3.txt", "rw");
        //获取通道
        FileChannel channel2 = randomAccessFile2.getChannel();
        channel2.write(bufs);
        randomAccessFile2.close();
        randomAccessFile.close();
        long endTime=System.currentTimeMillis();
        System.out.println("结束"+(endTime-startTime));
    }
}
