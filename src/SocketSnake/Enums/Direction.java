package SocketSnake.Enums;

import java.awt.*;

public enum Direction {
    Left(new Point(-1, 0)),
    Up(new Point(0, -1)),
    Right(new Point(1, 0)),
    Down(new Point(0, 1));

    private Point value;

    Direction(Point value) {
        this.value = value;
    }

    public Point getValue() {
        return value;
    }
}
