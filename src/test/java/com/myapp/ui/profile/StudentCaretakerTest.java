package com.myapp.ui.profile;

import com.myapp.dto.Student;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Piotrek on 2016-06-26.
 */
public class StudentCaretakerTest {
    @Test
    public void addMemento() throws Exception {
        StudentCaretaker caretaker = new StudentCaretaker();

        Student student = new Student(1, "Name", "LastName", "Address", 123456789, "500131233");
        caretaker.addMemento("before", student.saveToMemento());
        student.setName("NewName");
        caretaker.addMemento("after", student.saveToMemento());
        Assert.assertEquals("Name", caretaker.getMemento("before").getName());
        Assert.assertEquals("NewName", caretaker.getMemento("after").getName());
    }

}