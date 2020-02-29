package by.lugovskoi.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/hotel?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "кщще";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection newConnection() throws SQLException {

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
