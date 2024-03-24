package org.example;

//import java.io.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
        Socket socket  = new Socket("ya.ru",80);

        socket.setSoTimeout(2000);

        StringBuilder command = new StringBuilder("GET /index.html HTTP/1.1"); //get запрос главной странички
        command.append(System.lineSeparator());
        command.append("Host: ya.ru").append(System.lineSeparator());
        command.append("Connection: close").append(System.lineSeparator());
        command.append(System.lineSeparator());

        OutputStream os = socket.getOutputStream();// для отправки запроса на сервер
        os.write(command.toString().getBytes()); // отправляем массив байт

        InputStream is = socket.getInputStream(); // для получения ответа
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"cp1251")); // принимаем набор байт в виде строк с помощью BufferedReader
        String line = br.readLine();
        while(line != null){
            System.out.println(line);// читаем по строчкам пока не выведем все на экран
            try{
                line = br.readLine();
            }
            catch(SocketTimeoutException ex){
                ex.printStackTrace(System.out);
                break;
            }
        }
        os.close(); // закрываем сокет и потоки
        br.close();
        socket.close();
    } catch(IOException e){ //разрыв соединения или неправильное имя хоста, например
        System.err.println(e.getMessage());
        }
    }
}
