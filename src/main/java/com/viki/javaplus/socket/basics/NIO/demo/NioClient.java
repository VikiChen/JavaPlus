package com.viki.javaplus.socket.basics.NIO.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class NioClient {
    public static void main(String[] args) throws IOException {
        System.out.println("nioclient started");
        //1.创建socket 通道
        SocketChannel schinal = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
        //2.切换为异步非阻塞
        schinal.configureBlocking(false);
        //3.指定缓冲区大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(new Date().toString().getBytes());
        //4. 切换读取模式
        buffer.flip();
        schinal.write(buffer);
        buffer.clear();
        //5. 关闭通道
        schinal.close();



    }
}
