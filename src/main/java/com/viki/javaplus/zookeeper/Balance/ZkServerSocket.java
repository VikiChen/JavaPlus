package com.viki.javaplus.zookeeper.Balance;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//##ServerScoekt服务端
public class ZkServerSocket implements Runnable {
    private int port = 18081;

    public static void main(String[] args) throws IOException {
        int port = 18081;
        ZkServerSocket server = new ZkServerSocket(port);
        Thread thread = new Thread(server);
        thread.start();
    }

    public ZkServerSocket(int port) {
        this.port = port;
    }


    //将服务器信息注册到注册中心
    public void register(){
        ZkClient zkClient=   new ZkClient("127.0.0.1:2181",6000,1000);
        String path="/member/server-"+port;   //注意创建子节点时 member节点必须已经存在
        if (zkClient.exists(path)){
            zkClient.delete(path);
        }else {
            String value="127.0.0.1:"+port;
            zkClient.createEphemeral(path,"127.0.0.1:"+port);
            System.out.println("服务注册成功"+value);
        }
    }

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            register();
            System.out.println("Server start port:" + port);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e2) {

            }
        }
    }

}
