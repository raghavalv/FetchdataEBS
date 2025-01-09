<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Environment Variables</title>
</head>
<body>
    <h1>Environment Variables</h1>
    <p>MY_VARIABLE: <%= System.getenv("MY_VARIABLE") %></p>
</body>
</html>
