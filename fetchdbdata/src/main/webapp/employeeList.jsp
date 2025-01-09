<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.example.DatabaseUtil" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h1>Employee List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Department</th>
        </tr>
        <%
            ResultSet rs = null;
            try {
                rs = DatabaseUtil.getEmployees();
                while (rs.next()) {
        %>
                    <tr>
                        <td><%= rs.getInt("id") %></td>
                        <td><%= rs.getString("name") %></td>
                        <td><%= rs.getString("department") %></td>
                    </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) rs.close();
            }
        %>
    </table>
</body>
</html>
