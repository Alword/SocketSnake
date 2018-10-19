package SnakeServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Host implements Runnable, ActionListener {
    public final int DELAY = 250;

    ArrayList<Socket> sockets;
    ArrayList<Integer> data;

    public Host() {
        sockets = new ArrayList<>();
        data = new ArrayList<>();
    }

    public void addSockets(Socket socket) {
        sockets.add(socket);
        data.add(0);
    }

    @Override
    public void run() {
        Timer sender = new Timer(DELAY, this);
        sender.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Socket> lockSockets = (ArrayList<Socket>) sockets.clone();

        //PrepareData
        Point x = new Point((int) (Math.random() * 10), (int) (Math.random() * 10));
        String Data = RawPointData.applePoint(x);

        for (int i = 0; i < lockSockets.size(); i++) {
            Socket socket = lockSockets.get(i);
            PrintWriter output = null;
            try {
                output = new PrintWriter(socket.getOutputStream());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            output.println(Data);
            output.flush();
        }
    }
}

