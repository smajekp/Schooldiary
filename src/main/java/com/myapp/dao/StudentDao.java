package com.myapp.dao;

import com.myapp.dto.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Student data access object
 */
public class StudentDao extends CommonDao implements DaoInterface {

    public int save(Object object) {
        Student student = (Student) object;
        String query = "Insert Into student Values (null, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getAddress());
            statement.setInt(4, student.getHomeNumber());
            statement.setString(5, student.getPhone());
            int newRowId = statement.executeUpdate();
            statement.close();
            return newRowId;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void update(Object object) {
        Student student = (Student) object;
        String query = "Update student Set name = ?, last_name = ?, address = ?, home_number = ?, phone_number = ?" +
                "Where id = ?";
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getAddress());
            statement.setInt(4, student.getHomeNumber());
            statement.setString(5, student.getPhone());
            statement.setInt(6, student.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Object find(Integer id) {
        String query = "Select * From student Where id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Student student = new Student(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getInt(5), result.getString(6));
                statement.close();
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List find() {
        String query = "Select * From student";
        List<Object> students = new ArrayList<Object>();
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Student student = new Student(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getInt(5), result.getString(6));
                students.add(student);
            }
            statement.close();
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer id) {
        super.delete("student", id);
    }

    public void deleteAll() {
        super.deleteAll("student");
    }
}
