package com.example.socketpanel;

import java.util.ArrayList;

public class Resp {
    ArrayList<Point> points;
    public Resp(){

    }

    public Resp(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
}
