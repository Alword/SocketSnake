package SnakeServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        Host host = new Host();
        SocketListener listener = new SocketListener(host);

        new Thread(host).start();
        new Thread(listener).start();

        try {
            ServerSocket serverSocket = new ServerSocket(9800);
            while (true) {
                Socket socket = serverSocket.accept();
                host.addSockets(socket);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
