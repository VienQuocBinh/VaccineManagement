
package Storage;

import DTO.Vaccine;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VaccineList implements Serializable {

    // Vi save tung Object nen doc tung Object
    public void saveFile(String fileName) {
        try {

            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            List<Vaccine> list = new ArrayList<>();
            list.add(new Vaccine("Covid-V001", "AstraZeneca"));
            list.add(new Vaccine("Covid-V002", "SPUTNIK V"));
            list.add(new Vaccine("Covid-V003", "Vero Cell"));
            list.add(new Vaccine("Covid-V004", "Pfizer"));
            list.add(new Vaccine("Covid-V005", "Moderna"));
            for (Vaccine vc : list) {
                oos.writeObject(vc);
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Vi save tung Object nen doc tung Object
    public ArrayList<Vaccine> readFile(String fileName) {
        ArrayList<Vaccine> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Trong file con phan tu thi read ra
            while (fis.available() > 0) {
                list.add((Vaccine) ois.readObject());
            }
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public Vaccine searchByID(String id) {
        ArrayList<Vaccine> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("vaccine.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                list.add((Vaccine) ois.readObject());
            }
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        for (Vaccine vc : list) {
            if (vc.getId().equals(id)) {
                return vc;
            }
        }
        return null;
    }

}
