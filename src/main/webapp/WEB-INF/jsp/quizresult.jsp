<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Result</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            width: 500px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-top: 20px;
            margin-bottom: 10px;
        }
        .result {
            color: green;
            font-weight: bold;
        }
        .result.fail {
            color: red;
        }
        table {
            margin-top: 20px;
        }
        .form-group > label {
            margin-right: 10px;
        }
        button {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="container">
    <div class="card">
        <h2><b>Question Name</b></h2>
        ${quizRes.getQuiz().getName()}
        <h2><b>User Full Name</b></h2>
        ${loggedUser.getFirstname()}&nbsp;${loggedUser.getLastname()}
        <h2><b>Test End Time</b></h2>
        ${quizResEndTime}
        <h2><b>Result of the Test</b></h2>
        <p class="result ${quizResScore < 3 ? 'fail' : ''}">You score: ${quizResScore} (${quizResScore < 3 ? 'Fail' : 'Pass'})</p>
        <h2><b>Result Details</b></h2>
        <form>
            <table>
                <c:forEach var="question" items="${quizRes.getQuestions()}" varStatus="status">
                    <tr>
                        <td><b>Question ${status.count}: </b> ${question.getQuestion().getDescription()}</td>
                    </tr>
                    <tr>
                        <td>
                            <c:forEach var="choice" items="${question.getChoices()}" varStatus="choiceStatus">
                                <div class="form-group">
                                    <input type="radio"
                                           id="choice${status.index}${choiceStatus.index}"
                                           name="question${status.index}"
                                           value="${choice.getDescription()}"
                                        ${question.getQuizQuestion().getUser_choice_id() == choice.getChoice_id() ? 'checked' : ''}
                                           disabled>
                                    <label for="choice${status.index}${choiceStatus.index}"
                                           class="${choice.getIs_correct() ? 'text-success' : (question.getQuizQuestion().getUser_choice_id() == choice.getChoice_id() ? 'text-danger' : '')}">
                                            ${choice.getDescription()}
                                    </label>
                                </div>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>

        <div class="mt-4">
            <c:choose>
                <c:when test="${not empty isAdmin and isAdmin}">
                    <button class="btn btn-primary"><a href="/adminQuizResultMgmt" class="text-white">Back</a></button>
                </c:when>
                <c:otherwise>
                    <button class="btn btn-primary"><a href="/home" class="text-white">Take Another Quiz</a></button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
