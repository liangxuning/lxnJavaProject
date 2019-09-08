package com.lxn.learn.netty.basesocket.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClient {
    public SocketClient() {
        init();
    }

    private void init() {
        try {
            System.out.println("1");
            Socket socket = new Socket("127.0.0.1", 8989);
            System.out.println("2");
            if (socket.isConnected()) {
                System.out.println("3");
                new Writer(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Read extends Thread{
        private Socket socket;
        public Read(Socket socket) throws IOException {
            this.socket = socket;

        }

        @Override
        public void run() {
            super.run();
            System.out.println("4");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()));
                System.out.println(bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Writer extends Thread{
        private InputStreamReader inputStreamReader;
        private BufferedReader bufferedReader;
        private Socket socket;
        private java.io.Writer writer;
        public Writer(Socket socket) throws IOException {
            this.inputStreamReader = new InputStreamReader(socket.getInputStream());
            this.bufferedReader = new BufferedReader(inputStreamReader);
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            try {
                writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                writer.write("Hi,%d.天朗气清，惠风和畅！\n");
                writer.flush();
                String read = null;
                while ((read = bufferedReader.readLine()) != null) {
                    System.out.println(read);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                    bufferedReader.close();
                    inputStreamReader.close();
                    System.out.println("客户端socket" + socket.getPort() + socket);
                    socket.close();
                    System.out.println("客户端socket关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
