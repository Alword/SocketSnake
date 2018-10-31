package SnakeServer;

import javafx.scene.transform.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Host implements Runnable, ActionListener {
    public final int DELAY = 250;
    GameInfo gameInfo = null;

    ArrayList<Socket> sockets;

    public Host() {
        sockets = new ArrayList<>();
        gameInfo = new GameInfo();
    }

    public void addSockets(Socket socket) {
        sockets.add(socket);
        gameInfo.playersInfo.add(new PlayerInfo(16));
    }

    @Override
    public void run() {
        Timer sender = new Timer(DELAY, this);
        sender.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Socket> lockSockets = (ArrayList<Socket>) sockets.clone();

        for (int i = 0; i < lockSockets.size(); i++) {
            Socket socket = lockSockets.get(i);
            PrintWriter output = null;
            try {
                output = new PrintWriter(socket.getOutputStream());
            } catch (IOException e1) {
                e1.printStackTrace();
                sockets.remove(lockSockets.get(i));
            }
            output.println(TickRawData());
            output.flush();
        }
    }

    public String TickRawData() {
        //PrepareData
        String Data = RawPointData.applePoint(gameInfo.foodPosition);
        return Data;
    }
}