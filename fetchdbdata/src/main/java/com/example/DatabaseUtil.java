package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
	
	private static String getEnvironmentVariableOrDefault(String envVar, String defaultValue) {
        String value = System.getenv(envVar);
        return value != null && !value.isEmpty() ? value : defaultValue;
    }
	
	private static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IOException("Unable to find db.properties file in the classpath.");
            }
            props.load(input);
        }
        return props;
    }

    public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        Properties props = loadProperties();
        
     // Retrieve database configuration from environment variables
        String url = getEnvironmentVariableOrDefault("DB_URL", props.getProperty("db.url"));
        String username = getEnvironmentVariableOrDefault("DB_USERNAME", props.getProperty("db.username"));
        String password = getEnvironmentVariableOrDefault("DB_PASSWORD", props.getProperty("db.password"));
        String driver = getEnvironmentVariableOrDefault("DB_DRIVER", props.getProperty("db.driver"));

        Class.forName(driver); // Load the MySQL driver
        return DriverManager.getConnection(url, username, password);
    }

    public static ResultSet getEmployees() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM employees";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
}
