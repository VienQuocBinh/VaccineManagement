
package Storage;

import DTO.Student;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentList implements Serializable {


    // Vi save tung Object nen doc tung Object
    public void saveFile(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            List<Student> list = new ArrayList<>();
            list.add(new Student("SE15000", "Huy"));
            list.add(new Student("SE16000", "Vien Quoc Binh"));
            list.add(new Student("SE16001", "Vu Quoc Binh"));
            list.add(new Student("SE17000", "Hai Long"));
            list.add(new Student("SE15555", "Tan Tai"));
            list.add(new Student("SE16111", "Pham Quang"));
           
            for (Student st : list) {
                oos.writeObject(st);
            }
            oos.close();
            fos.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Vi save tung Object nen doc tung Object
    public ArrayList<Student> readFile(String fileName) {
        ArrayList<Student> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Trong file con phan tu thi read ra
            while (fis.available() > 0) {
                list.add((Student) ois.readObject());
            }
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public Student searchByID(String id) {
        ArrayList<Student> list = new ArrayList<>();
        Student result = new Student();
        try {
            FileInputStream fis = new FileInputStream("student.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                list.add((Student) ois.readObject());
            }
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        for (Student st : list) {
            if (st.getId().equals(id)) {
                result = st;
            }
        }
        return result;
    }
    
}
