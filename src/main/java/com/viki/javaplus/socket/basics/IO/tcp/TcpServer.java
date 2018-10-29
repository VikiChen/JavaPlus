package com.viki.javaplus.socket.basics.IO.tcp;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("tcp-server started");
        ExecutorService executorService = Executors.newCachedThreadPool();  //使用可缓存线程池 使用多线程
        //创建服务器端连接
        ServerSocket serverSocket = new ServerSocket(8080);
        try {
            //接收客户端请求 阻塞功能
            Socket accept = serverSocket.accept();
            while (true){
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = accept.getInputStream();
                            //字节转String
                            byte[] bytes = new byte[1024];
                            int len = inputStream.read(bytes);
                            String result = new String(bytes,0, len);
                            System.out.println("服务器端接收到客户端内容："+result);
                            //返回数据
                            OutputStream outputStream = accept.getOutputStream();
                            outputStream.write("nice".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (Exception e){

        }finally {
            serverSocket.close();
        }


    }
}
