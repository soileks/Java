package org.example;

//import org.apache.tools.ant.taskdefs.condition.Socket;

import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    int port = 3124;// порт по которому будет прослушивать сервер
    InetAddress ip = null; //ip адрес для сервера

    public void StartServer() {
        ServerSocket ss; // для того чтобы начать прослушаивать соединение по определенному порта
        Socket cs;
        InputStream is;
        OutputStream os;
        DataOutputStream dos;
        DataInputStream dis;
       // ObjectInputStream ois; // так как обьекты передаются в бинарном виде, их сложно отладить. Лучше преобразовать в Json


        try{        //порождаем сервер
            ip = InetAddress.getLocalHost(); // запускаем сервер на ip локального хоста
            ss = new ServerSocket(port,0,ip); //backlog=0 значит, что подключений неограниченное число
            System.out.append("Server start\n");

            while(true) {
                cs = ss.accept(); //поток засыпает до тех пор пока не будет подключен клиент(ожидание клиента)
                // каждый клиент получает отдельный сокет для общения с сервером(но порты для каждого клиента разные)
                System.out.println("Client connect (" + cs.getPort() + ")");// смотрим порт клиента

                // на серверe получаем входной и выходной потоки
                is = cs.getInputStream();
                os = cs.getOutputStream();
                // обертки для передачи более сложных данных(можем передавать более сложные данные, чем просто поток байт)
                dis = new DataInputStream(is);
                //ois = new ObjectInputStream(is); // для принятия обьекта по сети
                dos = new DataOutputStream(os);


                String s = dis.readUTF(); // читаем строку с клиента
                //Action act = (Action) ois.readObject();


                System.out.println("Connect: " + s); // вывод строчки полученной с клиента

                Gson gson = new Gson();
                Action act = gson.fromJson(s, Action.class); //десиреализуем(воссоздаем) обьект action
                System.out.println(act);

                dos.writeUTF("hello from Server"); // отправляем клиенту привет с сервера
            }
        }catch(IOException ex){
            System.out.println("Error");
        }

    }

    public static void main(String[] args) {

        new MyServer().StartServer();
    }


}
