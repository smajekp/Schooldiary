package com.myapp.dto;

import com.myapp.ui.profile.TeacherMemento;

/**
 * Class Teacher
 */
public class Teacher extends Person {

    /**
     * login
     */
    private String login;

    /**
     * password
     */
    private String password;

    /**
     * role
     */
    private String role;

    /**
     * subject
     */
    private String subject;

    public Teacher(String login, String password, String name, String lastName,
                   String role, String subject, String address, String phone) {
        super(name, lastName, address, phone);
        this.login = login;
        this.password = password;
        this.role = role;
        this.subject = subject;
    }

    public Teacher(Integer id, String login, String password, String name, String lastName,
                   String role, String subject, String address, String phone) {
        this(login, password, name, lastName, role, subject, address, phone);
        this.id = id;
    }

    public TeacherMemento saveToMemento() {
        return new TeacherMemento(this.login, this.password, this.name, this.lastName, this.role, this.subject, this.address, this.phone);
    }

    public void restoreFromMemento(TeacherMemento memento) {
        this.login = memento.getLogin();
        this.password = memento.getPassword();
        this.name = memento.getName();
        this.lastName = memento.getLastName();
        this.role = memento.getRole();
        this.subject = memento.getSubject();
        this.address = memento.getAddress();
        this.phone = memento.getPhone();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
