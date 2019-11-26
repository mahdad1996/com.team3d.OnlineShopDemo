package com.team3d.DatabaseContext;

import java.sql.*;

public class DbContext {
    //database driver

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/shopdb";

    //database credentials

    static final String username = "root";
    static final String password = "";

    public static Connection getConnection(){
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, username, password);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }



}



