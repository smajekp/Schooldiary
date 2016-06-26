package com.myapp.dao;

import com.myapp.dto.Presence;
import com.myapp.dto.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotrek on 2016-06-04.
 */
public class PresenceDao extends CommonDao implements DaoInterface {

    public static final String TABLE_NAME = "presence";

    public PresenceDao() {
    }

    public PresenceDao(boolean isTest) {
        super(isTest);
    }

    public void deleteAll() {
        super.deleteAll("presence");
    }

    public int save(Object object) {
        Presence presence = (Presence) object;
        String query = "Insert Into " + TABLE_NAME + " Values (null, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setParameters(presence, statement);
            int newRowId = statement.executeUpdate();
            statement.close();
            return newRowId;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void setParameters(Presence presence, PreparedStatement statement) throws SQLException {
        statement.setInt(1, presence.getStudent().getId());
        statement.setString(2, presence.getSubject());
        statement.setString(3, presence.getDate());
        statement.setBoolean(4, presence.isAbsence());
    }

    public void update(Object object) {
        Presence presence = (Presence) object;
        String query = "Update " + TABLE_NAME + " Set student_id = ?, subject = ?, date = ?, absence = ?" +
                "Where id = ?";
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(query);
            setParameters(presence, statement);
            statement.setInt(6, presence.getId());
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
                Student student = new Student(result.getInt(2));
                Presence presence = new Presence(result.getInt(1), student, result.getString(3),
                        result.getString(4), result.getBoolean(5));
                statement.close();
                return presence;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List find() {

        String query = "Select * From " + TABLE_NAME;
        List<Object> presences = new ArrayList<Object>();
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Student student = new Student(result.getInt(2));
                Presence presence = new Presence(result.getInt(1), student, result.getString(3), result.getString(4),
                        result.getBoolean(5));
                presences.add(presence);
            }
            statement.close();
            return presences;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findByStudentId(Integer id) {
        String query = "Select s.id, s.name, s.last_name, p.subject, p.date, p.absence from presence p Inner Join student s on " +
                "s.id = p.student_id where s.id = ? and p.absence = 0";
        try {
            List<Object> presences = new ArrayList<Object>();
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Student student = new Student(result.getInt(1), result.getString(2), result.getString(3));
                Presence presence = new Presence(student, result.getString(4), result.getString(5), result.getBoolean(6));
                presences.add(presence);
            }
            statement.close();
            return presences;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer id) {
            super.delete("presence", id);
    }
}
