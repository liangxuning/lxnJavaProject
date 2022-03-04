package com.sshd.lxn;

import io.netty.channel.local.LocalAddress;
import org.apache.sshd.common.future.CloseFuture;
import org.apache.sshd.common.future.SshFutureListener;
import org.apache.sshd.common.io.IoAcceptor;
import org.apache.sshd.common.io.IoHandler;
import org.apache.sshd.common.io.IoSession;
import org.apache.sshd.common.util.Readable;
import org.apache.sshd.netty.NettyIoAcceptor;
import org.apache.sshd.netty.NettyIoConnector;
import org.apache.sshd.netty.NettyIoServiceFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        NettyIoServiceFactory nettyIoServiceFactory = new NettyIoServiceFactory();
        IoHandler ioHandler = new IoHandler() {
            @Override
            public void sessionCreated(IoSession ioSession) throws Exception {
                System.out.println(ioSession.getId());
            }

            @Override
            public void sessionClosed(IoSession ioSession) throws Exception {
                System.out.println(ioSession.getId());
            }

            @Override
            public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {
                System.out.println(ioSession.getId());
            }

            @Override
            public void messageReceived(IoSession ioSession, Readable readable) throws Exception {
                byte[] b = new byte[4*1024];
                readable.getRawBytes(b, 0, 1024);
                System.out.println(ioSession.getId() + String.valueOf(b));
            }
        };
        NettyIoAcceptor acceptor = (NettyIoAcceptor) nettyIoServiceFactory.createAcceptor(ioHandler);
        acceptor.addCloseFutureListener(new SshFutureListener<CloseFuture>() {
            @Override
            public void operationComplete(CloseFuture closeFuture) {
                System.out.println("close");
            }
        });
        try {
            acceptor.bind(new InetSocketAddress("127.0.0.1", 1234));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
