<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div>
    <form method="post" action="/register">
        <div>
            <label>Email</label>
            <input type="text" name="email">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <div>
            <label>Password Confirm</label>
            <input type="password" name="passwordConfirm">
        </div>
        <div>
            <label>Firstname</label>
            <input type="text" name="firstname">
        </div>
        <div>
            <label>Lastname</label>
            <input type="text" name="lastname">
        </div>
        <button type="submit">Register</button>
    </form>
    <c:if test="${not empty error}">
        <small style="color: red">${error}</small>
    </c:if>
</div>
</body>
</html>
