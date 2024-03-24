package com.example.socketpanel;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class mainServer {
    Model m = BModel.build();
    int port = 3124;// порт по которому будет прослушивать сервер
    InetAddress ip = null; //ip адрес для сервера
    public void StartServer() {
        ServerSocket ss; // для того чтобы начать прослушаивать соединение по определенному порта
        Socket cs;
        m.add(new Point(10,20));
        m.add(new Point(50,50));

        try{        //порождаем сервер
            ip = InetAddress.getLocalHost(); // запускаем сервер на ip локального хоста
            ss = new ServerSocket(port,0,ip); //backlog=0 значит, что подключений неограниченное число
            System.out.append("Server start\n");

            while(true) {
                cs = ss.accept(); //поток засыпает до тех пор пока не будет подключен клиент(ожидание клиента)
                // каждый клиент получает отдельный сокет для общения с сервером(но порты для каждого клиента разные)
                System.out.println("Client connect (" + cs.getPort() + ")");// смотрим порт клиента
                // если подсоединился клиент, то
                SocketClient scl = new SocketClient(cs,true);//класс для общения клента с сервером
                m.addObserver(
                        (model)->
                        {               // всем клиентам, которые подписаны на модель, отсылаются все точки
                            Resp r = new Resp(model.getPoints());
                            scl.sendResp(r);
                        });
            }
        }catch(IOException ex){
            System.out.println("Error");
        }

    }

    public static void main(String[] args) {
        mainServer ms = new mainServer();
        ms.StartServer();
    }
}
