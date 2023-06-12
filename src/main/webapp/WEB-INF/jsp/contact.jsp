<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Contact us</title>
    <style>
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 50px;
        }
        .card {
            width: 1000px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group > h2 {
            margin-top: 20px;
            margin-bottom: 10px;
        }
        textarea {
            resize: vertical;
        }
        button[type="submit"] {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="container">
    <div class="card">
        <form method="post" action="/contactus">
            <h5>Your Email</h5>
            <c:choose>
                <c:when test="${not empty loggedUser}">
                    <input type="text" name="email" value="${loggedUser.getEmail()}" readonly class="form-control">
                </c:when>
                <c:otherwise>
                    <input type="text" name="email" maxlength="50" class="form-control">
                </c:otherwise>
            </c:choose>
            <h5>Please enter a subject (no more than 200 characters)</h5>
            <input type="text" name="subject" maxlength="200" class="form-control">
            <h5>Please leave your message (no more than 2000 characters)</h5>
            <textarea name="message" rows="4" cols="200" style="overflow-y: scroll;" maxlength="2000" class="form-control"></textarea>
            <button type="submit" class="btn btn-primary">Submit</button>
            <c:if test="${not empty alertMsg}">
                <script>alert('${alertMsg}');</script>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>
