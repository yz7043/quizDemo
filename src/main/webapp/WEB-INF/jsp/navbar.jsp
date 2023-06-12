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
    <c:choose>
        <c:when test="${not empty isLogin and isLogin}">
            <c:choose>
                <c:when test="${not empty isAdmin and isAdmin}">
                    <a href="/adminhome">Home</a>
                    <a href="/adminUserMgmt">User Management</a>
                    <a href="/adminQuizResultMgmt">Quiz Result Management</a>
                    <a href="/adminQuestionMgmt">Quiz Management</a>
                    <a href="/adminContactManagement">Contact Management</a>
                </c:when>
                <c:otherwise>
                    <a href="/home">Home</a>
                    <a href="/contactus">Contact us</a>
                    <%--                <a href="/quiz">New Quiz</a>--%>
                </c:otherwise>
            </c:choose>
            <a href="/logout">Logout</a>--%>
        </c:when>
        <c:otherwise>
                <a href="/login">Login</a>
                <a href="/register">Register</a>
                <a href="/contactus">Contact us</a>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
