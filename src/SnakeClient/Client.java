package SnakeClient;

import SocketSnake.GameGrid;

import java.awt.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    static GameGrid gameGrid = null;

    public Client(GameGrid grid) {
        gameGrid = grid;

        try {
            Socket socket = new Socket("127.0.0.1", 9870);
            DataSender keyListener = new DataSender(socket);
            new Thread(keyListener).start();

            Scanner scanner = new Scanner(socket.getInputStream());
            DataListener listener = new DataListener(scanner);
            new Thread(listener).start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ParseCommand(String rawData) {

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
    }
}

