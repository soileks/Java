/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kozinove.json_example;

/**
 *
 * @author evgen
 */
public class Student2  extends Student{
    String lable = "метка";

    public Student2(int id, String fio) {
        super(id, fio);
    }

    @Override
    public String toString() {
        return "Student2{" + super.toString()  + "lable=" + lable + '}';
    }
           
    
    
}
