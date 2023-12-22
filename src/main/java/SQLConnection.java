/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javachips.studentinformationsystem;

/**
 *
 * @author psg420
 */
import java.sql.*;

public class SQLConnection {

    private String databaseURL;
    private String username;
    private String password;

    SQLConnection(String databaseURL, String username, String password) {
        this.databaseURL = databaseURL;
        this.username = username;
        this.password = password;
    }

    public Connection getDatabaseConnection() throws SQLException{
        return DriverManager.getConnection(databaseURL, username, password);
    }

}
