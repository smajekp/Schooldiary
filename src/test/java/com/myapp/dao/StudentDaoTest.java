package com.myapp.dao;

import com.myapp.dto.Person;
import com.myapp.dto.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Class to test StudentDao
 */
public class StudentDaoTest {

    private static final int REPEAT = 5;

    private StudentDao dao = new StudentDao(true);

    @Before
    public void setUp() throws Exception {
        for (int i = 1; i <= REPEAT; i++) {
            dao.save(new Student("Name" + i, "LastName" + i, "City" + i, 100 + i, "12345" + i));
        }
    }

    @After
    public void tearDown() throws Exception {
        dao.deleteAll();
    }

    @Test
    public void save() throws Exception {
        Person person = new Student("Name", "LastName", "Address", 200, "12345");
        int newRowId = dao.save(person);
        Assert.assertNotEquals(0, newRowId);
    }

    @Test
    public void update() throws Exception {
        Person person = (Student) dao.find(3);
        final String NEW_NAME = "NewName";
        person.setName(NEW_NAME);
        dao.update(person);
        Assert.assertEquals(NEW_NAME, ((Student) dao.find(3)).getName());
    }

    @Test
    public void find() throws Exception {
        List<Object> students = dao.find();
        Assert.assertNotNull(students);
        Assert.assertTrue(students.size() == REPEAT);
    }

    @Test
    public void findById() throws Exception {
        Person person = (Student) dao.find(2);
        Assert.assertNotNull(person);
    }

    @Test
    public void delete() throws Exception {
        Assert.assertNotNull(dao.find(4));
        dao.delete(4);
        Assert.assertNull(dao.find(4));
    }
}