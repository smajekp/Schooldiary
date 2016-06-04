package com.myapp.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class to represent common data access object
 */
public abstract class CommonDao {

    /**
     * Connection
     */
    protected Connection connection;

    /**
     * Default constructor
     */
    public CommonDao() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(getDatabaseUrl());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get database url from property file
     * @return
     */
    private String getDatabaseUrl() {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src/main/resources/config.properties");
            properties.load(input);
            return properties.getProperty("database.url");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Abstract method to delete all rows
     */
    public abstract void deleteAll();

    /**
     * Delete all rows from table
     * @param table
     */
    protected void deleteAll(String table) {
        String query = "Delete From " + table;
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to delete row from table
     * @param table
     * @param id
     */
    protected void delete(String table, Integer id) {
        String query = "Delete From " + table + " Where id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
