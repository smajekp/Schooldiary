package com.myapp.dto;

/**
 * Created by Piotrek on 2016-06-04.
 */
public class Presence extends CommonDto{
    Student student;
    String subject;
    String date;
    boolean absence;

    public Presence(Student student, String subject, String date, boolean absence) {
        this.student = student;
        this.subject = subject;
        this.date = date;
        this.absence = absence;
    }

    @Override
    public String toString() {
        return "Presence{" +
                "absence=" + absence +
                ", date='" + date + '\'' +
                ", subject='" + subject + '\'' +
                ", student=" + student.getId() + " " + student.getName() + " " + student.getLastName() +
                '}';
    }

    public Presence(Integer id, Student student, String subject, String date, boolean absence) {
        this(student, subject, date, absence);
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAbsence() {
        return absence;
    }

    public void setAbsence(boolean absence) {
        this.absence = absence;
    }
}
