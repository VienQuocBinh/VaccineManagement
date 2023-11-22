package Storage;

import DTO.Injection;
import MyService.Validation;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class InjectionList implements Serializable {

    private static final long serialVersionUID = 1L;

    ArrayList<Injection> list;

    public InjectionList() {
        list = new ArrayList<>();
    }

    public Injection searchInjectionID(int id) {
        for (Injection injection : list) {
            if (injection.getId() == id) {
                return injection;
            }
        }
        return null;
    }

    public ArrayList<Injection> searchStudentName(String studentName) {
        ArrayList<Injection> result = new ArrayList<>();
        for (Injection var : list) {
            if (var.getStudentName().contains(studentName)) {
                result.add(var);
            }
        }
        return result;
    }

    public String isStudentInjected(String studentID) {
        for (Injection injection : list) {
            if (injection.getStudentID().equals(studentID)) {
                return injection.getStudentID();
            }
        }
        return "";
    }

    public boolean addInjection(Injection in) {
        list.add(in);
        return true;
    }

    public void updateInjection(int injectionID) throws Exception {
        String ask;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == injectionID) {
                if (list.get(i).getSecondDate().isEmpty() && list.get(i).getSecondPlace().isEmpty()) {
                    Scanner sc = new Scanner(System.in);
                    boolean flag = false;

                    do {
                        try {
                            System.out.println("Enter vaccine id: ");
                            sc = new Scanner(System.in);
                            String vaccineId = sc.nextLine();
                            if (!Validation.isVaccineIdValid(vaccineId) || !list.get(i).getVaccineID().equals(vaccineId)) {
                                throw new Exception();
                            }
                            flag = false;
                        } catch (Exception e) {
                            System.out.println("Vaccine is not the same type with the first");
                            System.out.println("Do you want to continue(y/n): ");
                            sc = new Scanner(System.in);
                            ask = sc.nextLine();
                            if (ask.equalsIgnoreCase("y")) {
                                flag = true;
                            } else {
                                throw new Exception();
                            }
                        }
                    } while (flag);
                    do {
                        try {
                            System.out.println("Enter second date (dd-MM-yyyy): ");
                            sc = new Scanner(System.in);
                            String secondDate = sc.nextLine();
                            if (!Validation.isDateValid(secondDate) || !Validation.isSecondDateValid(list.get(i).getFirstDate(), secondDate)) {
                                throw new Exception();
                            }
                            list.get(i).setSecondDate(secondDate);
                            flag = false;
                        } catch (Exception e) {
                            System.out.println("Second date is invalid");
                            System.out.println("Do you want to continue(y/n): ");
                            sc = new Scanner(System.in);
                            ask = sc.nextLine();
                            if (ask.equalsIgnoreCase("y")) {
                                flag = true;
                            } else {
                                throw new Exception();
                            }
                        }
                    } while (flag);
                    do {
                        try {
                            System.out.println("Enter second place: ");
                            sc = new Scanner(System.in);
                            String secondPlace = sc.nextLine();
                            if (secondPlace.isEmpty()) {
                                throw new Exception();
                            }
                            list.get(i).setSecondPlace(secondPlace);
                            flag = false;
                        } catch (Exception e) {
                            System.out.println("Second place is invalid");
                            System.out.println("Do you want to continue(y/n): ");
                            sc = new Scanner(System.in);
                            ask = sc.nextLine();
                            if (ask.equalsIgnoreCase("y")) {
                                flag = true;
                            } else {
                                throw new Exception();
                            }
                        }
                    } while (flag);

                } else {
                    System.out.println("Student has completed 2 injections");
                }

            }
        }

    }

    public boolean deleteInjection(int injectionId) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == injectionId) {
                list.remove(list.get(i));
                return true;
            }
        }

        return false;
    }

    public Injection searchInjectionByStudentID(String studentID) {
        for (Injection injection : list) {
            if (injection.getStudentID().equalsIgnoreCase(studentID)) {
                return injection;
            }
        }
        return null;
    }

    // Vi save tung Object nen doc tung Object 
    public void saveFile(String filename) throws Exception {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Injection injection : list) {
            oos.writeObject(injection);
        }
        fos.close();
        oos.close();
    }

    public void saveEncryptFile(String filename) throws Exception {
        String s;
        FileWriter fw = new FileWriter(filename);
        PrintWriter pw = new PrintWriter(fw);
        for (Injection injection : list) {
            s = Validation.encryptInjection(injection);
            pw.println(s);
        }
        fw.close();
        pw.close();
    }
    
    public ArrayList<String> readEncryptFile(String filename) throws FileNotFoundException, IOException{
        ArrayList<String> list = new ArrayList<>();
        FileReader f = new FileReader(filename);
        BufferedReader bf = new BufferedReader(f);
        while(bf.ready()){
            String s = bf.readLine();
            String[] temp = s.split("\n");
            if(temp.length==1){
               list.add(s);
            }
        }
        f.close();
        return list;
    }

    // Vi save tung Object nen doc tung Object
    public ArrayList<Injection> readFile(String fileName) {
        ArrayList<Injection> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Trong file con phan tu thi read ra
            while (fis.available() > 0) {
                list.add((Injection) ois.readObject());
            }
            fis.close();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void print() {
        if (list == null) {
            System.out.println("List is empty");
        } else {
            for (Injection injection : list) {
                System.out.println(injection);
            }
        }
    }

}
