<%@ page import="com.andy.project1.domain.Contact" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Management</title>
    <style>
        table {
            border-collapse: collapse;
        }

        td, th {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<h1>Contact Management</h1>
<table>
<c:forEach items="${adminContactList}" var="contact">
    <thead>
    <tr>
        <th>Subject</th>
        <th>Email</th>
        <th>Time</th>
        <th>Message</th>
    </tr>
    </thead>
    <tr>
        <td>${contact.getSubject()}</td>
        <td>${contact.getEmail()}</td>
        <td>${contact.getTimeFormatString()}</td>
        <td>${contact.getMessage()}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
