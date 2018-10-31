package SnakeServer;

import SnakeServer.Enums.Direction;

import java.awt.*;
import java.util.ArrayList;

public class GameInfo {

    Point foodPosition;
    ArrayList<PlayerInfo> playersInfo;

    public GameInfo() {
        playersInfo = new ArrayList<>();
        updateFood();
    }

    void sendCommand(int id, int command) {
        PlayerInfo player = playersInfo.get(id);

        switch (command) {
            case 0:
                player.setDirection(Direction.Left);
                break;
            case 1:
                player.setDirection(Direction.Up);
                break;
            case 2:
                player.setDirection(Direction.Right);
                break;
            case 3:
                player.setDirection(Direction.Down);
                break;
            case 4:
                updateFood();
                break;
        }
    }

    private void updateFood() {
        foodPosition = new Point((int) (Math.random() * 32), (int) (Math.random() * 32));
    }

}
