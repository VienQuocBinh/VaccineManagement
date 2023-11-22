
package DTO;

import java.io.Serializable;

public class Vaccine implements Serializable{

    private String id; 
    private String name;

    public Vaccine() {
        id = "";
        name = "";
    }

    public Vaccine(String id, String name) {
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
    public String toString() {
        return "Vaccine ID: " + id + "\n" + "Vaccine name: " + name +"\n";
    }
    
}
