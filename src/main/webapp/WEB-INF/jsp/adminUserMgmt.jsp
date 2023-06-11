<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin User Management</title>
    <style>
        table {
            border-collapse: collapse;
        }

        td {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<h1>User table</h1>
<table>
    <thead>
        <th>Full Name</th>
        <th>Email</th>
        <th>Status</th>
        <th>Toggle Status</th>
    </thead>
    <c:forEach items="${adminUserList}" var="user">
        <tr>
            <td>${user.getFirstname()}&nbsp;${user.getLastname()}</td>
            <td>${user.getEmail()}</td>
            <td>
                <c:choose>
                    <c:when test="${user.getIs_active()}">
                        <div style="color: green">Active</div>
                    </c:when>
                    <c:otherwise>
                        <div style="color: darkred">Suspended</div>
                    </c:otherwise>
                </c:choose>
            </td>
            <td><c:choose>
                <c:when test="${user.getIs_admin()}">Admin</c:when>
                <c:otherwise>
                    <button><a href="/adminUserToggleStatus?user_id=${user.getUser_id()}">Toggle</a></button>
                </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty alertMsg}">
    <script>alert('${alertMsg}');</script>
</c:if>
</body>
</html>
