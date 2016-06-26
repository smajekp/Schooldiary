package com.myapp.ui.profile;

import com.myapp.dao.GradeDao;
import com.myapp.dao.PresenceDao;
import com.myapp.dao.StudentDao;
import com.myapp.dao.TeacherDao;
import com.myapp.dto.Grade;
import com.myapp.dto.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Piotrek on 2016-06-11.
 */
public class Educator extends Profile {

    private static Educator instance = null;

    private Educator() {}

    public static Educator getInstance() {
        if (instance == null) {
            instance = new Educator();
        }
        return instance;
    }


    public void printMenu(String profileName) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("\n\t\t\t\t----- DZIENNIK ELEKTRONICZNY -----\n");

            TeacherDao teacherDao = new TeacherDao();
            List<Object> teachers = teacherDao.find();

            StudentDao studentDao = new StudentDao();
            List<Object> students = studentDao.find();

            PresenceDao presenceDao = new PresenceDao();

            GradeDao gradeDao = new GradeDao();

            String role = null;
            String name = null;
            String lastName = null;
            for (Object teacher : teachers) {
                if (((com.myapp.dto.Teacher) teacher).getLogin().equals("wychowawca") && ((com.myapp.dto.Teacher) teacher).getPassword().equals("wychowawca")) {
                    name = ((com.myapp.dto.Teacher) teacher).getName();
                    lastName = ((com.myapp.dto.Teacher) teacher).getLastName();
                    role = ((com.myapp.dto.Teacher) teacher).getRole();
                }
            }

            System.out.println("Zalogowany jako: " + " " + name + " " + lastName + " - " + role);

            System.out.println("Podaj opcję, którą chcesz wybrać.");
            System.out.println("1. SPRAWDŹ NIEOBECNOŚCI");
            System.out.println("2. DANE UCZNIÓW");
            System.out.println("3. SPRAWDŹ OCENY");
            System.out.println("4. DODAJ UCZNIA");
            System.out.println("5. USUŃ UCZNIA");
            System.out.println("6. EDYTUJ UCZNIA");
            System.out.println("7. WYLOGUJ");

            Boolean isError;

            String choiceTmp = br.readLine();

            if ((!choiceTmp.equals("1")) && (!choiceTmp.equals("2")) && (!choiceTmp.equals("3")) && (!choiceTmp.equals("4")) && (!choiceTmp.equals("5")) && (!choiceTmp.equals("6")) && (!choiceTmp.equals("7")))
                isError = true;
            else
                isError = false;

