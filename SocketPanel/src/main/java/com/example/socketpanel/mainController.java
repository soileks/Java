package com.example.socketpanel;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class mainController implements IObserver {// наблюдает за моделью
Model m = BModel.build();
SocketClient scl;
int port = 3124;
InetAddress ip = null;

public void initialize(){//для прослушивания события
  m.addObserver(this);
}

@FXML
Pane viewPoints;

@FXML
void connect(){//для подключения к клиенту
    if(scl!=null) return;// если уже есть подключение, то игнорируем
    // создадим подключение
    try{
        Socket cs;
        ip = InetAddress.getLocalHost();
        cs = new Socket(ip, port);
        System.out.append("Client start \n");
        scl = new SocketClient(cs,false);
        scl.sendMsg(new MSG(null,MsgAction.GET));
    }catch(IOException ex){
        System.out.println("Error connect");
    }

}

 @FXML
 void mouseEvnt(MouseEvent evn){
    if(scl!=null){
        ArrayList<Point> allp = new ArrayList<>();
        allp.add(new Point((int) evn.getX(), (int) evn.getY()));
        scl.sendMsg(new MSG(allp,MsgAction.ADD)); //с помощью массива мы можем передать несколько точек
        //т.е. добавляются все точки , вызывается у модели событие связанное с изменением. На сервере автоматически модель добавляется
    }
    else {

        m.add(new Point((int) evn.getX(), (int) evn.getY())); //добавление точки в модель
    }
}

 @Override
 public void event(Model m) {// реакция на изменение событий - добавление точек в модель
     Platform.runLater(
             ()->{
   viewPoints.getChildren().removeAll();
   for(Point p:m) {
       Circle circle = new Circle(p.getX(), p.getY(), 10);
       circle.setFill(Color.RED);
       viewPoints.getChildren().add(circle);
   }
   }
   );

 }
}
