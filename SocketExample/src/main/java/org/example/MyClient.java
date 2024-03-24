package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class MyClient {

    int port = 3124;
    InetAddress ip = null;

    public void ClientStart(){

        Socket cs;
        InputStream is;
        OutputStream os;
        DataOutputStream dos;
        DataInputStream dis;
       // ObjectOutputStream oos;

        try{
            ip = InetAddress.getLocalHost();
            cs = new Socket(ip, port);

            System.out.append("Client start \n");
            is = cs.getInputStream();
            os = cs.getOutputStream();

            dis = new DataInputStream(is);
            //oos = new ObjectOutputStream(os); // для передачи обьекта в виде потока байт
            dos = new DataOutputStream(os);

            System.out.println("Client send message");

            Action act = new Action(TypeAction.F1,"Hi!!!"); // обьект, который хотим передать

            Gson gson = new Gson();
            String obj_str = gson.toJson(act); //получаем строчку за счет сериализации


            //oos.writeObject(act);
           // oos.flush();
            dos.writeUTF(obj_str);//клиент отправялет серверу привет

            System.out.println("Read message");
            String s = dis.readUTF(); // читаем сообщение от сервера
            System.out.println("Message from server: "+ s);

        }catch(IOException ex){
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        new MyClient().ClientStart();
    }
}
