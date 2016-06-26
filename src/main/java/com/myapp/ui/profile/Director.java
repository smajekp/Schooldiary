package com.myapp.ui.profile;

import com.myapp.dao.StudentDao;
import com.myapp.dao.TeacherDao;
import com.myapp.dto.Student;
import com.myapp.dto.Teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Piotrek on 2016-06-11.
 */
public class Director extends Profile {

    private static Director instance = null;

    private Director() {}

    public static Director getInstance() {
        if (instance == null) {
            instance = new Director();
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

            String role = null;
            String name = null;
            String lastName = null;
            for (Object teacher : teachers) {
                if (((Teacher) teacher).getLogin().equals("dyrektor") && ((Teacher) teacher).getPassword().equals("dyrektor")) {
                    name = ((Teacher) teacher).getName();
                    lastName = ((Teacher) teacher).getLastName();
                    role = ((Teacher) teacher).getRole();
                }
            }

            System.out.println("Zalogowany jako: " + " " + name + " " + lastName + " - " + role);

            System.out.println("Podaj opcję, którą chcesz wybrać.\n");
            System.out.println("1. DANE UCZNIÓW");
            System.out.println("2. DANE NAUCZYCIELI");
            System.out.println("3. DODAJ NAUCZYCIELA");
            System.out.println("4. USUŃ NAUCZYCIELA");
            System.out.println("5. EDYTUJ NAUCZYCIELA");
            System.out.println("6. WYLOGUJ");

            Boolean isError;

            String choiceTmp = br.readLine();

            if ((!choiceTmp.equals("1")) && (!choiceTmp.equals("2")) && (!choiceTmp.equals("3")) && (!choiceTmp.equals("4")) && (!choiceTmp.equals("5")) && (!choiceTmp.equals("6")))
                isError = true;
            else
                isError = false;

            if (isError == true) {
                System.out.println("Nie ma takiej opcji w menu.");

            } else{
                int choice = new Integer(choiceTmp).intValue();

                switch (choice) {

                    //dane uczniow
                    case 1: {
                        System.out.println("\t\t\t\t----- DANE UCZNIÓW -----\n");
                        StudentDao studentDao = new StudentDao();
                        List<Object> students = studentDao.find();

                        for (Object student : students) {
                            System.out.println(((Student) student).getId() + ". " + ((Student) student).getName() + " " + ((Student) student).getLastName() + " " + ((Student) student).getAddress() + " " + ((Student) student).getHomeNumber() + " " + ((Student) student).getPhone());

                        }
                        System.out.println("\nAby powrócić do menu naciśnij enter...");
                        Scanner scanner = new Scanner(System.in);
                        scanner.nextLine();

                        break;


                    }
                    //dane nauczycieli
                    case 2: {
                        System.out.println("\t\t\t\t----- DANE NAUCZYCIELI -----\n");

                        for (Object teacher : teachers) {
                            System.out.println(((Teacher) teacher).getId() + ". " + ((Teacher) teacher).getName() + " " + ((Teacher) teacher).getLastName() + " " + ((Teacher) teacher).getRole() + " " + ((Teacher) teacher).getSubject() + " " + ((Teacher) teacher).getAddress() + " " + ((Teacher) teacher).getPhone());
                        }
                        System.out.println("\nAby powrócić do menu naciśnij enter...");
                        Scanner scanner = new Scanner(System.in);
                        scanner.nextLine();

                        break;
                    }
                    //dodaj nauczyciela
                    case 3: {
                        System.out.println("\t\t\t\t----- DODAWANIE NAUCZYCIELA -----\n");

                        System.out.println("Wpisz login dla nauczyciela:");
                        String teacherLogin = br.readLine();
                        System.out.println("Wpisz hasło dla nauczyciela:");
                        String teacherPassword = br.readLine();
                        System.out.println("Wpisz imię nauczyciela:");
                        String teacherName = br.readLine();
                        System.out.println("Wpisz nazwisko nauczyciela:");
                        String teacherLastName = br.readLine();
                        String teacherRole = "Nauczyciel";
                        System.out.println("Wpisz przedmiot, którego uczy nauczyciel:");
                        String teacherSubject = br.readLine();
                        System.out.println("Wpisz adres zamieszkania nauczyciela:");
                        String teacherAddress = br.readLine();
                        System.out.println("Wpisz numer telefonu nauczyciela:");
                        String teacherPhone = br.readLine();


                        teacherDao.save(new Teacher(teacherLogin, teacherPassword, teacherName, teacherLastName,
                                teacherRole, teacherSubject, teacherAddress, teacherPhone));

                        System.out.println("Nauczyciel dodany");

                        break;
                    }
                    //usuń nauczyciela
                    case 4: {
                        System.out.println("\t\t\t\t----- USUWANIE NAUCZYCIELA -----\n");
                        for (Object teacher : teachers) {
                            System.out.println(((Teacher) teacher).getId() + ". " + ((Teacher) teacher).getName() + " " + ((Teacher) teacher).getLastName() + " " + ((Teacher) teacher).getRole() + " " + ((Teacher) teacher).getSubject() + " " + ((Teacher) teacher).getAddress() + " " + ((Teacher) teacher).getPhone());
                        }
                        System.out.println("\nWpisz id nauczyciela, którego chcesz usunąć");

                        int teacherId = in.nextInt();
                        teacherDao.delete(teacherId);

                        System.out.println("Nauczyciel został usunięty\n");
                        break;
                    }
                    //edytuj nauczyciela
                    case 5: {
                        while(true) {

                            List<Object> teachers2 = teacherDao.find();

                            System.out.println("\t\t\t\t----- EDYCJA NAUCZYCIELA -----\n");
                            for (Object teacher : teachers2) {
                                System.out.println(((Teacher) teacher).getId() + ". " + ((Teacher) teacher).getName() + " " + ((Teacher) teacher).getLastName() + " " + ((Teacher) teacher).getRole() + " " + ((Teacher) teacher).getSubject() + " " + ((Teacher) teacher).getAddress() + " " + ((Teacher) teacher).getPhone());
                            }
                            System.out.println("\nWpisz id nauczyciela, którego chcesz edytować lub 0 jeśli chcesz zakończyć edycję nauczycieli");

                            int teacherId = in.nextInt();
                            Teacher teacher = (Teacher) teacherDao.find(teacherId);

                            if(teacherId == 0 )
                                break;
                            if(teacher == null){
                                System.out.println("Nie ma nauczyciela o takim id");
                            }
                            else
                            {
                                while(true) {

                                    System.out.println("Wybierz jedną z poniższych opcji");
                                    System.out.println("1. Edytuj login nauczyciela");
                                    System.out.println("2. Edytuj hasło nauczyciela");
                                    System.out.println("3. Edytuj imię nauczyciela");
                                    System.out.println("4. Edytuj nazwisko nauczyciela");
                                    System.out.println("5. Edytuj przedmiot nauczyciela");
                                    System.out.println("6. Edytuj adres nauczyciela");
                                    System.out.println("7. Edytuj numer telefonu nauczyciela");
                                    System.out.println("8. Zakończ edycję tego nauczyciela");
                                    String choiceEdit = br.readLine();

                                    if (choiceEdit.equals("1")) {

                                        System.out.println("Wpisz nowy login nauczyciela:");
                                        String newLogin = br.readLine();
                                        teacher.setLogin(newLogin);
                                        teacherDao.update(teacher);
                                        System.out.println("Login został zaktualizowany\n");

                                    } else if (choiceEdit.equals("2")) {

                                        System.out.println("Wpisz nowe hasło nauczyciela:");
                                        String newPassword = br.readLine();
                                        teacher.setPassword(newPassword);
                                        teacherDao.update(teacher);
                                        System.out.println("Hasło zostało zaktualizowane\n");

                                    } else if (choiceEdit.equals("3")) {

                                        while(true) {
                                            System.out.println("Wpisz nowe imię nauczyciela:");
                                            String newName = br.readLine();

                                            if (newName.matches("[a-zA-Z]+$")) {
                                                teacher.setName(newName);
                                                teacherDao.update(teacher);
                                                break;
                                            } else
                                                System.out.println("Imię musi się składać wyłącznie z liter\n");
                                        }

                                        System.out.println("Imię zostało zaktualizowane\n");

                                    } else if (choiceEdit.equals("4")) {

                                        while(true) {
                                            System.out.println("Wpisz nowe nazwisko nauczyciela:");
                                            String LastName = br.readLine();

                                            if (LastName.matches("[a-zA-Z]+$")) {
                                                teacher.setLastName(LastName);
                                                teacherDao.update(teacher);
                                                break;
                                            }else
                                                System.out.println("Nazwisko musi się składać wyłącznie z liter\n");
                                        }

                                        System.out.println("Nazwisko zostało zaktualizowane\n");

                                    } else if (choiceEdit.equals("5")) {
                                        while(true) {
                                            System.out.println("Wpisz nowy przedmiot nauczyciela:");
                                            String subject = br.readLine();

                                            if (subject.matches("[a-zA-Z]+$")) {
                                                teacher.setSubject(subject);
                                                teacherDao.update(teacher);
                                                break;
                                            }else
                                                System.out.println("Przedmiot musi się składać wyłącznie z liter\n");
                                        }

                                        System.out.println("Przedmiot został zaktualizowany\n");

                                    } else if (choiceEdit.equals("6")) {

                                        System.out.println("Wpisz nowy adres nauczyciela:");
                                        String address = br.readLine();
                                        teacher.setAddress(address);
                                        teacherDao.update(teacher);
                                        System.out.println("Adres został zaktualizowany\n");

                                    } else if (choiceEdit.equals("7")) {
                                        while(true) {
                                            System.out.println("Wpisz nowy numer telefonu nauczyciela:");
                                            String phoneNumber = br.readLine();

                                            if(phoneNumber.length() == 9) {

                                                if (phoneNumber.matches("[0-9]+$")) {
                                                    teacher.setPhone(phoneNumber);
                                                    teacherDao.update(teacher);
                                                    break;
                                                } else
                                                    System.out.println("Numer telefonu musi się składać wyłącznie z cyfr\n");
                                            }else
                                                System.out.println("Numer telefonu musi się składać tylko z 9 cyfr\n");
                                        }
                                        System.out.println("Numer telefonu został zaktualizowany\n");

                                    } else if (choiceEdit.equals("8")) {

                                        break;

                                    } else if (!choiceEdit.equals("1") && !(choiceEdit.equals("2")) && !(choiceEdit.equals("3")) && !(choiceEdit.equals("4")) && !(choiceEdit.equals("5")) && !(choiceEdit.equals("6")) && !(choiceEdit.equals("7")) && !(choiceEdit.equals("8"))) {
                                        System.out.println("Możesz wybrać tylko opcję od 1 do 8...");
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 6: {
                        while(true) {
                            System.out.println("CZY NA PEWNO CHCESZ SIĘ WYLOGOWAĆ? (t/n)");

                            String choiceExit = br.readLine();

                            if (choiceExit.equals("t")){
                                System.exit(0);
                            }else if(choiceExit.equals("n")){
                                break;
                            }else if(!choiceExit.equals("n") && !(choiceExit.equals("t"))){
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
