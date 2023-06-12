<%@ page import="com.andy.project1.domain.Contact" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        .content-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid black;
            padding: 8px;
            height: 50px;  /* adjust as per requirement */
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
        }
        h1{
            justify-content: center;
            align-items: center;
            display: flex;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="content-container">
<h1>Contact Management</h1>
    <div class="col-md-12">
    <table class="table able table-striped table-hover">
    <c:forEach items="${adminContactList}" var="contact">
        <thead>
        <tr>
            <th class="col-3">Subject</th>
            <th class="col-3">Email</th>
            <th class="col-2">Time</th>
            <th class="col-4">Message</th>
        </tr>
        </thead>
        <tr>
            <td class="col-3">${contact.getSubject()}</td>
            <td class="col-3">${contact.getEmail()}</td>
            <td class="col-2">${contact.getTimeFormatString()}</td>
            <td class="col-4">
                <c:choose>
                    <c:when test="${contact.getMessage().length() <= 50}">
                        ${contact.getMessage()}
                    </c:when>
                    <c:otherwise>
                        ${contact.getMessage().substring(0, 44)}<a href="/contactDetails?contact_id=${contact.getContact_id()}">...Show</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </table>
    </div>
</div>
</body>
</html>
