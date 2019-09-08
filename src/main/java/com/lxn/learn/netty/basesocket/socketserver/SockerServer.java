package com.lxn.learn.netty.basesocket.socketserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SockerServer {
    private int port;
    public SockerServer(int i) {
        this.port = i;
        init();
    }

    private void init() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("建立一个连接……");
                new SockerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class SockerThread extends Thread{
        private InputStream inputStream;
        private InputStreamReader inputStreamReader;
        private BufferedReader bufferedReader;
        private Socket socket;
        private Writer writer;
        public SockerThread(Socket socket) throws IOException {
            inputStream = socket.getInputStream();
            this.inputStreamReader = new InputStreamReader(inputStream);
            this.bufferedReader = new BufferedReader(inputStreamReader);
            this.socket = socket;
        }
        public void run() {
            try {
                String readline = null;
                while ( (readline = bufferedReader.readLine()) != null) {
                    System.out.println(readline);
                    Thread.sleep(1000);
                    writer = new OutputStreamWriter(socket.getOutputStream(),
                            "UTF-8");
                    writer.write(String.format("Hi,%d.天朗气清，惠风和畅！\n", socket.getPort()));
                    writer.flush();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                    bufferedReader.close();
                    inputStreamReader.close();
                    System.out.println("服务端socket" + socket.getPort() + socket);
                    socket.close();
                    System.out.println("服务端socket关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
