package com.myapp.dao;

import com.myapp.dto.Person;
import com.myapp.dto.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Teacher data access object
 */
public class TeacherDao extends CommonDao implements DaoInterface {

    public TeacherDao() {
        super();
    }

    public TeacherDao(boolean isTest) {
        super(isTest);
    }

    public int save(Object object) {
        Teacher teacher = (Teacher) object;
        String query = "Insert Into teacher Values (null, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, teacher.getLogin());
            statement.setString(2, teacher.getPassword());
            statement.setString(3, teacher.getName());
            statement.setString(4, teacher.getLastName());
            statement.setString(5, teacher.getRole());
            statement.setString(6, teacher.getSubject());
            statement.setString(7, teacher.getAddress());
            statement.setString(8, teacher.getPhone());
            int newRowId = statement.executeUpdate();
            statement.close();
            return newRowId;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void update(Object object) {
        Teacher teacher = (Teacher) object;
        String query = "Update teacher Set login = ?, password = ?, name = ?, last_name = ?, role = ?, subject = ?, address = ?, phone = ?" +
                "Where id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, teacher.getLogin());
            statement.setString(2, teacher.getPassword());
            statement.setString(3, teacher.getName());
            statement.setString(4, teacher.getLastName());
            statement.setString(5, teacher.getRole());
            statement.setString(6, teacher.getSubject());
            statement.setString(7, teacher.getAddress());
            statement.setString(8, teacher.getPhone());
            statement.setInt(9, teacher.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object find(Integer id) {
        String query = "Select * From teacher Where id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Person teacher = new Teacher(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getString(9));
                statement.close();
                return teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object> find() {
        String query = "Select * From teacher";
        List<Object> teachers = new ArrayList<Object>();
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Person teacher = new Teacher(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getString(9));
                teachers.add(teacher);
            }
            statement.close();
            return teachers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer id) {
        super.delete("teacher", id);
    }

    public void deleteAll() {
        super.deleteAll("teacher");
    }
}
