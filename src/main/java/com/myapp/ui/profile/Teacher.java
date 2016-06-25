package com.myapp.ui.profile;

import com.myapp.dao.*;
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
public class Teacher extends Profile {

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

            LessonDao lessonDao = new LessonDao();
            List<Object> lessons = lessonDao.find();

            String role = null;
            String name = null;
            String lastName = null;
            String subjectTeacher = null;

            for (Object teacher : teachers) {
                if (((com.myapp.dto.Teacher) teacher).getSubject().equals(profileName)) {
                    name = ((com.myapp.dto.Teacher) teacher).getName();
                    lastName = ((com.myapp.dto.Teacher) teacher).getLastName();
                    role = ((com.myapp.dto.Teacher) teacher).getRole();
                    subjectTeacher = ((com.myapp.dto.Teacher) teacher).getSubject();
                }
            }

            System.out.println("Zalogowany jako: " + " " + name + " " + lastName + " - " + role + " - " + subjectTeacher);

            System.out.println("Podaj opcję, którą chcesz wybrać.");

            System.out.println("1. WPISZ TEMAT LEKCJI");
            System.out.println("2. SPRAWDŹ OBECNOŚĆ");
            System.out.println("3. SPRAWDŹ OCENY");
            System.out.println("4. WPISZ OCENĘ");
            System.out.println("5. POKAŻ WSZYSTKIE TEMATY LEKCJI");
            System.out.println("6. DANE UCZNIÓW");
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

                    case 1: {
                        System.out.println("\t\t\t\t----- DODAJ TEMAT LEKCJI -----\n");

                        System.out.println("Wpisz temat lekcji");
                        String topic = br.readLine();

                        String date = null;

                        while(true) {
                            System.out.println("Podaj datę lekcji w formacie (dd/mm/rrrr)");
                            date = br.readLine();

                            if (date.matches("[0-9]{2}[\\/][0-9]{2}[\\/][0-9]{4}")) {
                                break;
                            } else
                                System.out.println("Data musi być w formacie (dd/mm/rrrr)\n");
                        }

                        lessonDao.save(new com.myapp.dto.Lesson(profileName, topic, date));

                        System.out.println("Temat dodany");

                        break;
                    }

                    case 2: {
                        System.out.println("\t\t\t\t----- SPRAWDŹ OBECNOŚĆ -----\n");

                        String date = null;

                        while(true) {
                            System.out.println("Podaj datę lekcji w formacie (dd/mm/rrrr)");
                            date = br.readLine();

                            if (date.matches("[0-9]{2}[\\/][0-9]{2}[\\/][0-9]{4}")) {
                                break;
                            } else
                                System.out.println("Data musi być w formacie (dd/mm/rrrr)\n");
                        }

                        for (Object student : students) {
                            System.out.println("Czy " + ((Student) student).getName() + " " + ((Student) student).getLastName() + " jest obecny/obecna? (t/n)" );
                            Student studentId = new Student(((Student) student).getId());
                            boolean absence = false;

                            while(true) {
                                String absenceStr = br.readLine();
                                if (absenceStr.equals("t")) {
                                    absence = true;
                                    break;
                                }
                                else if(absenceStr.equals("n")) {
                                    absence = false;
                                    break;
                                }
                                else{
                                    System.out.println("Możesz wpisywać tylko t lub n...");
                                }

                            }
                            presenceDao.save(new com.myapp.dto.Presence(studentId,profileName, date, absence));
                        }

                        System.out.println("Obecność sprawdzona");

                        break;
                    }


                    //sprawdz oceny
                    case 3: {
                        while(true) {
                            System.out.println("\t\t\t\t----- SPRAWDŹ OCENY -----\n");

                            String subject = profileName;
                            List<Grade> grades = gradeDao.findBySubject(subject);

                            if (grades != null) {
                                for (Object grade : grades) {
                                    System.out.println(((com.myapp.dto.Grade) grade).getStudent().getName() + " " + ((com.myapp.dto.Grade) grade).getStudent().getLastName() + " " + ((com.myapp.dto.Grade) grade).getGradeType() + " " + ((com.myapp.dto.Grade) grade).getGrade());
                                }
                            }
                            System.out.println("\nAby powrócić do menu naciśnij enter...");
                            Scanner scanner = new Scanner(System.in);
                            scanner.nextLine();

                            break;
                        }
                        break;
                    }

                    //dodaj ocene
                    case 4: {
                        System.out.println("\t\t\t\t----- DODAWANIE OCENY -----\n");

                        for (Object student : students) {
                            System.out.println(((Student) student).getId() + ". " + ((Student) student).getName() + " " + ((Student) student).getLastName() + " " + ((Student) student).getAddress() + " " + ((Student) student).getHomeNumber() + " " + ((Student) student).getPhone());
                        }

                        System.out.println("Wpisz id ucznia:");
                        int studentId = in.nextInt();
                        Student student = new Student(studentId);
                        System.out.println("Wpisz typ oceny (np. odpowiedz, kartkowka, sprawdzian, aktywnosc)");
                        String gradeType = br.readLine();
                        System.out.println("Wpisz ocenę:");;
                        int grade = in.nextInt();

                        gradeDao.save(new com.myapp.dto.Grade(student,profileName, gradeType, grade));

                        System.out.println("Ocena dodana");

                        break;
                    }

                    case 5: {
                        System.out.println("\t\t\t\t----- TEMATY LEKCJI -----\n");

                        for (Object lesson : lessons) {
                            if(((com.myapp.dto.Lesson) lesson).getSubject().equals(profileName)) {
                                System.out.println(((com.myapp.dto.Lesson) lesson).getDate() + " - " + ((com.myapp.dto.Lesson) lesson).getTopic());
                            }
                        }
                        System.out.println("\nAby powrócić do menu naciśnij enter...");
                        Scanner scanner = new Scanner(System.in);
                        scanner.nextLine();

                        break;
                    }
                    //dane uczniow
                    case 6: {
                        System.out.println("\t\t\t\t----- DANE UCZNIÓW -----\n");

                        for (Object student : students) {
                            System.out.println(((Student) student).getId() + ". " + ((Student) student).getName() + " " + ((Student) student).getLastName() + " " + ((Student) student).getAddress() + " " + ((Student) student).getHomeNumber() + " " + ((Student) student).getPhone());
                        }
                        System.out.println("\nAby powrócić do menu naciśnij enter...");
                        Scanner scanner = new Scanner(System.in);
                        scanner.nextLine();

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
