package com.mycompany.bustrackingsystem.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/bustrackingsystem";
    private static final String USER = "root";
    private static final String PASSWORD = "021019Ss@";

    private static Connection connection = null;

    // Get database connection
    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connected successfully.");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("❌ MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            throw new SQLException("❌ Failed to connect to database!", e);
        }
        return connection;
    }
}
