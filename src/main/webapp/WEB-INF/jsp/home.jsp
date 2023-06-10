<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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
                <li><a>${categories.getName()}</a></li>
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
                        <td>${quiz.getName()}</td>
                        <td>${quiz.getTime_end()}</td>
                        <td><a>Result</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <div>I am Admin</div>
    </c:otherwise>
</c:choose>

</body>
</html>