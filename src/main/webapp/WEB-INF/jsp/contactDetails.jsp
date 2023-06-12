<%@ page import="com.andy.project1.domain.Contact" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        .content-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .card {
            width: 100%;
            max-width: 600px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="content-container">
    <h1>Contact Details</h1>
    <div class="card">
        <h5 class="card-header"><b>Subject: </b>${adminContactDetail.getSubject()}</h5>
        <div class="card-body">
            <h5 class="card-title"><b>Email</b></h5>
            <p class="card-text">${adminContactDetail.getEmail()}</p>
            <h5 class="card-title"><b>Post Time</b></h5>
            <p class="card-text">
                <c:choose>
                    <c:when test="${not empty adminContactDetail.getTimeFormatString()}">
                        ${adminContactDetail.getTimeFormatString()}
                    </c:when>
                    <c:otherwise>N/A</c:otherwise>
                </c:choose>
            </p>
            <h5 class="card-title"><b>Details</b></h5>
            <p class="card-text">${adminContactDetail.getMessage()}</p>
            <a href="/adminContactManagement" class="btn btn-outline-primary">Back</a>
        </div>
    </div>
</div>
</body>
</html>
