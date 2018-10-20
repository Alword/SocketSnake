package SnakeClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Time;

public class DataSender implements Runnable, ActionListener {

    private int command = -1;

    private Socket socket;

    private PrintStream printStream = null;

    DataSender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            printStream = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Timer sendTimer = new Timer(250 / 2, this);
        sendTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        printStream.println(command);
        printStream.flush();
        command = -1;
    }

    public void sendCommand(int command) {
        this.command = command;
    }
}
