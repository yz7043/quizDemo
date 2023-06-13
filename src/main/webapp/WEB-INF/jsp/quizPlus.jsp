<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz</title>
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
        h1 {
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
        .answered {
            background-color: lightblue;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div id="count_down">

</div>
<div class="container">
    <div class="card">
        <h1>${quizPlus.getCategory().getName()}${" "} Quiz</h1>
        <form action="/quizplus" method="post">
            <table>
                <c:forEach var="question" items="${quizPlus.getQuestionList()}" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index == quizPlus.getCurIdx()}">
                            <tr>
                                <td>
                                    <b>Question ${status.count}: </b> ${question.getDescription()}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <c:forEach var="choice" items="${quizPlus.getChoices().get(quizPlus.getCurIdx())}" varStatus="choiceStatus">
                                        <div>
                                            <input type="radio"
                                                   id="choice${status.index}${choiceStatus.index}"
<%--                                                   name="choice${question.getQuestion_id()}"--%>
                                                    name="choice${status.index}"
                                                   value="${choice.getChoice_id()}"
                                                   onchange="changeLabelColor(this.id)"
                                                   <c:if test="${choice.getChoice_id() == quizPlus.getUserChoices().get(status.index)}">checked</c:if>
                                            >
                                            <label for="choice${status.index}${choiceStatus.index}">
                                                    ${choice.getDescription()}
                                            </label>
                                        </div>
                                    </c:forEach>
                                    <input type="hidden" name="questionId${status.index}" value="${question.getQuestion_id()}">
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="questionId${status.index}" value="${question.getQuestion_id()}">
                            <input type="hidden" name="userChoice${status.index}" value="${quizPlus.getUserChoices().get(status.index)}">
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>
            <input type="hidden" name="categoryId" value="${quizPlus.getCategory().getCategory_id()}">
            <input type="hidden" name="categoryName" value="${quizPlus.getCategory().getName()}">
            <input type="hidden" name="startTime" value="${quizStartTime}">
            <input type="hidden" name="curIdx" value="${quizPlus.getCurIdx()}">
            <input type="submit" name="finished" value="finished" class="btn btn-success">
            <c:if test="${quizPlus.getCurIdx() > 0}">
                <input type="submit" name="prev" value="prev" class="btn btn-primary">
            </c:if>
            <c:if test="${quizPlus.getCurIdx() < quizPlus.getChoices().size() - 1}">
                <input type="submit" name="next" value="next" class="btn btn-primary">
            </c:if>
        </form>
    </div>
</div>
<script>
    // change color if a choice is selected
    function changeLabelColor(inputId){
        var label = document.querySelector("label[for='" + inputId + "']");
        label.style.backgroundColor = "lightblue";
    }
    // Timer
    var countdown = 30;
    var countdownDisplay = document.getElementById('count_down');
    countdownDisplay.style.fontSize = '2em';

    function startTimer() {
        countdownDisplay.textContent = 'Time left: ' + countdown + ' seconds';
        countdown--;
        if (countdown < 0) {
            var form = document.querySelector('form');
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'finished';
            input.value = 'finished';
            document.querySelector('form').submit();
        } else {
            setTimeout(startTimer, 1000);
        }
    }

    // window.onload = startTimer;

</script>
</body>
</html>
