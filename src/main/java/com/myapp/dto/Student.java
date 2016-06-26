package com.myapp.dto;

import com.myapp.ui.profile.StudentMemento;

/**
 * Class Student
 */
public class Student extends Person {

    /**
     * homeNumber
     */
    private Integer homeNumber;

    public Student(String name, String lastName, String address, int homeNumber, String phone) {
        super(name, lastName, address, phone);
        this.homeNumber = homeNumber;
    }

    public Student(Integer id, String name, String lastName, String address, int homeNumber, String phone) {
        this(name, lastName, address, homeNumber, phone);
        this.id = id;
    }

    public Student(Integer id) {
        super();
        this.id = id;
    }

    public Student(Integer id, String name, String lastName) {
        super(name, lastName, null, null);
        this.id = id;
    }

    public StudentMemento saveToMemento() {
        return new StudentMemento(this.name, this.lastName, this.address, this.homeNumber, this.phone);
    }

    public void restoreFromMemento(StudentMemento memento) {
        this.name = memento.getName();
        this.lastName = memento.getLastName();
        this.address = memento.getAddress();
        this.homeNumber = memento.getHomeNumber();
        this.phone = memento.getPhone();
    }

    public Integer getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(Integer homeNumber) {
        this.homeNumber = homeNumber;
    }
}
