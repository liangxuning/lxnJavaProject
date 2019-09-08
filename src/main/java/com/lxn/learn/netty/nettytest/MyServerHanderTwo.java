package com.lxn.learn.netty.nettytest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

public class MyServerHanderTwo extends IdleStateHandler {
    /**
     * Creates a new instance firing {@link IdleStateEvent}s.
     *
     * @param readerIdleTimeSeconds an {@link IdleStateEvent} whose state is {@link IdleState#READER_IDLE}
     *                              will be triggered when no read was performed for the specified
     *                              period of time.  Specify {@code 0} to disable.
     * @param writerIdleTimeSeconds an {@link IdleStateEvent} whose state is {@link IdleState#WRITER_IDLE}
     *                              will be triggered when no write was performed for the specified
     *                              period of time.  Specify {@code 0} to disable.
     * @param allIdleTimeSeconds    an {@link IdleStateEvent} whose state is {@link IdleState#ALL_IDLE}
     *                              will be triggered when neither read nor write was performed for
     *                              the specified period of time.  Specify {@code 0} to disable.
     */
    public MyServerHanderTwo(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }


    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.out.println("hander two close");
        super.close(ctx, promise);
    }
}
