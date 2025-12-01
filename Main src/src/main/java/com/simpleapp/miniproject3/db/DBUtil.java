package com.simpleapp.miniproject3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/miniprojectdb?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Wm3002003";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
