package com.viki.javaplus.socket.basics.NIO.Netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ClientHandler extends SimpleChannelHandler {
    //通道关闭的时候被触发
    @Override
    public void channelInterestChanged(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelInterestChanged(ctx, e);
        System.out.println("变了");
    }

    //必须要建立连接，关闭通道的时候才会触发
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        System.out.println("关闭");
    }

    //接收端出现异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        System.out.println("异常");
    }

    //接收数据
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);
        System.out.println("收到了");
        System.out.println(e.getMessage());
    }
}

public class NettyClient {
    public static void main(String[] args) {
        //1.创建服务对象
        ClientBootstrap clientBootstrap = new ClientBootstrap();
        //2.创建两个线程池  作用1监听端口号，2，nio监听
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService wook = Executors.newCachedThreadPool();
        //3.将线程池放入工程中
        clientBootstrap.setFactory(new NioClientSocketChannelFactory(boos,wook));
        //4.设置管道工厂
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                //传输数据的时候直接为string 类型
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                //设置监狱类
                pipeline.addLast("clientHandler",new ClientHandler());
                return pipeline;

            }
        });
        //绑定端口号
        ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 8080));
        Channel channel = connect.getChannel();
        System.out.println("客户端已经被启动");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入内容：");
            channel.write(scanner.next());
        }
    }
}
