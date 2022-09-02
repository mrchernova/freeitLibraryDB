package com.chernova.libraryDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    public static Connection con;
    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement postman;
    static {
        try {
            postman = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
