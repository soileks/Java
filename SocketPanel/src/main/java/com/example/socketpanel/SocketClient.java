package com.example.socketpanel;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketClient {
    Model m = BModel.build(); // есть доступ к модели(для оповещения клиента о  событиях)
    Gson gson = new Gson();
    ServerSocket ss; // для того чтобы начать прослушаивать соединение по определенному порта
    Socket cs;
    InputStream is;
    OutputStream os;
    DataOutputStream dos;
    DataInputStream dis;
    boolean isServer = true;

    public SocketClient(Socket cs,boolean isServer) {
        this.cs = cs;
        this.isServer = isServer;
        try {
            os =cs.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException e) {
            System.out.println("Error SocketClient(Socket cs)");
        }
        new Thread(()->{run();}).start();
    }
    void run(){// бесконечное прослушивание сообщений
        try {
            is = cs.getInputStream();
        } catch (IOException e) {
            System.out.println("Error run");
        }
        dis = new DataInputStream(is);
        while (true){
            if(isServer){
                MSG msg = readMsg();
                switch(msg.getAction()){
                    case GET:// получили сообщение от клиента на получение всех точек
                        Resp resp = new Resp(m.getPoints());
                        sendResp(resp);
                        break;
                    case ADD:// получили сообщение от клиента на добавление точек
                        for(Point p: msg.getPoints()){
                            m.add(p);//добавляем точки в модель
                        }
                        break;
                }

            }
            else{// на стороне клиента просто ждем ответа

            Resp r = readResp();// ждем ответа через метод, который вызывает чтение прихода результата
            m.set(r.getPoints());//устанавливаем в модель массив, который пришел
            }
        }

    }
    public MSG readMsg(){
        MSG r = null;
        try {
            String respStr = dis.readUTF();
            r = gson.fromJson(respStr,MSG.class);

        } catch (IOException e) {
            System.out.println("Error read");
        }
        return r;

    }
    public void sendResp(Resp msg){
        String strMsg = gson.toJson(msg);
        try {
            dos.writeUTF(strMsg);
        } catch (IOException e) {
            System.out.println("Error send");
        }

    }

    public  Resp readResp(){// для возвращения ответа
        Resp r = null;
        try {
            String respStr = dis.readUTF();
            r = gson.fromJson(respStr,Resp.class);

        } catch (IOException e) {
            System.out.println("Error read");
        }
        return r;
    }
    public void sendMsg(MSG msg){
        String strMsg = gson.toJson(msg);
        try {
            dos.writeUTF(strMsg);
        } catch (IOException e) {
            System.out.println("Error send");
        }

    }


}
