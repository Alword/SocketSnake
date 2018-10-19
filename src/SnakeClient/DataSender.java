package SnakeClient;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class DataSender implements Runnable {

    Socket socket;

    DataSender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            int number = (int)(Math.random()*10);
            printStream.println(number);
            printStream.flush();
        }
    }
}
