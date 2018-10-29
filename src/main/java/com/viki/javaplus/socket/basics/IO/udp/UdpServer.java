package com.viki.javaplus.socket.basics.IO.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("udp-server started");
        //创建服务器端端口号
        DatagramSocket ds = new DatagramSocket(8080);
        //服务器接收客户端1024字节
        byte[] bytes=new byte[1024];
        //定义数据包
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        //接收客户端请求，数据封装
        ds.receive(dp);
        System.out.println("来源"+dp.getAddress()+"端口号"+dp.getPort());
        String result= new String(dp.getData(),0,dp.getLength());
        System.out.println(result);
        ds.close();
    }
}
