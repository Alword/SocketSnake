package SocketSnake;

import SnakeServer.Enums.Direction;
import SnakeServer.PlayerInfo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class GameKeyListener extends KeyAdapter {

    private PlayerInfo snake;

    public GameKeyListener(PlayerInfo snake) {
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
