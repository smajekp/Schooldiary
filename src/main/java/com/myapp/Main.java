package com.myapp;

import com.myapp.dao.TeacherDao;
import com.myapp.dto.Teacher;
import com.myapp.ui.profile.Profile;
import com.myapp.ui.profile.ProfileFactory;
import com.myapp.ui.profile.ProfileType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Main class
 */
public class Main {


    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) throws IOException {

        while(true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\t\t\t\t----- DZIENNIK ELEKTRONICZNY -----");
            System.out.print("Podaj login: ");
            String login = br.readLine();
            System.out.print("Podaj hasło: ");
            String password = br.readLine();

            String role = null;
            String subject = null;
            TeacherDao teacherDao = new TeacherDao();
            List<Object> teachers = teacherDao.find();

                for (Object teacher : teachers) {
                    if (((Teacher) teacher).getLogin().equals(login) && ((Teacher) teacher).getPassword().equals(password)) {
                        role = ((Teacher) teacher).getRole();
                        subject = ((Teacher) teacher).getSubject();
                    }
                }

                if(role != null) {

                    Profile profile = null;

                    if (role.equals("Dyrektor")) {
                        profile = ProfileFactory.buildProfile(ProfileType.DIRECTOR);
                    } else if (role.equals("Wychowawca")) {
                        profile = ProfileFactory.buildProfile(ProfileType.EDUCATOR);
                    } else {
                        profile = ProfileFactory.buildProfile(ProfileType.TEACHER);
                    }

                    profile.printMenu(subject);
                }else{
                    System.out.println("Błędny login lub hasło, spróbuj jeszcze raz...\n");
                }
        }
    }
}
