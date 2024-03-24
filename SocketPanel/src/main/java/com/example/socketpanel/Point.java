package com.example.socketpanel;

public class Point {
    public int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public Point() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {// чтобы видеть какая точка передавалась
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
