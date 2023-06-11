<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
<%@ include file="navbar.jsp"%>
<h1>${quizCategory.getName()}${" "} Quiz</h1>
<form action="/quiz" method="post">
    <table>
        <c:forEach var="question" items="${quizQuestion.getQuestions()}" varStatus="status">
            <tr>
                <td><b>Question ${status.count}: </b> ${question.getQuestion().getDescription()}</td>
            </tr>
            <tr>
                <td>
                    <c:forEach var="choice" items="${question.getChoices()}" varStatus="choiceStatus">
                        <div>
                            <input type="radio"
                                   id="choice${status.index}${choiceStatus.index}"
                                   name="question${question.getQuestion().getQuestion_id()}"
                                   value="${choice.getChoice_id()}"
                            >
                            <label for="choice${status.index}${choiceStatus.index}">
                                    ${choice.getDescription()}
                            </label>
                        </div>
                    </c:forEach>
                    <input type="hidden" name="questionId${status.index}" value="${question.getQuestion().getQuestion_id()}">
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" name="categoryId" value="${quizCategory.getCategory_id()}">
    <input type="hidden" name="categoryName" value="${quizCategory.getName()}">
    <input type="hidden" name="startTime" value="${quizStartTime}">
    <input type="submit" value="Submit Quiz">
</form>

</body>
</html>
