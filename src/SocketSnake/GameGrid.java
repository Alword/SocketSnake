package SocketSnake;

import javafx.scene.transform.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameGrid extends JPanel implements ActionListener {
    //geometry
    private final int SCALE = 16;
    private final int FIELD_SIZE = 32 * SCALE;
    private final int CELL_COUNT = FIELD_SIZE / SCALE;
    private final int ALL_DOTS = CELL_COUNT * CELL_COUNT;

    //time
    private final int DELAY = 250;
    private Timer timer;

    private Image snakeSkin;
    private Image food;

    //food
    private Point foodPoint = null;

    //Snake
    private ArrayList<Point> snakePoints = null;
    private int snakeSize = 0;

    //enum
    private boolean isLeft = false;
    private boolean isRight = true;
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isIngame = true;

    GameGrid() {
        setBackground(Color.black);
        createApple();
        initGame();
    }

    private void initGame() {
        snakeSize = 3;
        snakePoints = new ArrayList<>(snakeSize);

        for (int i = 0; i < snakeSize; i++) {
            int x = (3 + i) * SCALE;
            int y = FIELD_SIZE / 2;
            snakePoints.add(new Point(x, y));
        }

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void createApple() {
        int x = (int) (Math.random() * CELL_COUNT - 1) * SCALE;
        int y = (int) (Math.random() * CELL_COUNT - 1) * SCALE;
        foodPoint = new Point(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isIngame) {
            moveSnake();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isIngame) {
            drawApple(g);
            drawSnake(g);
        }
    }

    private void moveSnake() {
        int last = snakePoints.size() - 1;

        Point moveVector;

        //switch () enum
        if (isUp) {
            moveVector = new Point(0, 1);
        } else if (isRight) {
            moveVector = new Point(1, 0);
        } else if (isDown) {
            moveVector = new Point(0, -1);
        } else if (isLeft) {
            moveVector = new Point(-1, 0);
        } else {
            moveVector = new Point();
        }

        moveVector = MathPoint.myltiply(moveVector, SCALE);
        Point nextPoint = MathPoint.add(snakePoints.get(last), moveVector);
        snakePoints.add(nextPoint);
        snakePoints.remove(0);
    }

    private void drawApple(Graphics g) {
        drawRect(g, Color.red, foodPoint);
    }

    private void drawSnake(Graphics g) {

        for (Point point :
                snakePoints) {
            drawRect(g, Color.white, point);
        }
    }

    private void drawRect(Graphics g, Color newColor, Point point) {
        Color oldColor = g.getColor();
        g.setColor(newColor);
        g.drawRect(point.x, point.y, SCALE, SCALE);
        g.setColor(oldColor);
    }
}
