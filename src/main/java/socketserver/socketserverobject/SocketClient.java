package socketserver.socketserverobject;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class SocketClient {
    private String readline;


    public String init() {
        try {
            System.out.println("1");
            Socket socket = new Socket("127.0.0.1", 8989);
            System.out.println("2");
            if (socket.isConnected()) {
                System.out.println("3");
                CountDownLatch count = new CountDownLatch(1);
                new Writer(socket, count).start();
                count.await();
            }
            return readline;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return readline;
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
        private ObjectOutputStream objectOutputStream;
        private InputStreamReader inputStreamReader;
        private BufferedReader bufferedReader;
        private Socket socket;
        private CountDownLatch count;
        public Writer(Socket socket, CountDownLatch count) throws IOException {
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStreamReader = new InputStreamReader(socket.getInputStream());
            this.bufferedReader = new BufferedReader(inputStreamReader);
            this.socket = socket;
            this.count = count;
        }

        @Override
        public void run() {
            super.run();
            try {
                NeInfo neInfo = new NeInfo();
                neInfo.setNe_id("123");
                objectOutputStream.writeObject(neInfo);
                String read = null;
                while ((read = bufferedReader.readLine()) != null) {
                    readline = read;
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    objectOutputStream.close();
                    bufferedReader.close();
                    inputStreamReader.close();
                    System.out.println("客户端socket" + socket.getPort() + socket);
                    socket.close();
                    count.countDown();
                    System.out.println("客户端socket关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getReadline() {
        return readline;
    }

    public void setReadline(String readline) {
        this.readline = readline;
    }
}
