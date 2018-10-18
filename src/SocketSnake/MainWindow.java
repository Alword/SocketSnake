package SocketSnake;

import sun.applet.Main;

import javax.swing.*;

public class MainWindow extends JFrame {

    private MainWindow() {
        setTitle("SocketSnake Client 1.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(512, 512);
        setLocation(200, 200);
        add(new GameGrid());
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
