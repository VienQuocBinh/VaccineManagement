
package DTO;

import MyService.Validation;
import Storage.InjectionList;
import Storage.StudentList;
import Storage.VaccineList;
import java.io.Serializable;
import java.util.Scanner;

public class Injection implements Serializable {

    private int id;
    private String studentID;
    private String vaccineID;
    private String firstPlace;
    private String secondPlace;
    private String firstDate;
    private String secondDate;
    Student student;
    Vaccine vaccine;
    StudentList studentList = new StudentList();
    VaccineList vaccineList = new VaccineList();
    ;
    InjectionList injectionList = new InjectionList();

    public Injection(int id, Student s, Vaccine v, String firstPlace, String firstDate) {
        this.id = id;
        this.studentID = s.getId();
        this.vaccineID = v.getId();
        this.firstPlace = firstPlace;
        this.firstDate = firstDate;
        this.secondDate = "";
        this.secondPlace = "";
    }

    public Injection() {
        id = 0;
        student = null;
        vaccine = null;
        firstDate = "";
        secondDate = "";
        firstPlace = "";
        secondPlace = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }
    
    public String getStudentName(){
        return studentList.searchByID(this.studentID).getName();
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(String secondDate) {
        this.secondDate = secondDate;
    }

    public void inputInjection() throws Exception {

        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        String ask;
        do {
            try {

                System.out.println("Enter vaccine id (Covid-Vxxx): ");
                sc = new Scanner(System.in);
                vaccineID = sc.nextLine();
                if (vaccineList.searchByID(vaccineID) == null || !Validation.isVaccineIdValid(vaccineID)) {
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("Vaccine does not exist");
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
                System.out.println("Enter first date(dd-MM-yyyy): ");
                sc = new Scanner(System.in);
                firstDate = sc.nextLine();
                if (!Validation.isDateValid(firstDate)) {
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("Plese enter in right format and valid date");
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
                System.out.println("Enter first place: ");
                sc = new Scanner(System.in);
                firstPlace = sc.nextLine();
                if (firstPlace.isBlank()) {
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("Empty string");
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
    }

    @Override
    public String toString() {
        if (this.getSecondDate().isEmpty() || this.getSecondPlace().isEmpty()) {
            return "\nInjection ID: " + this.getId()
                    + "\nFirst information\n"
                    + "Student ID: " + this.studentID + ", "
                    + "Student name: " + studentList.searchByID(this.studentID).getName() + ", "
                    + "Vaccine ID: " + this.vaccineID + ", "
                    + "Vaccine name: " + vaccineList.searchByID(this.vaccineID).getName() + ", "
                    + "First place: " + this.getFirstPlace() + ", "
                    + "First date: " + this.getFirstDate()
                    + "\nSecond infomation: N/A"
                    + "\nStatus: Not complete";
        } else {
            return "\nInjection ID: " + this.getId()
                    + "\nFirst information\n"
                    + "Student ID: " + this.studentID + ", "
                    + "Student name: " + studentList.searchByID(this.studentID).getName() + ", "
                    + "Vaccine ID: " + this.vaccineID + ", "
                    + "First place: " + this.getFirstPlace() + ", "
                    + "First date: " + this.getFirstDate()
                    + "\nSecond infomation:\n"
                    + "Vaccine ID: " + this.vaccineID + ", "
                    + "Vaccine name: " + vaccineList.searchByID(this.vaccineID).getName() + ", "
                    + "Second place: " + this.getSecondPlace() + ", "
                    + "Second date: " + this.getSecondDate()
                    + "\nStatus: complete";
        }
    }
}
