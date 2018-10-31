package SnakeServer;

public class MathPoint {

    public static java.awt.Point add(java.awt.Point p1, java.awt.Point p2) {
        return new java.awt.Point(p1.x + p2.x, p1.y + p2.y);
    }

    public static java.awt.Point multiply(java.awt.Point p1, int scalar) {
        return new java.awt.Point(p1.x * scalar, p1.y * scalar);
    }
}
