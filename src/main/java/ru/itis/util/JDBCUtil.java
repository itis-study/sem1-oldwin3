package ru.itis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private final static String url = "jdbc:postgresql://localhost:5432/recipe+website";
    private final static String user = "postgres";
    private final static String password = "2305";
    private static Connection connection;

    private JDBCUtil() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (JDBCUtil.class) {
                if (connection == null) {
                    initializeConnection();
                }
            }
        }
        return connection;
    }

    private static void initializeConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing database connection", e);
        }
    }

}
