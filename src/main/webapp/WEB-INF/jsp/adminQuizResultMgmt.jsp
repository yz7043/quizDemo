<%--<%@ page import="com.andy.project1.domain.admin.AdminQuizResult" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Result Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        table {
            border-collapse: collapse;
        }

        td, th {
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
            <h1 style="justify-content: center; align-items: center; display: flex">Quiz result management</h1>
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
            <c:if test="${not empty adminQuizResultSortCategory}">
                <div>
                    Sorted by category
                    <button><a href="/adminQuizResultMgmt">Clear Filter</a></button>
                </div>
            </c:if>
            <c:if test="${not empty adminQuizResultSortFullname}">
                <div>
                    Sorted by full name
                    <button><a href="/adminQuizResultMgmt">Clear Filter</a></button>
                </div>
            </c:if>
            <div>

        <table class="able table-striped table-hover">
            <thead>
                <th>Taken time</th>
                <th><a href="/adminQuizResultMgmt?category=1">Category</a></th>
                <th><a href="/adminQuizResultMgmt?fullname=1">User Full Name</a></th>
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
                    <td><button class="btn-outline-secondary"><a href="/adminQuizResultDetail?quizId=${adminQuiz.getQuiz().getQuiz_id()}">Details</a></button></td>
                </tr>
            </c:forEach>
        </table>
        </div>
        </div>
    </div>
</div>
</body>
</html>
