package com.bankingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/BankingSystem";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Sanjukta98#";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
