package com.myapp.dao;

import com.myapp.dto.Lesson;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Class to test TeacherDao
 */
public class LessonDaoTest {

    private static final int REPEAT = 5;

    private LessonDao dao = new LessonDao();

    @Before
    public void setUp() throws Exception {
        String currentDate="13/03/2016";
        for (int i = 1; i <= REPEAT; i++) {
            dao.save(new Lesson("subject" + i, "topic" + i, currentDate));
        }
    }

    @After
    public void tearDown() throws Exception {
        dao.deleteAll();
    }

    @Test
    public void save() throws Exception {
        String currentDate="13/03/2016";
        Lesson lesson = new Lesson("Subject", "topic", currentDate);
        int newRowId = dao.save(lesson);
        Assert.assertNotEquals(0, newRowId);
    }

    @Test
    public void update() throws Exception {
        final String SUBJECT = "New subject";
        final String TOPIC = "New topic";
        Lesson lesson = (Lesson) dao.find(3);
        Assert.assertNotNull(lesson);
        lesson.setSubject(SUBJECT);
        lesson.setTopic(TOPIC);
        dao.update(lesson);
        Lesson updatedLesson = (Lesson) dao.find(3);
        Assert.assertEquals(updatedLesson.getSubject(), SUBJECT);
        Assert.assertEquals(updatedLesson.getTopic(), TOPIC);
    }

    @Test
    public void find() throws Exception {
        List<Object> lessons = dao.find();
        Assert.assertNotNull(lessons);
        Assert.assertTrue(lessons.size() == REPEAT);
    }

    @Test
    public void findById() throws Exception {
        Lesson lesson = (Lesson) dao.find(4);
        Assert.assertNotNull(lesson);
    }

    @Test
    public void delete() throws Exception {
        Assert.assertNotNull(dao.find(3));
        dao.delete(3);
        Assert.assertNull(dao.find(3));
    }
}