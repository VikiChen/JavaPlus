package com.viki.javaplus.socket.basics.NIO.Netty5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

class ServerHandler extends ChannelHandlerAdapter {
	/**
	 * 当通道被调用,执行方法(拿到数据)
	 */
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String value = (String) msg;
		System.out.println("服务器端收到客户端msg:" + value);
		// // 回復客戶端
		ctx.writeAndFlush("好啊");

	}
}

public class NettyServer {

	public static void main(String[] args) {
		try {
			System.out.println("服务器端启动...");
			// 1.创建两个线程池,一个负责接收客户端，一个进行传输
			NioEventLoopGroup pGroup = new NioEventLoopGroup();
			NioEventLoopGroup cGroup = new NioEventLoopGroup();
			// 2.创建辅助类
			ServerBootstrap b = new ServerBootstrap();
			b.group(pGroup, cGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
					// 3.设置缓冲区与发送区大小
					.option(ChannelOption.SO_SNDBUF, 32 * 1024).option(ChannelOption.SO_RCVBUF, 32 * 1024)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel sc) throws Exception {
							sc.pipeline().addLast(new FixedLengthFrameDecoder(10));
							// ByteBuf buf =
							// Unpooled.copiedBuffer("_mayi".getBytes());
							// sc.pipeline().addLast(new
							// DelimiterBasedFrameDecoder(1024, buf));
							// 设置string类型
							sc.pipeline().addLast(new StringDecoder());
							sc.pipeline().addLast(new ServerHandler());
						}
					});
			// 启动
			ChannelFuture cf = b.bind(8080).sync();
			// 关闭
			cf.channel().closeFuture().sync();
			pGroup.shutdownGracefully();
			cGroup.shutdownGracefully();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
