package com.myapp.dao;

import com.myapp.dto.Grade;
import com.myapp.dto.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GradeDao extends CommonDao implements DaoInterface {

    public static final String TABLE_NAME = "grades";

    public GradeDao() {}

    public GradeDao(boolean isTest) {
        super(isTest);
    }

    public void deleteAll() {
        super.deleteAll("grades");
    }

    public int save(Object object) {
        Grade grade = (Grade) object;
        String query = "Insert Into " + TABLE_NAME + " Values (null, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setParameters(grade, statement);
            int newRowId = statement.executeUpdate();
            statement.close();
            return newRowId;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void setParameters(Grade grade, PreparedStatement statement) throws SQLException {
        statement.setInt(1, grade.getStudent().getId());
        statement.setString(2, grade.getSubject());
        statement.setString(3, grade.getGradeType());
        statement.setInt(4, grade.getGrade());
    }

    public void update(Object object) {
        Grade grade = (Grade) object;
        String query = "Update " + TABLE_NAME + " Set id_student = ?, subject = ?, grade_type = ?, grade = ?" +
                "Where id = ?";
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(query);
            setParameters(grade, statement);
            statement.setInt(5, grade.getId());
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
                Student student = new Student(result.getInt(1));
                Grade grade = new Grade(result.getInt(1), student, result.getString(3), result.getString(4),
                        result.getInt(5));
                statement.close();
                return grade;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public List find() {

        return null;
    }

    public void delete(Integer id) {
        super.delete("grades", id);
    }

    public List<Grade> findBySubject(String subject) {
        String query = "Select s.id, s.name, s.last_name, g.id, g.subject, g.grade_type, g.grade from grades g " +
                "Inner Join student s on " +
                "s.id = g.id_student where g.subject = ?";
        try {
            List<Grade> grades = new ArrayList<Grade>();
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, subject);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Student student = new Student(result.getInt(1), result.getString(2), result.getString(3));
                Grade grade = new Grade(result.getInt(4), student, result.getString(5),
                        result.getString(6), result.getInt(7));
                grades.add(grade);
            }
            statement.close();
            return grades;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
