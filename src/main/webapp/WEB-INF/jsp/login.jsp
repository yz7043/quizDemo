<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        .container{
            margin-top: 10vh;
        }
        .center {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
    </style>
    <title>Login</title>
</head>
<body>
<%@ include file="navbar.jsp"%>
<%-- div is for grouping items --%>
<div class="container">
<div class="card">
    <div class="card-body">
        <div class="center">
            <form method="post" action="/login">
                <h2 class="card-title center">Login</h2>
                <div class="form-group">
                    <label class="text-left">Email</label>
                    <input type="text" name="email" class="form-control">
                </div>
                <div class="form-group">
                    <label class="text-left">Password</label>
                    <input type="password" name="password" class="form-control">
                </div>
                <div class="center">
                    <button type="submit" class="btn btn-outline-primary">Submit</button>
                </div>
            </form>
        </div>
    <c:if test="${not empty error}">
        <small style="color: red; margin-top: 10px;" class="center">${error}</small>
    </c:if>
    <c:if test="${not empty alertMsg}">
        <script>alert('${alertMsg}');</script>
    </c:if>
    </div>
</div>
</div>
</body>
</html>
