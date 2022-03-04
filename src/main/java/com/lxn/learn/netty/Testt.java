package com.lxn.learn.netty;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

class Telnet {
    Socket serverSocket;
    // 连接用socket
    public OutputStream serverOutput; // 用于网络输出的流
    public BufferedInputStream serverInput; // 用于网络输入的流
    String host; // 连接服务器的地址
    int port; // 连接服务器的端口号
    static final int DEFAULT_TELNET_PORT = 23; // telnet的端口号(23号)
    // 构造器(1):指定地址和端口号时用
    public Telnet(String host, int port) {
        this.host = host;		this.port = port;
    }	// 构造器(2)只指定地址时用
    public Telnet(String host) {
        this(host, DEFAULT_TELNET_PORT); // 假定为Telnet端口
    }
    // openConnection 方法
    // 由地址和端口号虽构成Socket而开成流
    public void openConnection() throws IOException, UnknownHostException {
        serverSocket = new Socket(host, port);
        serverOutput = serverSocket.getOutputStream();
        serverInput = new BufferedInputStream(serverSocket.getInputStream());
        // 若连接的是Telnet端口则进行协商
        if (port == DEFAULT_TELNET_PORT) {
            negotiation(serverInput, serverOutput);
        }
    }	// main_proc方法	// 启动进行网络处理的线程
    public void main_proc() throws IOException {
        try {
            // 生成线程用streamConnector类的对象
            StreamConnector stdin_to_socket = new StreamConnector(System.in,serverOutput);
            StreamConnector socket_to_stdout = new StreamConnector(serverInput,	System.out);
            // 生成线程
            Thread input_thread = new Thread(stdin_to_socket);
            Thread output_thread = new Thread(socket_to_stdout);
            // 启动线程
            input_thread.start();
            output_thread.start();
        } catch (Exception e) {
            System.err.print(e);
            System.exit(1);
        }
    }	// 定义用于协商的命令
    static final byte IAC = (byte) 255;
    static final byte DONT = (byte) 254;
    static final byte DO = (byte) 253;
    static final byte WONT = (byte) 252;
    static final byte WILL = (byte) 251;
    // negotiation 方法
    // 利用NVT进行协商通信
    static void negotiation(BufferedInputStream in, OutputStream out) throws IOException {
        byte[] buff = new byte[3];
        // 接收命令的数组
        while (true) {
            in.mark(buff.length);
            if (in.available() >= buff.length) {
                in.read(buff);
                if (buff[0] != IAC) {
                    // 协商结束
                    in.reset();
                    return;
                } else if (buff[1] == DO) {
                    // 对于DO命令......
                    buff[1] = WONT; // 用WONT作为应答
                    out.write(buff);
                }
            }
        }
    }	// main方法
    // 建立TCP连接,开始处理
    public static void main(String[] arg) {
        try {
            Telnet t = null;
            // 由参数个数决定调用哪个构造器
            switch (arg.length) {
                case 1: //只指定服务器地址
                    t = new Telnet(arg[0]);
                    break;
                case 2: //指定地址和端口
                    t = new Telnet(arg[0], Integer.parseInt(arg[1]));
                    break;
                default: //使用方法不正确时
                    System.out.println(
                            "usage:java Telnet <host name> { <port number> } ");
                    return;
            }
            t = new Telnet("222.211.88.185", 23);
            t.openConnection();
            t.main_proc();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

// StreamConnector类
// 接收流参数以二者结合实现数据传递
// StreamConnector类是用于构造线程序的类
class StreamConnector implements Runnable {
    InputStream src = null;	OutputStream dist = null;
    // 构造器 接收输入输出流
    public StreamConnector(InputStream in, OutputStream out) {
        src = in;
        dist = out;
    }
    // 执行处理的函数体
    // 无限循环进行流的读写
    @Override
    public void run() {
        byte[] buff = new byte[1024];
        while (true) {
            try {
                int n = src.read(buff);
                if (n > 0) {
                    dist.write(buff, 0, n);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.print(e);
                System.exit(1);
            }
        }
    }
}