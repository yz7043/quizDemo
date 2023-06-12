<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Question</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        .container{
            margin-top: 10vh;
        }
        .center {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        h1{
            background-color: #f2f2f2;
        }
        h2{
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container">
    <div class="card">
        <div class="card-body">
            <div class="center">
                <div class="col-12">
                    <h1 class="center">
                        <b>Category</b>
                    </h1>
                </div>
                <h2>${adminModifyQuestion.getCategory().getName()}</h2>
                <div class="col center">
                    <img src="${adminModifyQuestion.getCategory().getPicture()}" alt="${categories.getName()}" class="category-img border p-3">
                </div>
                <div class="col-12">
                    <h1 class="center"><b>Question Detail</b></h1>
                </div>
            <form action="/adminModifyQuestion" method="post">
                <input type="hidden" id="questionId", name="questionId", value="${adminModifyQuestion.getQuestion().getQuestion_id()}">
                <div class="col-12">
                    <h2 class="center">Description</h2>
                </div>
                <div class="col-12">
<%--                    <input type="text" id="description" name="description" value="${adminModifyQuestion.getQuestion().getDescription()}">--%>
                    <textarea id="description" name="description" rows="4" cols="200"
                              style="overflow-y: scroll;" maxlength="1000" class="form-control"
                    >${adminModifyQuestion.getQuestion().getDescription()}</textarea>
                </div>
                <div class="col-12" style="margin-bottom: 5px; margin-top: 5px">
                    <h2 class="center">Choices</h2>
                </div>
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
                                       maxlength="300"
                                >
                            </label>
                        </div>
                    </c:forEach>
                    </div>
                <div class="col-12 center" style="margin-top: 10px; margin-bottom: 10px">
                    <input type="submit" class="center  btn btn-outline-primary">
                </div>
            </form>
                <div class="col-12 center">
                    <a href="/adminQuestionMgmt" style="color: red" class="center btn btn-outline-danger">Back</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="alert.jsp"%>
</body>
</html>
