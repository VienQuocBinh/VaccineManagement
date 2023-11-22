
package DTO;

import java.io.Serializable;


public class Student implements Serializable, iInfo {

    private String id; 
    private String name;
   
    

    public Student() {
        id = "";
        name = "";
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Override
    public void printStudent() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        
    }

    @Override
    public String toString() {
        return "Student ID: " + id + "\n" + "Student name: " + name + "\n";
    }
    
    
}
