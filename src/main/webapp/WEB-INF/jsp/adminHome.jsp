<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>
<%@ include file="navbar.jsp"%>
<h1>Admin Page</h1>
<table>
    <tr>
        <td><button><a href="/adminUserMgmt">User Management</a></button></td>
        <td><button><a href="/adminQuizResultMgmt">Quiz Result Management</a></button></td>
        <td><button><a href="/adminQuestionMgmt">Quiz Management</a></button></td>
        <td><button><a href="/adminContactManagement">Contact Management</a></button></td>
    </tr>
</table>
</body>
</html>
