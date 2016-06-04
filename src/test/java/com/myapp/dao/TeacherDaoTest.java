package com.myapp.dao;

import com.myapp.dto.Person;
import com.myapp.dto.Teacher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Class to test TeacherDao
 */
public class TeacherDaoTest {

    private static final int REPEAT = 5;

    private TeacherDao dao = new TeacherDao();

    @Before
    public void setUp() throws Exception {
        for (int i = 1; i <= REPEAT; i++) {
            dao.save(new Teacher("Login" + i, "password" + i, "Name" + i, "Last" + i,
                    "Admin", "Math" + i, "City" + i, "12345" + i));
        }
    }

    @After
    public void tearDown() throws Exception {
        dao.deleteAll();
    }

    @Test
    public void save() throws Exception {
        Person person = new Teacher("Login", "password", "Name", "Last", "Admin", "Math", "City", "12345");
        int newRowId = dao.save(person);
        Assert.assertNotEquals(0, newRowId);
    }

    @Test
    public void update() throws Exception {
        Person person = (Teacher) dao.find(1);
        final String NEW_NAME = "NewName";
        person.setName(NEW_NAME);
        dao.update(person);
        Assert.assertEquals(NEW_NAME, ((Teacher) dao.find(1)).getName());
    }

    @Test
    public void find() throws Exception {
        List<Object> teachers = dao.find();
        Assert.assertNotNull(teachers);
        Assert.assertTrue(teachers.size() == REPEAT);
    }

    @Test
    public void findById() throws Exception {
        Person person = (Teacher) dao.find(2);
        Assert.assertNotNull(person);
    }

    @Test
    public void delete() throws Exception {
        Assert.assertNotNull(dao.find(3));
        dao.delete(3);
        Assert.assertNull(dao.find(3));
    }
}