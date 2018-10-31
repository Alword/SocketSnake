package SocketSnake;

import sun.applet.Main;

import javax.swing.*;

public class MainWindow extends JFrame {

    private MainWindow() {
        setTitle("SocketSnake Client 1.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(545, 570);
        setLocation(400, 200);
        add(new GameGrid());
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
