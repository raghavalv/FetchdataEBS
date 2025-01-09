package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT id, name, department FROM employees";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                employees.add(new Employee(id, name, department));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}