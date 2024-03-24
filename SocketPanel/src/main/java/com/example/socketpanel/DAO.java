package com.example.socketpanel;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

// класс для хранения данных модели(позволяет управлять точками)
public class DAO implements Iterable<Point> {
    //будем хранить окружности в виде массива точек
    ArrayList<Point> allPoint = new ArrayList<>();
    void set(ArrayList<Point> allPoint){//для замены массива(получаем с сервера)
        this.allPoint = allPoint;
    }
    public ArrayList<Point> getPoints(){
        return allPoint;
    }


    void add(Point p){
        allPoint.add(p);
    }
    void remove(Point p){
        allPoint.remove(p);
    }

    @Override
    public Iterator<Point> iterator() { //с помощью итератора проходим по всем точкам
        return allPoint.iterator();
    }


}
