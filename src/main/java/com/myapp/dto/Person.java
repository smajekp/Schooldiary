package com.myapp.dto;

/**
 * Class
 */
public class Person extends CommonDto {

    /**
     * name
     */
    protected String name;

    /**
     * last name
     */
    protected String lastName;

    /**
     * address
     */
    protected String address;

    /**
     * phone
     */
    protected String phone;

    public Person(String name, String lastName, String address, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
