package de.felix_klauke.sansa.server.initializer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Felix 'SasukeKawaii' Klauke
 */
public class SansaServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new LoggingHandler(LogLevel.TRACE));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new SimpleChannelInboundHandler<String>() {
            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("Active channel!");

                ctx.channel().writeAndFlush("234 ok\n");
            }

            @Override
            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                System.out.println("Request: '" + s + "'");

                if (s.contains("AUTH TLS")) {
                    channelHandlerContext.channel().writeAndFlush("234 ok\n");
                } else if (s.contains("USER felix")) {
                    channelHandlerContext.channel().writeAndFlush("331 password needed\n");
                } else if (s.contains("PASS test")) {
                    channelHandlerContext.channel().writeAndFlush("230 logged in\n");
                }
            }
        });
    }
}
