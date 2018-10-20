package SocketSnake;

import SnakeClient.Client;
import SnakeServer.MathPoint;
import SnakeServer.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameGrid extends JPanel {
    //geometry
    private final int SCALE = 16;
    private final int CELL_COUNT = 32;
    private final int FIELD_SIZE = CELL_COUNT * SCALE;
    private final int ALL_DOTS = CELL_COUNT * CELL_COUNT;

    //food
    private Point foodPoint = null;

    //Snake
    PlayerInfo snake;
    Client snakeClient = null;

    GameGrid() {
        setBackground(Color.black);
        createApple(new Point());
        initGame();
        addKeyListener(new GameKeyListener(snake));
        setFocusable(true);
    }

    private void initGame() {
        snakeClient = new Client(this);
        snake = new PlayerInfo(SCALE);
    }

    public void actionPerformed() {
        if (snake.isInGame) {
            snake.moveSnake();
            checkApple(snake);
            checkWalls();
            checkSnakes();
        }
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (snake.isInGame) {
            g.drawRect(0, 0, FIELD_SIZE + SCALE, FIELD_SIZE + SCALE);
            drawApple(g);
            drawSnake(g);
        } else {
            g.setColor(Color.white);
            g.drawString("Game Over", 545 / 2 - 67 / 2, FIELD_SIZE / 2);
        }
    }

    private void drawApple(Graphics g) {
        drawRect(g, Color.red, foodPoint);
    }

    private void drawSnake(Graphics g) {
        for (Point point :
                snake.GetBody()) {
            drawRect(g, Color.white, point);
        }
    }

    private void drawRect(Graphics g, Color newColor, Point point) {
        Color oldColor = g.getColor();
        g.setColor(newColor);
        g.drawRect(point.x, point.y, SCALE, SCALE);
        g.setColor(oldColor);
    }

    public void createApple(Point point) {
        foodPoint = MathPoint.multiply(point, SCALE);
    }

    private void checkApple(PlayerInfo snake) {
        if (snake.checkApple(foodPoint)) {
            snakeClient.sendCommand(4);
        }
    }

    private void checkWalls() {
        Point snakeHead = snake.snakeHeadGet();
        if (snakeHead.x > FIELD_SIZE
                || snakeHead.y > FIELD_SIZE
                || snakeHead.x < 0
                || snakeHead.y < 0) {
            snake.Kill();
        }
    }

    private void checkSnakes() {
        ArrayList<Point> snakeBody = snake.GetBody();

        for (int i = 0; i < snake.size() - 1; i++) {
            if (snake.snakeHeadGet().equals(snakeBody.get(i))) {
                snake.Kill();
            }
        }
    }

    private void setSnake() {
        Point snakeHead = snake.snakeHeadGet();
        if (snakeHead.x > FIELD_SIZE
                || snakeHead.y > FIELD_SIZE
                || snakeHead.x < 0
                || snakeHead.y < 0) {
            snake.Kill();
        }
    }
}
