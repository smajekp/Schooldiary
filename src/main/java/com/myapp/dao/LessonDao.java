package com.myapp.dao;

import com.myapp.dto.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotrek on 2016-06-04.
 */
public class LessonDao extends CommonDao implements DaoInterface {

    public static final String TABLE_NAME = "lesson";

    public void deleteAll() {
        super.deleteAll(TABLE_NAME);
    }

    public LessonDao() {
    }

    public LessonDao(boolean isTest) {
        super(isTest);
    }

    public int save(Object object) {
        Lesson lesson = (Lesson) object;
        String query = "Insert Into " + TABLE_NAME + " Values (null, ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setParameters(lesson, statement);
            int newRowId = statement.executeUpdate();
            statement.close();
            return newRowId;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void setParameters(Lesson lesson, PreparedStatement statement) throws SQLException {
        statement.setString(1, lesson.getSubject());
        statement.setString(2, lesson.getTopic());
        statement.setString(3, lesson.getDate());
    }

    public void update(Object object) {
        Lesson lesson = (Lesson) object;
        String query = "Update " + TABLE_NAME + " Set subject = ?, topic = ?, date = ? " +
                "Where id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setParameters(lesson, statement);
            statement.setInt(4, lesson.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object find(Integer id) {
        String query = "Select * From " + TABLE_NAME + " Where id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result != null && result.next()) {
                Lesson lesson = new Lesson(result.getInt(1), result.getString(2), result.getString(3),
                        result.getString(4));
                statement.close();
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List find() {
        String query = "Select * From " + TABLE_NAME;
        List<Object> lessons = new ArrayList<Object>();
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Lesson lesson = new Lesson(result.getInt(1), result.getString(2), result.getString(3),
                        result.getString(4));
                lessons.add(lesson);
            }
            statement.close();
            return lessons;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer id) {
        super.delete(TABLE_NAME, id);
    }
}
