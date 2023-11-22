package GUI;

import DTO.Injection;
import MyService.Validation;
import Storage.InjectionList;
import Storage.StudentList;
import Storage.VaccineList;
import java.util.ArrayList;
import java.util.Scanner;

public class VaccineManagement {

    public static void main(String[] args) {
        final String fileInjection = "injection.dat";
        Scanner sc = new Scanner(System.in);
        InjectionList listOfInjections = new InjectionList();
        StudentList listOfStudent = new StudentList();
        VaccineList listOfVaccine = new VaccineList();
        listOfStudent.saveFile("student.dat");
        listOfVaccine.saveFile("vaccine.dat");
        ArrayList<Injection> file = new ArrayList<>();
        ArrayList<String> fileEnr = new ArrayList<>();
        try {
            file = listOfInjections.readFile(fileInjection);
            for (Injection injection : file) {
                listOfInjections.addInjection(injection);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        boolean flag = false;

        int choice = 0;
        do {
            System.out.println("1. Show injection information");
            System.out.println("2. Add new injection");
            System.out.println("3. Update injection");
            System.out.println("4. Delete injection");
            System.out.println("5. Search injection by student infomation");
            System.out.println("6. Encrypt and Store data to file (.txt)");
            System.out.println("Others quit");
            do {
                try {
                    System.out.println("Select option: ");
                    sc = new Scanner(System.in);
                    choice = sc.nextInt();
                    if (choice <= 0) {
                        throw new Exception();
                    }
                    flag = false;
                } catch (Exception e) {
                    System.out.println("Please enter the valid number");
                    flag = true;
                }

            } while (flag);

            switch (choice) {

                case 1:
                    ArrayList<Injection> result = listOfInjections.readFile(fileInjection);
                    System.out.println(result);
                    try {
                        fileEnr = listOfInjections.readEncryptFile("encrypt.txt");
                        for (String string : fileEnr) {
                            System.out.println("Enrcypt: " + string);
                        }
                    } catch (Exception e) {
                    }
                    break;

                case 2:
                    String ask = "";
                    do {
                        try {
                            Injection in = new Injection();
                            flag = false;
                            do {
                                try {
                                    System.out.println("Enter injection id: ");
                                    sc = new Scanner(System.in);
                                    int id = sc.nextInt();
                                    Injection check = listOfInjections.searchInjectionID(id);
                                    if (id <= 0) {
                                        throw new Exception("Injection id is invalid");
                                    }
                                    if (check != null) {
                                        throw new Exception("Injection already has in the list");
                                    }
                                    in.setId(id);
                                    flag = false;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Do you want to create new injection or go back to the menu(y/n)");
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
                                    System.out.println("Enter student id (SExxxxx): ");
                                    sc = new Scanner(System.in);
                                    String studentID = sc.nextLine();
                                    String injectedStudent = listOfInjections.isStudentInjected(studentID);
                                    if (listOfStudent.searchByID(studentID).getId().isEmpty() || !Validation.isStudentIdValid(studentID)) {
                                        throw new Exception("Student does not exist");
                                    }
                                    if (!injectedStudent.isBlank()) {
                                        throw new Exception("Student already had injection");
                                    } else {
                                        in.setStudentID(studentID);
                                        flag = false;
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Do you want to create new injection (y/n)");
                                    sc = new Scanner(System.in);
                                    ask = sc.nextLine();
                                    if (ask.equalsIgnoreCase("y")) {
                                        flag = true;
                                    } else {
                                        throw new Exception();
                                    }
                                }
                            } while (flag);
                            in.inputInjection();
                            listOfInjections.addInjection(in);
                            System.out.println("Success");
                            try {
                                listOfInjections.saveFile(fileInjection);
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                            System.out.println("Do you want to create new injection or go back to the menu(y/n)");
                            sc = new Scanner(System.in);
                            ask = sc.nextLine();
                            flag = ask.equalsIgnoreCase("y");
                        } catch (Exception e) {
                            System.out.println("Add fail");
                            break;
                        }
                    } while (flag);

                    break;
                case 3:
                    int injectionId;
                    do {
                        try {
                            System.out.println("Enter injection id to update: ");
                            sc = new Scanner(System.in);
                            injectionId = sc.nextInt();
                            if (listOfInjections.searchInjectionID(injectionId) == null) {
                                System.out.println("Injection does not exist");
                                System.out.println("Do you want to continue(y/n): ");
                                sc = new Scanner(System.in);
                                ask = sc.nextLine();
                                if (ask.equalsIgnoreCase("y")) {
                                    flag = true;
                                } else {
                                    throw new Exception();
                                }
                            } else {
                                listOfInjections.updateInjection(injectionId);
                                System.out.println("Updated");
                                try {
                                    listOfInjections.saveFile(fileInjection);
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                                flag = false;
                            }

                        } catch (Exception e) {
                            System.out.println("Update fail");
                            flag = false;
                        }
                    } while (flag);

                    break;
                case 4:
                    do {
                        try {
                            System.out.println("Enter injection id: ");
                            sc = new Scanner(System.in);
                            int injectionID = sc.nextInt();
                            if (Validation.confirmMessage() && listOfInjections.deleteInjection(injectionID)) {
                                System.out.println("Delete success");
                                try {
                                    listOfInjections.saveFile(fileInjection);
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            } else {
                                System.out.println("Delete Fail, injection is not exist");
                                throw new Exception();
                            }
                            flag = false;
                        } catch (Exception e) {
                            System.out.println("Do you want to continue: (y/n)");
                            sc = new Scanner(System.in);
                            ask = sc.nextLine();
                            flag = ask.equalsIgnoreCase("y");
                        }
                    } while (flag);

                    break;
                case 5:
                    int choice_upgrade;
                    flag = false;
                    do {

                        try {
                            System.out.println("1. Search Student by ID");
                            System.out.println("2. Search Student by name");
                            System.out.println("Enter you choice: ");
                            sc = new Scanner(System.in);
                            choice_upgrade = sc.nextInt();
                            if (choice_upgrade <= 0 || choice_upgrade >= 3) {
                                throw new Exception();
                            }
                            switch (choice_upgrade) {
                                case 1:
                                    // by id
                                    do {

                                        try {
                                            System.out.println("Enter Student ID (SExxxxx): ");
                                            sc = new Scanner(System.in);
                                            String studentId = sc.nextLine();
                                            if (!listOfStudent.searchByID(studentId).getId().isEmpty() && listOfInjections.searchInjectionByStudentID(studentId) != null && Validation.isStudentIdValid(studentId)) {
                                                System.out.println(listOfInjections.searchInjectionByStudentID(studentId));

                                            } else {
                                                throw new Exception();
                                            }
                                            flag = false;
                                        } catch (Exception e) {
                                            System.out.println("This student does not have injection");
                                            System.out.println("Do you want to continue(y/n): ");
                                            sc = new Scanner(System.in);
                                            String con = sc.nextLine();
                                            flag = con.equalsIgnoreCase("y");
                                        }
                                    } while (flag);
                                    break;

                                case 2:
                                    //by name
                                    do {
                                        try {
                                            System.out.println("Enter student name: ");
                                            sc = new Scanner(System.in);
                                            String studentName = sc.nextLine();
                                            ArrayList<Injection> resultList = listOfInjections.searchStudentName(studentName);
                                            if (resultList != null) {
                                                for (Injection injection : resultList) {
                                                    System.out.println(injection);
                                                }
                                            }

                                            flag = false;
                                        } catch (Exception e) {
                                            System.out.println("Don't have any student matches the name");
                                            System.out.println("Do you want to continue(y/n): ");
                                            sc = new Scanner(System.in);
                                            String con = sc.nextLine();
                                            flag = con.equalsIgnoreCase("y");
                                        }
                                    } while (flag);
                                    break;
                            }
                            flag = false;
                        } catch (Exception e) {
                            System.out.println("Wrong input");
                            System.out.println("Do you want to continue(y/n): ");
                            sc = new Scanner(System.in);
                            String con = sc.nextLine();
                            flag = con.equalsIgnoreCase("y");
                        }

                    } while (flag);

                    break;
                case 6: {
                    try {
                        listOfInjections.saveEncryptFile("encrypt.txt");
//                        listOfInjections.saveFile(fileInjection);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                break;

            }

        } while (choice > 0 && choice <= 6);

    }
}
