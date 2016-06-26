package com.myapp.ui.profile;

/**
 * Created by Piotrek on 2016-06-26.
 */
public class TeacherMemento {
    private String login;
    private String password;
    private String name;
    private String role;
    private String subject;
    private String lastName;
    private String address;
    private String phone;

    public TeacherMemento(String login, String password, String name, String lastName,
                          String role, String subject, String address, String phone) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.subject = subject;
        this.address = address;
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getSubject() {
        return subject;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }
}
