package com.example;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try {
            // Load properties file
            Properties properties = new Properties();
            InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);

            // Replace placeholders with environment variables
            String dbUrl = properties.getProperty("db.url")
                            .replace("${RDS_HOST}", System.getenv("RDS_HOST"))
                            .replace("${RDS_PORT}", System.getenv("RDS_PORT"))
                            .replace("${RDS_DB_NAME}", System.getenv("RDS_DB_NAME"));

            String dbUsername = properties.getProperty("db.username")
                             .replace("${RDS_USERNAME}", System.getenv("RDS_USERNAME"));

            String dbPassword = properties.getProperty("db.password")
                             .replace("${RDS_PASSWORD}", System.getenv("RDS_PASSWORD"));

            String dbDriver = properties.getProperty("db.driver");

            // Load the MySQL driver
            Class.forName(dbDriver);

            // Establish the connection
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}