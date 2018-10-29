package com.viki.javaplus.socket.basics.NIO.basic;


//NIO test  配合通道使用 存储数据

import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/***
 * Buffer
 * ByteBuffer
 * LongBuffer
 * IntegerBuffer
 * FloatBuffer
 * DubleBuffer
 *
 */
public class BufferTest {

    @Test
    public void test001(){
        //初始化缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byteBuffer.put("abcs".getBytes());

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //读取值
        System.out.println("开始读取");
        //开启读取模式
        byteBuffer.flip();   //从0 开始
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes,0,bytes.length));
        //重复读取
        System.out.println("重复读取");
        //开启读取模式
        byteBuffer.rewind();  //返回上一次读取position
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byte[] bytes2 = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes2);
        System.out.println(new String(bytes2,0,bytes2.length));

        //清空缓冲区
        System.out.println("清空缓冲区");
        byteBuffer.clear();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println((char) byteBuffer.get());

    }


}
