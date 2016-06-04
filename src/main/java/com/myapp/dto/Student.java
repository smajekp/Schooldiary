package com.myapp.dto;

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

    public Integer getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(Integer homeNumber) {
        this.homeNumber = homeNumber;
    }
}
