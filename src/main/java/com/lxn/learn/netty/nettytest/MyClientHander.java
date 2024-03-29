package com.lxn.learn.netty.nettytest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class MyClientHander extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(channelHandlerContext.channel().remoteAddress());
        System.out.println("client output: " + s);
        Thread.sleep(1000);
        channelHandlerContext.writeAndFlush("from client: " + LocalDateTime.now() + "from clientfrom client" +
                "from clientfrom clientfrom clientfrom clientfrom clientfrom clientfrom clientfrom clientfrom client" +
                "from clientfrom clientfrom clientfrom clientfrom clientfrom client" +
                "from client");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自客户端的问候！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("被服务端关闭了");
        cause.printStackTrace();
        ctx.close();
    }

}
