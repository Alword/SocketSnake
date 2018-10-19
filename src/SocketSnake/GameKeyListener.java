package SocketSnake;

import SocketSnake.Enums.Direction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class GameKeyListener extends KeyAdapter {

    private Snake snake;

    public GameKeyListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
                snake.setDirection(Direction.Left);
                break;
            case KeyEvent.VK_RIGHT:
                snake.setDirection(Direction.Right);
                break;
            case KeyEvent.VK_UP:
                snake.setDirection(Direction.Up);
                break;
            case KeyEvent.VK_DOWN:
                snake.setDirection(Direction.Down);
                break;
        }
    }
}
