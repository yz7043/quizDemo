<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Question Management</title>
    <style>
        table {
            border-collapse: collapse;
        }

        td, th {
            border: 1px solid black;
            padding: 8px;
        }
        .main-content {
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .center {
             display: flex;
             flex-direction: column;
             align-items: center;
             justify-content: center;
        }
        .category-img {
            width: 200px;  /* Set your desired size */
            height: 200px;  /* Set your desired size */
            object-fit: cover;  /* This will keep the aspect ratio */
            border: 1px solid #ccc;
            padding: 10px;
            margin: 10px;
        }
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="container main-content">
    <div class="row justify-content-center">
        <div class="col-xl-12 center" style="margin-top: 20px;">
            <h1>Categories</h1>
            <div class="container">
                <div class="row row-cols-3">
                    <c:forEach items="${adminQuestionMgmtCategories}" var="category">
                        <div class="col">
                            <img src="${category.getPicture()}" alt="${categories.getName()}" class="category-img border p-3">
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="col-xl-12 center" style="margin-top: 20px">
            <h1>Add a category</h1>
            <div class="center" style="margin-top: 20px">
                <a href="/adminAddCategory"  class="btn btn-outline-primary">Add Category</a>
            </div>
        </div>

        <%--    <button>Add an Category</button>--%>
        <div class="col-xl-12 center" style="margin-top: 20px">
            <h1>Add a question</h1>
            <div class="center" style="margin-top: 20px">
                <a href="/adminAddQuestion"  class="btn btn-outline-primary">Add Question</a>
            </div>
        </div>
        <div class="col-xl-12 center" style="margin-top: 20px">
            <h1>Question banks</h1>
            <table class="table table-striped table-hover">
                <thead>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Edit</th>
                    <th>Toggle</th>
                </thead>
            <c:forEach items="${adminQuestionMgmtQuestions}" var="question">
                <tr>
                    <td>${question.getCategory().getName()}</td>
                    <td>${question.getQuestion().getDescription()}</td>
                    <td>
                        <c:choose>
                            <c:when test="${question.getQuestion().getIs_active()}">
                                <div style="color: green">Active</div>
                            </c:when>
                            <c:otherwise>
                                <div style="color: darkred">Suspended</div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="/adminModifyQuestion?question_id=${question.getQuestion().getQuestion_id()}" class="btn btn-outline-primary">Edit</a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${question.getQuestion().getIs_active()}">
                                <a href="/adminQuestionMgmtToggle?question_id=${question.getQuestion().getQuestion_id()}" class="btn btn-outline-danger">Suspend</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/adminQuestionMgmtToggle?question_id=${question.getQuestion().getQuestion_id()}" class="btn btn-outline-success">Enable</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </div>
    </div>
</div>
    <%@ include file="alert.jsp"%>
</body>
</html>
