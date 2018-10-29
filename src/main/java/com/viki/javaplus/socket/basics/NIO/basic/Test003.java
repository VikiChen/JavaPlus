package com.viki.javaplus.socket.basics.NIO.basic;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

public class Test003 {

    @Test
    public void test002() throws IOException {
        long startTime= System.currentTimeMillis();
        //直接缓冲区
        FileChannel inChannel = FileChannel.open(Paths.get("1.mp4"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.mp4"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
        //定义映射文件
        MappedByteBuffer inMappdeByte = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappdeByte = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        //直接对缓冲区操作
        byte[] dsf = new byte[inMappdeByte.limit()];
        inMappdeByte.get(dsf);
        outMappdeByte.put(dsf);
        inChannel.close();
        outChannel.close();
        long endTime=System.currentTimeMillis();
        System.out.println("耗时"+(endTime-startTime));
    }

    @Test
    public void test001() throws IOException {
        long startTime= System.currentTimeMillis();
        //非直接缓冲区  读写操作
        //读入
        FileInputStream fileInputStream = new FileInputStream("1.mp4");
        //写入流
        FileOutputStream fileOutputStream = new FileOutputStream("2.mp4");
        //创建通道
        FileChannel inC=fileInputStream.getChannel();
        FileChannel outC = fileOutputStream.getChannel();
        //分配缓冲区大小
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (inC.read(buf)!=-1){
            //开启读取模式
            buf.flip();
            //将数据写入通道中
            outC.write(buf);
            buf.clear();
        }
        //关闭通道
        inC.close();
        outC.close();
        fileInputStream.close();
        fileOutputStream.close();
        long endTime=System.currentTimeMillis();
        System.out.println("耗时"+(endTime-startTime));
    }
}
