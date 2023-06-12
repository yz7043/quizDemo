<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Home</title>
    <style>
        table {
            border-collapse: collapse;
        }

        td {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<c:choose>
    <c:when test="${empty isAdmin or not isAdmin}">
        <div>
            <h1>Choose one category</h1>
            <ul>
            <c:forEach items="${allCategories}" var="categories">
                <li><a href="/quiz?category_id=${categories.getCategory_id()}">${categories.getName()}</a></li>
            </c:forEach>
            </ul>
        </div>
        <div>
            <h1>Here is your ongoing Quiz</h1>
            <c:choose>
                <c:when test="${not empty ongoingQuiz}">

                    <a>${ongoingQuiz.getName()}</a>
                </c:when>
                <c:otherwise>
                    No ongoing quiz!
                </c:otherwise>
            </c:choose>

        </div>

        <div>
            <h1>Quiz History</h1>
            <table>
                <c:forEach items="${historyQuiz}" var="quiz">
                    <tr>
                        <td>${quiz.getQuiz().getName()}</td>
                        <td>${quiz.getQuiz().getTime_end()}</td>
                        <td>Score:
                            <c:choose>
                                <c:when test="${quiz.getScore() < 3}">Fail</c:when>
                                <c:otherwise>Pass</c:otherwise>
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
<c:if test="${not empty alertMsg}">
    <script>alert('${alertMsg}');</script>
</c:if>
</body>
</html>