/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kozinove.json_example;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author evgen
 */
public class Student {
    int id = 0;
    String fio; 
    ArrayList<Integer> mark = new ArrayList<>(); 

    public Student(int id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
    
    
    public void addMark(int m){
        mark.add(m);
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", fio=" + fio + ", mark=[" + 
                mark.stream().map(Object::toString)
                    .collect(Collectors.joining(", "))
                + "]}";
    }
    
    
    
}
