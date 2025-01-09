<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.EmployeeDAO" %>
<%@ page import="com.example.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
    <style>
        table {
            width: 60%;
            border-collapse: collapse;
            margin: 50px 0;
            font-size: 18px;
            text-align: left;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 12px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Employee Details</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Department</th>
        </tr>
        <%
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Employee> employees = employeeDAO.getEmployees();
            if (employees != null && !employees.isEmpty()) {
                for (Employee employee : employees) {
        %>
                    <tr>
                        <td><%= employee.getId() %></td>
                        <td><%= employee.getName() %></td>
                        <td><%= employee.getDepartment() %></td>
                    </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="3">No employees found</td>
            </tr>
        <%
            }
        %>
    </table>
</body>
</html>