package SocketSnake;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameGrid extends JPanel {
    private final int SIZE = 1024;
    private final int SCALE = 16;
    private final int DOT_SIZE = SIZE / SCALE;
    private final int ALLDOTS = DOT_SIZE * DOT_SIZE;

    private Image snakeSkin;
    private Image food;

    private int foodX = 0;
    private int foodY = 0;

    //Snake
    private ArrayList<Point> snakePoints = null;
    private int snakeSize = 0;

    //enum
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isIngame = false;


    public GameGrid() {

    }
}
