package com.example.socketpanel;

import java.util.ArrayList;

public class MSG {
   ArrayList<Point> points;
   MsgAction action;// что мы хотим с точками сделать

   public MSG(ArrayList<Point> points, MsgAction action) {
      this.points = points;
      this.action = action;
   }

   public ArrayList<Point> getPoints() {
      return points;
   }

   public MsgAction getAction() {
      return action;
   }

   @Override
   public String toString() {
      return "MSG{" +
              "points=" + points +
              ", action=" + action +
              '}';
   }
}
