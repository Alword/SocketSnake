package SnakeServer;

import java.awt.*;

public class RawPointData {

    public static String applePoint(Point point) {
        return "a" + point.x + ":" + point.y;
    }

    public static String initSnake(int snakeID, Point[] points) {
        String rawBody = "id" + snakeID;
        for (Point point :
                points) {
            rawBody += "X" + point.x + "Y" + point.y;
        }
        return "j:" + rawBody + ";";
    }

    public static String updateString(int snakeID, Point vector) {
        return "u:id" + snakeID + "X" + vector.x + "Y" + vector.y;
    }
}
