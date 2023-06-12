<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Question</title>
</head>
<body>
<%@include file="navbar.jsp"%>
<h1>Category</h1>
<h2>${adminModifyQuestion.getCategory().getName()}</h2>
<h1>Question Detail</h1>
<form action="/adminModifyQuestion" method="post">
    <input type="hidden" id="questionId", name="questionId", value="${adminModifyQuestion.getQuestion().getQuestion_id()}">
<h2>Description</h2>
    <div>
    <input type="text" id="description" name="description" value="${adminModifyQuestion.getQuestion().getDescription()}">
    </div>
<h2>Choices</h2>
    <div>
    <c:forEach items="${adminModifyQuestion.getChoices()}" var="choice" varStatus="status">
        <div>
            <input type="radio"
                   id="correctChoice${choice.getChoice_id()}"
                   name="correctChoice"
                   value="${choice.getChoice_id()}"
                   ${choice.getIs_correct() ? 'checked' : ''}
            >
            <label>
                <input type="text"
                       id="choice${choice.getChoice_id()}"
                       name="choice${choice.getChoice_id()}"
                       value="${choice.getDescription()}"
                >
            </label>
        </div>
    </c:forEach>
    </div>
<input type="submit">
</form>
<button><a href="/adminQuestionMgmt">Back</a></button>
<%@include file="alert.jsp"%>
</body>
</html>
