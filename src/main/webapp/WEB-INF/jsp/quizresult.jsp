<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Result</title>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div>
<h2>Question Name</h2>
${quizRes.getQuiz().getName()}
<h2>User Full Name</h2>
${loggedUser.getFirstname()}&nbsp;${loggedUser.getLastname()}
<h2>Test End Time</h2>
${quizResEndTime}
<h2>Result of the Test</h2>
    You score: ${quizResScore} &nbsp; (<c:choose><c:when test="${quizResScore < 3}">Fail</c:when><c:otherwise>Pass</c:otherwise></c:choose>)
<h2>Result Details</h2>
    <form>
        <table>
            <c:forEach var="question" items="${quizRes.getQuestions()}" varStatus="status">
                <tr>
                    <td><b>Question ${status.count}: </b> ${question.getQuestion().getDescription()}</td>
                </tr>
                <tr>
                    <td>
                        <c:forEach var="choice" items="${question.getChoices()}" varStatus="choiceStatus">
                            <div>
                                <input type="radio"
                                       id="choice${status.index}${choiceStatus.index}"
                                       name="question${status.index}"
                                       value="${choice.getDescription()}"

                                    ${question.getQuizQuestion().getUser_choice_id() == choice.getChoice_id() ? 'checked' : ''}
                                       disabled
                                >
                                <label for="choice${status.index}${choiceStatus.index}"
                                       style="${choice.getIs_correct() ? 'color:green;' :
                                         (question.getQuizQuestion().getUser_choice_id() == choice.getChoice_id() ? 'color:red;' : '')}">
                                        ${choice.getDescription()}
                                </label>
                            </div>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    
    <c:choose>
        <c:when test="${not empty isAdmin and isAdmin}">
            <button><a href="/adminQuizResultMgmt">Back</a></button>
        </c:when>
        <c:otherwise>
            <button><a href="/home">Take Another Quiz</a></button>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
