package SocketSnake;

import sun.applet.Main;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("SocketSnake Client 1.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1024, 1024);
        setLocation(300, 300);
        add(new GameGrid());
        setVisible(true);
    }

    public static void main(String[] args) {

    }
}
