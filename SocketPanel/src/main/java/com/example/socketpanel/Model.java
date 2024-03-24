package com.example.socketpanel;

import java.util.ArrayList;
import java.util.Iterator;

public class Model implements Iterable<Point> { //модель нужна для прослушивания некоторых событий
    DAO dao=new DAO();
    ArrayList<IObserver> allO = new ArrayList<>();
    void event(){// будет оповещать слушателей, что событие произошло
        for(IObserver o:allO){
            o.event(this);
        }
    }
    public void addObserver(IObserver obs){ // добавим наблюдателя
        allO.add(obs);
    }
    void set(ArrayList<Point> allPoint){
        dao.set(allPoint);
        event();// оповещаем всех
    }
    public ArrayList<Point> getPoints(){
        return dao.getPoints();
    }


    public void add(Point p){
        dao.add(p);
        event();// оповещаем при обновлении данных или удалении
    }
    public void remove(Point p){
        dao.remove(p);
        event();
    }

    @Override
    public Iterator<Point> iterator() {
        return dao.iterator();
    }
}
