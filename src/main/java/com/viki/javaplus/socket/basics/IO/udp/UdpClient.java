package com.viki.javaplus.socket.basics.IO.udp;

import java.io.IOException;
import java.net.*;

public class UdpClient {
    public static void main(String[] args) throws IOException {
        System.out.println("upd-client started");
        DatagramSocket ds=new DatagramSocket();
        String str="蚂蚁课堂";
        byte[] strByte =str.getBytes();
        DatagramPacket dp =new DatagramPacket(strByte,strByte.length, InetAddress.getByName("127.0.0.1"),8080);
        ds.send(dp);
        ds.close();

    }
}
