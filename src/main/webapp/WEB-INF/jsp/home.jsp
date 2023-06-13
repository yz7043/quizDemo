<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Home</title>
    <style>
        .center {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .category-img {
            width: 500px;
            height: 360px;
            object-fit: cover;
            border: 1px solid #ccc;
            padding: 10px;
            margin: 10px;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="center">
<c:choose>
    <c:when test="${empty isAdmin or not isAdmin}">
            <h1>Choose one category</h1>
        <div>
            <div class="container">
                <div class="row row-cols-2">
                    <c:forEach items="${allCategories}" var="categories">
                        <a href="/quiz?category_id=${categories.getCategory_id()}"
                        class="col d-flex align-items-center justify-content-center">
                            <img src="${categories.getPicture()}" alt="${categories.getName()}" class="category-img border p-3">
                            <div>${categories.getName()}</div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>

            <h1>Quiz History</h1>
        <div>
            <table class="table table-striped table-hover">
                <thead>
                    <th>Quiz Name</th>
                    <th>End Time</th>
                    <th>Score</th>
                    <th></th>
                </thead>
                <c:forEach items="${historyQuiz}" var="quiz">
                    <tr>
                        <td>${quiz.getQuiz().getName()}</td>
                        <td>${quiz.getQuiz().getTime_end()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${quiz.getScore() < 3}"><div style="color: red">Fail</div></c:when>
                                <c:otherwise><div style="color: #04AA6D">Pass</div></c:otherwise>
                            </c:choose>
                        </td>
                        <td><a href="/quizresult?quiz_id=${quiz.getQuiz().getQuiz_id()}">Result</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <div>I am Admin</div>
    </c:otherwise>
</c:choose>
</div>
<c:if test="${not empty alertMsg}">
    <script>alert('${alertMsg}');</script>
</c:if>
</body>
</html>