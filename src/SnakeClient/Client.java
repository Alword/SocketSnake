package SnakeClient;

import SocketSnake.GameGrid;

import java.awt.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private GameGrid gameGrid = null;
    private DataSender dataSender = null;
    private DataListener dataListener = null;

    public Client(GameGrid grid) {
        gameGrid = grid;

        try {
            Socket socket = new Socket("127.0.0.1", 9870);
            dataSender = new DataSender(socket);
            new Thread(dataSender).start();

            Scanner scanner = new Scanner(socket.getInputStream());
            dataListener = new DataListener(this,scanner);
            new Thread(dataListener).start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCommand(String rawData) {

        if (rawData == null) {
            return;
        }

        if (rawData.contains("a")) {
            rawData = rawData.substring(1);
            String[] pointStrings = rawData.split(":");
            Point applePoint = new Point(Integer.parseInt(pointStrings[0]), Integer.parseInt(pointStrings[1]));
            gameGrid.createApple(applePoint);
        }
        if (rawData.contains("j")) {
        }
        if (rawData.contains("u")) {
        }
        gameGrid.actionPerformed();
    }

    public void sendCommand(int command) {
        dataSender.sendCommand(command);
    }
}

