package org.example;

import java.io.Serializable;

public class Action implements Serializable { // имплементирует Serializable(сериализуем - значит преобразован в поток байт)
                                                // для поддержки передачи обьектов
    TypeAction ta;

    String msg = "";

    public Action(TypeAction ta, String msg) {
        this.ta = ta;
        this.msg = msg;
    }

    public TypeAction getTa() {
        return ta;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTa(TypeAction ta) {
        this.ta = ta;
    }

    @Override
    public String toString() {
        return "Action{" +
                "ta=" + ta +
                ", msg='" + msg + '\'' +
                '}';
    }
}
