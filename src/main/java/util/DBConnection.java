package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sunrise_dental";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    // Single shared database connection
    private static Connection connection;

    // Private constructor prevents creation of DBConnection objects
    private DBConnection() {
    }

    // Provides access to the single shared connection
    public static Connection getConnection() {

        try {

            // Create connection only if one does not exist or was closed
            if (connection == null || connection.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(
                        URL,
                        USER,
                        PASSWORD
                );

                System.out.println("Database Connected Successfully");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}