package com.myapp.dao;

import com.myapp.dto.Presence;
import com.myapp.dto.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Piotrek on 2016-06-04.
 */
public class PresenceDaoTest {

    private static final int REPEAT = 5;
    PresenceDao dao = new PresenceDao(true);
    StudentDao studentDao = new StudentDao(true);

    @Before
    public void setUp() throws Exception {

        for (int i = 1; i <= 2; i++) {
            studentDao.save(new Student("Name" + i, "LastName" + i, "City" + i, 100 + i, "12345" + i));
        }

        for (int i = 1; i <= REPEAT; i++) {
            Student student = new Student(1);
            dao.save(new Presence(student, "Subject", "2015-05-05", (2 % i == 0)));
        }
    }

    @After
    public void tearDown() throws Exception {
        dao.deleteAll();
        studentDao.deleteAll();
    }

    @Test
    public void findByStudentId() throws Exception {
        List<Presence> presences = (List<Presence>) dao.findByStudentId(1);
        Assert.assertNotNull(presences);
        Assert.assertTrue(presences.size() == 3);

        for(Presence presence : presences) {
            System.out.println(presence.toString());
        }
     }

    @Test
    public void save() throws Exception {
        Student student = new Student(1);
        Presence presence = new Presence(student, "Subject", "2015-05-01", false);
        int newIdRow = dao.save(presence);
        Assert.assertNotEquals(0, newIdRow);
    }

}