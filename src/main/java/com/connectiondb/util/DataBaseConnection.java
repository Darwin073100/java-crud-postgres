package com.connectiondb.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataBaseConnection {
    private final static String URL = "jdbc:postgresql://localhost:5432/project";
    private final static String USER = "postgres";
    private final static String PASSWORD = "admin123";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance(){
        try {
            pool = new BasicDataSource();
            pool.setUrl(URL);
            pool.setUsername(USER);
            pool.setPassword(PASSWORD);

            pool.setInitialSize(3);
            pool.setMinIdle(2);
            pool.setMaxIdle(10);
            pool.setMaxTotal(10);
            return pool;
        } catch (Exception e) {
            System.err.println(e.getMessage() + " :: " + e.getClass().getName());
        }
        return null;
    }

    public static Connection getConnection() throws SQLException{
        return getInstance().getConnection();
    }
}
