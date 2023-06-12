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
    <h1>${quizCategory.getName()}${" "} Quiz</h1>
    <form action="/quiz" method="post">
        <table>
            <c:forEach var="question" items="${quizQuestion.getQuestions()}" varStatus="status">
                <tr>
                    <td>
                        <b>Question ${status.count}: </b> ${question.getQuestion().getDescription()}
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:forEach var="choice" items="${question.getChoices()}" varStatus="choiceStatus">
                            <div>
                                <input type="radio"
                                       id="choice${status.index}${choiceStatus.index}"
                                       name="question${question.getQuestion().getQuestion_id()}"
                                       value="${choice.getChoice_id()}"
                                       onchange="changeLabelColor(this.id)"
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
        <input type="submit" value="Submit Quiz" class="btn btn-success">
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
            document.querySelector('form').submit();
        } else {
            setTimeout(startTimer, 1000);
        }
    }

    window.onload = startTimer;

</script>
</body>
</html>
