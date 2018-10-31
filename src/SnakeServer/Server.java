package SnakeServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //StartConnection
    public static void main(String[] args) {
        Host host = new Host();
        SocketListener listener = new SocketListener(host);

        new Thread(host).start();
        new Thread(listener).start();

        try {
            ServerSocket serverSocket = new ServerSocket(9870);
            while (true) {
                Socket socket = serverSocket.accept();
                host.addSockets(socket);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
