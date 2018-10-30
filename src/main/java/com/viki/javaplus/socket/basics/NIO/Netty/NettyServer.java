package com.viki.javaplus.socket.basics.NIO.Netty;


import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServerHandler extends SimpleChannelHandler {
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
        ctx.getChannel().write("你好啊");
    }
}

public class NettyServer {
    public static void main(String[] args) {
        //1.创建服务对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //2.创建两个线程池  作用1监听端口号，2，nio监听
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService wook = Executors.newCachedThreadPool();
        //3.将线程池放入工程中
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boos,wook));
        //4.设置管道工厂
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                //传输数据的时候直接为string 类型
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                //设置监狱类
                pipeline.addLast("serverer",new ServerHandler());
                return pipeline;

            }
        });
        //绑定端口号
        serverBootstrap.bind(new InetSocketAddress(8080));
        System.out.println("服务器端已经被启动");


    }
}
