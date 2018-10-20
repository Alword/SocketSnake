package SnakeServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class SocketListener implements Runnable, ActionListener {

    Host host = null;

    public SocketListener(Host host) {
        this.host = host;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Socket> lockSockets = (ArrayList<Socket>) host.sockets.clone();
        for (int i = 0; i < lockSockets.size(); i++) {
            try {
                Socket socket = lockSockets.get(i);
                Scanner scanner = new Scanner(socket.getInputStream());
                //GetCommands
                if (scanner.hasNextInt()) {
                    int cmd = scanner.nextInt();
                    System.out.println(cmd);
                    host.gameInfo.sendCommand(i, cmd);
                }

            } catch (IOException e1) {
                e1.printStackTrace();
                host.sockets.remove(lockSockets.get(i));
            }
        }
    }

    @Override
    public void run() {
        Timer sender = new Timer(host.DELAY / 2, this);
        sender.start();
    }
}