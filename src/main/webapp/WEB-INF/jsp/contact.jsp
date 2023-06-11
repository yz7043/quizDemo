<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact us</title>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div>
    <form method="post" action="/contactus">
        <h2>Your Email</h2>
        <c:choose>
            <c:when test="${not empty loggedUser}">
                <input type="text" name="email" value="${loggedUser.getEmail()}" readonly>
            </c:when>
            <c:otherwise>
                <input type="text" name="email" maxlength="50">
            </c:otherwise>
        </c:choose>
        <h2>Please enter a subject (no more than 200 characters)</h2>
        <input type="text" name="subject" maxlength="200">
        <h2>Please leave your message (no more than 2000 characters)</h2>
        <textarea name="message" rows="4" cols="200" style="overflow-y: scroll;" maxlength="2000"></textarea>
        <br/>
        <button type="submit">Submit</button>
        <c:if test="${not empty alertMsg}">
            <script>alert('${alertMsg}');</script>
        </c:if>
    </form>
</div>
</body>
</html>
