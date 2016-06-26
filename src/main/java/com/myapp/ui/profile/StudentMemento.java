package com.myapp.ui.profile;

/**
 * Created by Piotrek on 2016-06-26.
 */
public class StudentMemento {
    private String name;
    private String lastName;
    private String address;
    private int homeNumber;
    private String phone;

    public StudentMemento(String name, String lastName, String address, int homeNumber, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.homeNumber = homeNumber;
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getHomeNumber() {
        return homeNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }
}
