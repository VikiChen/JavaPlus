package com.viki.javaplus.socket.basics.NIO.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NioServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Nio server started");
        //1.创建服务器端
        ServerSocketChannel sChannel = ServerSocketChannel.open();
        //2.设置成异步读取
        sChannel.configureBlocking(false);
        //3.绑定连接
        sChannel.bind(new InetSocketAddress(8080));
        //4.获取选择器
        Selector selector= Selector.open();
        //5.将通道注册到选择器中 并且监听已经接受到的事件
        sChannel.register(selector,SelectionKey.OP_ACCEPT);
        //6.轮询获取已经准备就绪的事件
        while (selector.select()>0) {
            //7.获取当前选择器 有注册已经监听到时间
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //8.获取准备就绪时间
                SelectionKey sk = it.next();
                //9.判断时间准备就绪
                if (sk.isAcceptable()) {
                    //10. 如果接受就绪，获取客户端连接
                    SocketChannel scoketChannel = sChannel.accept();
                    //11.设置为阻塞
                    scoketChannel.configureBlocking(false); //异步非阻塞
                    //12。将该通道注册到服务器上
                    scoketChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    //13. 获取当前选择器 “就绪状态”通道
                    SelectableChannel socketChinnel = (SocketChannel)sk.channel();
                    //14. 读取数据
                    int len=0;
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while ((len=((SocketChannel) socketChinnel).read(byteBuffer))>0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,len));
                        byteBuffer.clear();
                    }
                }
                it.remove();
            }
        }

    }
}
