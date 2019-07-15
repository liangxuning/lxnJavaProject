package socketserver.socketserverobject;

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
        private ObjectInputStream objectInputStream;
        private Socket socket;
        private Writer writer;
        public SockerThread(Socket socket) throws IOException {
            this.inputStream = socket.getInputStream();
            this.objectInputStream = new ObjectInputStream(inputStream);
            this.socket = socket;
        }
        public void run() {
            try {
                NeInfo neInfo = (NeInfo) objectInputStream.readObject();
                System.out.println(neInfo.getNe_id());
                writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                writer.write(String.format("收到 \n", socket.getPort()));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    objectInputStream.close();
                    writer.close();
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
