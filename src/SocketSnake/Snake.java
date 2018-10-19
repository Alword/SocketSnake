package SocketSnake;

import SocketSnake.Enums.Direction;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Snake {

    public boolean isInGame = true;
    private Direction currentDirection;
    private Direction controllerDirection;
    private ArrayList<Point> snakePoints = null;
    private int scale = 0;

    Snake(int scale) {
        currentDirection = Direction.Right;
        this.scale = scale;
        initSnake();
    }


    public Point snakeHeadGet() {
        return snakePoints.get(snakePoints.size() - 1);
    }

    public int size() {
        return snakePoints.size();
    }

    public void setDirection(Direction newDirection) {
        if (!currentDirection.getValue()
                .equals(MathPoint.multiply(newDirection.getValue(), -1))) {
            controllerDirection = newDirection;
        }
    }

    public void moveSnake() {
        if (controllerDirection != null) {
            currentDirection = controllerDirection;
        }
        Point moveVector = currentDirection.getValue();
        moveVector = MathPoint.multiply(moveVector, scale);
        Point nextPoint = MathPoint.add(snakeHeadGet(), moveVector);
        snakePoints.add(nextPoint);
        snakePoints.remove(0);
    }

    public void Kill() {
        isInGame = false;
    }

    public boolean checkApple(Point foodPoint) {
        if (snakePoints.get(snakePoints.size() - 1).distance(foodPoint) == 0) {
            addSnake();
            return true;
        }
        return false;
    }

    public ArrayList<Point> GetBody() {
        return snakePoints;
    }

    private void addSnake() {
        snakePoints.add(0, new Point(snakePoints.get(0).x, snakePoints.get(0).y));
    }

    private void initSnake() {
        int snakeSize = 3;
        snakePoints = new ArrayList<>(snakeSize);

        for (int i = 0; i < snakeSize; i++) {
            int x = (3 + i) * scale;
            int y = scale * 3;
            snakePoints.add(new Point(x, y));
        }
    }
}
