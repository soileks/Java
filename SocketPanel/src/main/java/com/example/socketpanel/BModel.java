package com.example.socketpanel;

public class BModel { // как бы getter для модели
    static Model m = new Model();

    public static Model build(){ //модель один раз создастся и будем получать из этого метода ее, где нам надо
        return m;
    }
}
