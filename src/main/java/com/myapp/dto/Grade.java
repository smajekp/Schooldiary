package com.myapp.dto;


public class Grade extends CommonDto {
    Student student;
    String subject;
    String gradeType;
    Integer grade;

    public Grade(Student student, String subject, String gradeType, Integer grade) {
        this.student = student;
        this.subject = subject;
        this.gradeType = gradeType;
        this.grade = grade;
    }

    public Grade(Integer id, Student student, String subject, String gradesType, Integer grade) {
        this(student, subject, gradesType, grade);
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

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