            if (isError == true) {
                System.out.println("Nie ma takiej opcji w menu.");

            } else {
                int choice = new Integer(choiceTmp).intValue();

                switch (choice) {
                    //sprawdz nieobecnosci
                    case 1: {
                        while(true) {
                            System.out.println("\t\t\t\t----- SPRAWDŹ NIEOBECNOŚCI -----\n");

                            for (Object student : students) {
                                System.out.println(((Student) student).getId() + ". " + ((Student) student).getName() + " " + ((Student) student).getLastName() + " " + ((Student) student).getAddress() + " " + ((Student) student).getHomeNumber() + " " + ((Student) student).getPhone());
                            }

                            System.out.println("Podaj numer z dziennika ucznia, lub wpisz 0 jeśli chcesz powrócić do menu");

                            int student = in.nextInt();

                            List<Object> presences = presenceDao.findByStudentId(student);

                            int studentsSize = students.size();

                            if (studentsSize >= student) {
                                for (Object presence : presences) {
                                    System.out.println(((com.myapp.dto.Presence) presence).getDate() + " - " + ((com.myapp.dto.Presence) presence).getSubject());
                                }
                            } else {
                                System.out.println("Nie ma takiego studenta");
                            }
                            if(student == 0){
                                break;
                            }
                        }
                        break;
                    }
                    //dane uczniow
                    case 2: {
                        System.out.println("\t\t\t\t----- DANE UCZNIÓW -----\n");

                        for (Object student : students) {
                            System.out.println(((Student) student).getId() + ". " + ((Student) student).getName() + " " + ((Student) student).getLastName() + " " + ((Student) student).getAddress() + " " + ((Student) student).getHomeNumber() + " " + ((Student) student).getPhone());
                        }
                        System.out.println("\nAby powrócić do menu naciśnij enter...");
                        Scanner scanner = new Scanner(System.in);
                        scanner.nextLine();

                        break;
                    }

                    //sprawdz oceny
                    case 3: {
                        while(true) {
                            System.out.println("\t\t\t\t----- SPRAWDŹ OCENY -----\n");
                            System.out.println("Podaj nazwę przedmiotu (np. Polski), lub wpisz 0 jeśli chcesz powrócić do menu");

                            String subject = br.readLine();
                            List<Grade> grades = gradeDao.findBySubject(subject);

                            if (grades != null) {
                                for (Object grade : grades) {
                                    System.out.println(((com.myapp.dto.Grade) grade).getStudent().getName() + " " + ((com.myapp.dto.Grade) grade).getStudent().getLastName() + " " + ((com.myapp.dto.Grade) grade).getGradeType() + " " + ((com.myapp.dto.Grade) grade).getGrade());
                                }
                            } else {
                                System.out.println("Nie ma takiego przedmiotu, lub wpisałeś/aś przedmiot z małej litery");
                            }
                            if(subject.equals("0")){
                                break;
                            }
                        }
                        break;
                    }

                    //dodaj ucznia
                    case 4: {
                        System.out.println("\t\t\t\t----- DODAWANIE UCZNIA -----\n");

                        System.out.println("Wpisz imię ucznia:");
                        String studentName = br.readLine();
                        System.out.println("Wpisz nazwisko ucznia:");
                        String studentLastName = br.readLine();
                        System.out.println("Wpisz adres zamieszkania ucznia:");
                        String studentAddress = br.readLine();
                        System.out.println("Wpisz numer telefonu domowego ucznia:");
                        int studentHomePhone = in.nextInt();
                        System.out.println("Wpisz numer telefonu ucznia:");
                        String studentPhone = br.readLine();

                        studentDao.save(new com.myapp.dto.Student(studentName, studentLastName, studentAddress, studentHomePhone,
                                studentPhone));

                        System.out.println("Uczeń dodany");

                        break;
                    }
                    //usuń ucznia
                    case 5: {
                        System.out.println("\t\t\t\t----- USUWANIE UCZNIA -----\n");
                        for (Object student : students) {
                            System.out.println(((com.myapp.dto.Student) student).getId() + ". " + ((com.myapp.dto.Student) student).getName() + " " + ((com.myapp.dto.Student) student).getLastName() + " " + ((com.myapp.dto.Student) student).getAddress() + " " + ((com.myapp.dto.Student) student).getHomeNumber() + " " + ((com.myapp.dto.Student) student).getPhone());
                        }
                        System.out.println("\nWpisz id ucznia, którego chcesz usunąć");

                        int studentId = in.nextInt();
                        studentDao.delete(studentId);

                        System.out.println("Nauczyciel został usunięty\n");
                        break;
                    }
                    //edytuj ucznia
                    case 6: {
                        while (true) {

                            List<Object> students2 = studentDao.find();

                            System.out.println("\t\t\t\t----- EDYCJA UCZNIA -----\n");
                            for (Object student : students2) {
                                System.out.println(((com.myapp.dto.Student) student).getId() + ". " + ((com.myapp.dto.Student) student).getName() + " " + ((com.myapp.dto.Student) student).getLastName() + " " + ((com.myapp.dto.Student) student).getAddress() + " " + ((com.myapp.dto.Student) student).getHomeNumber() + " " + ((com.myapp.dto.Student) student).getPhone());
                            }
                            System.out.println("\nWpisz id ucznia, którego chcesz edytować lub 0 jeśli chcesz zakończyć edycję uczniów");

                            int studentId = in.nextInt();
                            com.myapp.dto.Student student = (com.myapp.dto.Student) studentDao.find(studentId);

                            if (studentId == 0)
                                break;
                            if (student == null) {
                                System.out.println("Nie ma ucznia o takim id");
                            } else {
                                while (true) {

                                    System.out.println("Wybierz jedną z poniższych opcji");
                                    System.out.println("1. Edytuj imię ucznia");
                                    System.out.println("2. Edytuj nazwisko ucznia");
                                    System.out.println("3. Edytuj adres ucznia");
                                    System.out.println("4. Edytuj numer telefonu domowego ucznia");
                                    System.out.println("5. Edytuj numer telefonu ucznia");
                                    System.out.println("6. Zakończ edycję tego ucznia");
                                    String choiceEdit = br.readLine();

                                    if (choiceEdit.equals("1")) {

                                        while (true) {
                                            System.out.println("Wpisz nowe imię ucznia:");
                                            String newName = br.readLine();

                                            if (newName.matches("[a-zA-Z]+$")) {
                                                student.setName(newName);
                                                studentDao.update(student);
                                                break;
                                            } else
                                                System.out.println("Imię musi się składać wyłącznie z liter\n");
                                        }

                                        System.out.println("Imię zostało zaktualizowane\n");

                                    } else if (choiceEdit.equals("2")) {

                                        while (true) {
                                            System.out.println("Wpisz nowe nazwisko ucznia:");
                                            String LastName = br.readLine();

                                            if (LastName.matches("[a-zA-Z]+$")) {
                                                student.setLastName(LastName);
                                                studentDao.update(student);
                                                break;
                                            } else
                                                System.out.println("Nazwisko musi się składać wyłącznie z liter\n");
                                        }

                                        System.out.println("Nazwisko zostało zaktualizowane\n");

                                    } else if (choiceEdit.equals("3")) {

                                        System.out.println("Wpisz nowy adres ucznia:");
                                        String address = br.readLine();
                                        student.setAddress(address);
                                        studentDao.update(student);
                                        System.out.println("Adres został zaktualizowany\n");

                                    } else if (choiceEdit.equals("4")) {
                                        while (true) {
                                            System.out.println("Wpisz nowy numer telefonu domowego ucznia:");
                                            int phoneNumber = in.nextInt();
                                            int length = (int) Math.log10(phoneNumber) + 1;

                                            if (length == 9) {
                                                    student.setHomeNumber(phoneNumber);
                                                    studentDao.update(student);
                                                    break;
                                            } else
                                                System.out.println("Numer telefonu domowego musi się składać tylko z 9 cyfr\n");
                                        }
                                        System.out.println("Numer telefonu domowego został zaktualizowany\n");

                                    } else if (choiceEdit.equals("5")) {
                                        while (true) {
                                            System.out.println("Wpisz nowy numer telefonu ucznia:");
                                            String phoneNumber = br.readLine();

                                            if (phoneNumber.length() == 9) {

                                                if (phoneNumber.matches("[0-9]+$")) {
                                                    student.setPhone(phoneNumber);
                                                    studentDao.update(student);
                                                    break;
                                                } else
                                                    System.out.println("Numer telefonu musi się składać wyłącznie z cyfr\n");
                                            } else
                                                System.out.println("Numer telefonu musi się składać tylko z 9 cyfr\n");
                                        }
                                        System.out.println("Numer telefonu został zaktualizowany\n");

                                    } else if (choiceEdit.equals("6")) {

                                        break;

                                    } else if (!choiceEdit.equals("1") && !(choiceEdit.equals("2")) && !(choiceEdit.equals("3")) && !(choiceEdit.equals("4")) && !(choiceEdit.equals("5")) && !(choiceEdit.equals("6")) && !(choiceEdit.equals("7")) && !(choiceEdit.equals("8"))) {
                                        System.out.println("Możesz wybrać tylko opcję od 1 do 8...");
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 7: {
                        while (true) {
                            System.out.println("CZY NA PEWNO CHCESZ SIĘ WYLOGOWAĆ? (t/n)");

                            String choiceExit = br.readLine();

                            if (choiceExit.equals("t")) {
                                System.exit(0);
                            } else if (choiceExit.equals("n")) {
                                break;
                            } else if (!choiceExit.equals("n") && !(choiceExit.equals("t"))) {
                                System.out.println("Możesz wybrać tylko opcję t lub n...");
                            }
                        }
                        break;
                    }
                    default:
                        System.out.println("Nie ma takiej opcji, wybierz inny numer");
                }

            }
        }
    }
}

