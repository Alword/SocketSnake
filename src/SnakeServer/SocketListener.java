package SnakeServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
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
                System.out.println("Слушаю клиента" + i);

                Socket socket = lockSockets.get(i);
                Scanner input = new Scanner(socket.getInputStream());
                String next = input.next();
                if (next != null) {
                    host.data.set(i, Integer.parseInt(next));
                }

            } catch (IOException e1) {
                e1.printStackTrace();
                host.sockets.remove(lockSockets.get(i));
            }
        }
    }

    @Override
    public void run() {
        Timer sender = new Timer(host.DELAY, this);
        sender.start();
    }
}
