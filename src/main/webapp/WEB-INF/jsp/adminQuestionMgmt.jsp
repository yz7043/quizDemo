<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Question Management</title>
    <style>
        table {
            border-collapse: collapse;
        }

        td, th {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div>
    <h1>Categories</h1>
    <button>Add one category</button>
    <table>
        <c:forEach items="${adminQuestionMgmtCategories}" var="category">
        <tr>
            <td>${category.getName()}</td>
        </tr>
        </c:forEach>
    </table>
<%--    <button>Add an Category</button>--%>
</div>
<div>
    <h1>Add a question</h1>
</div>
<div>
    <h1>Question banks</h1>
    <table>
        <thead>
            <th>Category</th>
            <th>Description</th>
            <th>Status</th>
            <th>Edit</th>
            <th>Toggle</th>
        </thead>
    <c:forEach items="${adminQuestionMgmtQuestions}" var="question">
        <tr>
            <td>${question.getCategory().getName()}</td>
            <td>${question.getQuestion().getDescription()}</td>
            <td>
                <c:choose>
                    <c:when test="${question.getQuestion().getIs_active()}">
                        <div style="color: green">Active</div>
                    </c:when>
                    <c:otherwise>
                        <div style="color: darkred">Suspended</div>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <button>Edit</button>
            </td>
            <td>
                <button>
                    <a href="/adminQuestionMgmtToggle?question_id=${question.getQuestion().getQuestion_id()}">Toggle</a>
                </button>
            </td>
        </tr>
    </c:forEach>
    </table>
</div>
<%@ include file="alert.jsp"%>
</body>
</html>
