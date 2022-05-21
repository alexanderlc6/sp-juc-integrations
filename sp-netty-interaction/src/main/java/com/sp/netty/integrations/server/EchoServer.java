package com.sp.netty.integrations.server;

import com.sp.netty.integrations.encoder.RequestSampleHandler;
import com.sp.netty.integrations.encoder.ResponseSampleEncoder;
import com.sp.netty.integrations.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * @description:
 * @author: luchao
 * @date: Created in 5/15/22 1:22 PM
 */
public class EchoServer {
    public void startEchoServer(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bts = new ServerBootstrap();
            bts.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sch) throws Exception {
                            //Terminal telnet连接测试
//                            sch.pipeline().addLast(new FixedLengthFrameDecoder(20));
//                            ByteBuf delimiter = Unpooled.copiedBuffer("&".getBytes());
//                            sch.pipeline().addLast(new DelimiterBasedFrameDecoder(10, true,
//                                    true,delimiter));
//                            sch.pipeline().addLast(new EchoServerHandler());
                            sch.pipeline().addLast(new ResponseSampleEncoder());
                            sch.pipeline().addLast(new RequestSampleHandler());
                        }
                    });
            ChannelFuture cf = bts.bind(port).sync();
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer().startEchoServer(8088);
    }
}
