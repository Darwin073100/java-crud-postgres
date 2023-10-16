package com.connectiondb.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    private final static String URL = "jdbc:postgresql://localhost:5432/project";
    private final static String USER = "postgres";
    private final static String PASSWORD = "admin123";
    private static Connection conn;

    public static Connection getConnection(){
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection...");
            return conn;
        } catch (Exception e) {
            System.err.println(e.getMessage() + " :: " + e.getClass().getName());
        }
        return null;
    }
}
