<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@ include file="navbar.jsp"%>
<%-- div is for grouping items --%>
<div>
    <form method="post" action="/login">
        <div>
            <label>Email</label>
            <input type="text" name="email">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <button type="submit">Submit</button>
    </form>
    <c:if test="${not empty error}">
        <small style="color: red">${error}</small>
    </c:if>
    <c:if test="${not empty alertMsg}">
        <script>alert('${alertMsg}');</script>
    </c:if>
</div>
</body>
</html>
