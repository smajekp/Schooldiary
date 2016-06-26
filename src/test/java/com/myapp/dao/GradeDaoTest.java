package com.myapp.dao;

import com.myapp.dto.Grade;
import com.myapp.dto.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Piotrek on 2016-06-11.
 */
public class GradeDaoTest {

    private static final int REPEAT = 5;
    GradeDao gradeDao = new GradeDao(true);
    StudentDao studentDao = new StudentDao(true);

    @Before
    public void setUp() throws Exception {

        for (int i = 1; i <= 2; i++) {
            studentDao.save(new Student("Name" + i, "LastName" + i, "City" + i, 100 + i, "12345" + i));
        }

        for (int i = 1; i <= REPEAT; i++) {
            Student student = new Student(1);
            gradeDao.save(new Grade(student, "Math", "test", 4));
        }

        for (int i = 1; i <= REPEAT; i++) {
            Student student = new Student(1);
            gradeDao.save(new Grade(student, "Polish", "test", 4));
        }
    }

    @After
    public void tearDown() throws Exception {
        gradeDao.deleteAll();
        studentDao.deleteAll();
    }

    @Test
    public void save() throws Exception {
        Student student = new Student(1);
        Grade grade = new Grade(student, "Math", "test", 4);
        int newIdRow = gradeDao.save(grade);
        Assert.assertNotEquals(0, newIdRow);
    }

    @Test
    public void update() throws Exception{
       Grade grade = (Grade) gradeDao.find(5);
        Assert.assertNotNull(grade);
        grade.setGrade(2);
        gradeDao.update(grade);
        grade = (Grade) gradeDao.find(5);
        Assert.assertEquals(grade.getGrade(), new Integer(2));

    }

    @Test
    public void findBySubject() throws Exception {
        final String subject = "Math";
        List<Grade> grades = gradeDao.findBySubject(subject);
        Assert.assertNotNull(grades);
        Assert.assertTrue(grades.size() == REPEAT);
    }
}
