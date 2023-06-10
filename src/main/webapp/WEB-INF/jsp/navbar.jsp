<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>

<div class="topnav">
    <c:if test="${empty isLogin or not isLogin}">
        <a href="/login">Login</a>
        <a href="/register">Register</a>
        <a href="/contact">Contact us</a>
    </c:if>
    <c:if test="${not empty isLogin and isLogin}">
        <a href="/home">Home</a>
        <a href="/contact">Contact us</a>
        <a href="/quiz">New Quiz</a>
        <a href="/quizresult">Quiz Result</a>
        <a href="/admin">Admin</a>
        <a href="/logout">Logout</a>
    </c:if>
</div>

</body>
</html>