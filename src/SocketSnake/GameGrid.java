package SocketSnake;

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

    //food
    private Point foodPoint = null;

    //Snake
    Snake snake;

    GameGrid() {
        snake = new Snake(SCALE);
        setBackground(Color.black);
        createApple();
        initGame();
        addKeyListener(new GameKeyListener(snake));
        setFocusable(true);
    }

    private void initGame() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            drawApple(g);
            drawSnake(g);
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

    private void createApple() {
        int x = (int) (Math.random() * CELL_COUNT - 1) * SCALE;
        int y = (int) (Math.random() * CELL_COUNT - 1) * SCALE;
        foodPoint = new Point(x, y);
    }

    private void checkApple(Snake snake) {
        if (snake.checkApple(foodPoint)) {
            createApple();
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
            if ( snake.snakeHeadGet().equals(snakeBody.get(i))) {
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
