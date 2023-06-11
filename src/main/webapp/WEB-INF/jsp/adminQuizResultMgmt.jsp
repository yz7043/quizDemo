<%--<%@ page import="com.andy.project1.domain.admin.AdminQuizResult" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Result Management</title>
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
<h1>Quiz result management</h1>
<c:if test="${not empty adminQuizResultByCategory}">
<div>
    Filtered by Category: ${adminQuizResultByCategory.getName()}
    <button><a href="/adminQuizResultMgmt">Clear Filter</a></button>
</div>
</c:if>
<c:if test="${not empty adminQuizResultByUser}">
<div>
    Filtered by User: ${adminQuizResultByUser.getFirstname()}&nbsp;${adminQuizResultByUser.getLastname()}
    <button><a href="/adminQuizResultMgmt">Clear Filter</a></button>
</div>
</c:if>
<div>
<%--    <%List<AdminQuizResult> obj = (List<AdminQuizResult>) request.getAttribute("adminQuizList");--%>
<%--        System.out.println("=================");--%>
<%--    System.out.println(obj);--%>
<%--        System.out.println("=================");--%>
<%--    %>--%>
<table>
    <thead>
        <th>Taken time</th>
        <th>Category</th>
        <th>User Full Name</th>
        <th>Number of Questions</th>
        <th>Score</th>
        <th>Details</th>
    </thead>
    <c:forEach items="${adminQuizList}" var="adminQuiz">
        <tr>
            <td>${adminQuiz.getQuiz().getTimeStartFormatString()}</td>
            <td><a href="/adminQuizResultMgmt?category_id=${adminQuiz.getCategory().getCategory_id()}">
                    ${adminQuiz.getCategory().getName()}</a>
            </td>
            <td>
                <a href="/adminQuizResultMgmt?user_id=${adminQuiz.getUser().getUser_id()}">
                    ${adminQuiz.getUser().getFirstname()}&nbsp;${adminQuiz.getUser().getLastname()}
                </a>
            </td>
            <td>${adminQuiz.getQuizQuestions().size()}</td>
            <td>${adminQuiz.getScore()}</td>
            <td><button><a>Details</a></button></td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
