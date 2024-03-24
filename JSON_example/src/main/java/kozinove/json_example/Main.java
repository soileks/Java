/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kozinove.json_example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

/**
 *
 * @author evgen
 */
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student2(123, "Иванов И");
        s1.addMark(5);
        s1.addMark(4);
        s1.addMark(5);
        System.out.println(s1);
        
        TypeToken<Student> tt = new TypeToken<>(){}; // для сериализации(тайптокен позволяет генерировать доп поля в json по типам данных)
        
        RuntimeTypeAdapterFactory<Student> typeFactory = RuntimeTypeAdapterFactory
                .of(Student.class, "type") //type это поле обозначающее сериализуемый класс
                .registerSubtype(Student2.class); //регистрирует класс студента и его подкласс

        
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory)
                      .setPrettyPrinting().create();//регистрируется фабрика типов
        
        String str_obj = gson.toJson(s1, tt.getType());// обьект сериализуется
        
        System.out.println(str_obj);//печать строчки сериализации
        
        
        Student s2;
        s2 = gson.fromJson(str_obj, tt.getType()); //десиреализуем обьект и печатаем
        System.out.println(s2);
        
    }
}
