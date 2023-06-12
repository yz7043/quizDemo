<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin User Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>

        td {
            border: 1px solid black;
            padding: 8px;
        }
        .main-content {
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="container main-content">
    <div class="row justify-content-center">
        <div class="col-xl-12">
        <h1 style="justify-content: center; align-items: center; display: flex">User table</h1>
        <table class="able table-striped table-hover">
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
                            <button class="btn-outline-secondary"><a href="/adminUserToggleStatus?user_id=${user.getUser_id()}">Toggle</a></button>
                        </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </div>
</div>
<c:if test="${not empty alertMsg}">
    <script>alert('${alertMsg}');</script>
</c:if>
</body>
</html>
