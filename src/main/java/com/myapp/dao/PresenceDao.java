package com.myapp.dao;

import com.myapp.dto.Presence;
import com.myapp.dto.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotrek on 2016-06-04.
 */
public class PresenceDao extends CommonDao implements DaoInterface {


    public void deleteAll() {
        super.deleteAll("presence");
    }

    public int save(Object object) {
        Presence presence = (Presence) object;
        String query = "Insert Into presence Values (null, ?, ?, ?, ?)";
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

    }

    public Object find(Integer id) {
        return null;
    }

    public List find() {
        return null;
    }

    public Object findByStudentId(Integer id) {
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

    }
}
