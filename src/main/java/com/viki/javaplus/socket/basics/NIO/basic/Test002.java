package com.viki.javaplus.socket.basics.NIO.basic;

import org.junit.Test;

import java.nio.ByteBuffer;

public class Test002 {
    @Test
    public void test002(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        String str="abcd";
        byteBuffer.put(str.getBytes());
        //开启读模式
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes,0,2);
        byteBuffer.mark(); //打一个标记
        System.out.println(new String(bytes,0,2));
        System.out.println(byteBuffer.position());
        System.out.println("----------------------");

        //还原到mark 位置
        System.out.println(byteBuffer.position());
        byteBuffer.get(bytes,0,2);
        System.out.println(new String(bytes,0,2));
        byteBuffer.reset();
        System.out.println("重置还原到mark标记");
        System.out.println(byteBuffer.position());


    }
}
