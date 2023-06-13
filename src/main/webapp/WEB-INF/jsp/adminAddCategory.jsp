<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a Category</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        .center {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        h2{
            background-color: #f2f2f2;
        }
    </style>

</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="container" style="margin-top: 10px">
    <div class="card">
        <div class="card-body">
            <form action="/adminAddCategory" method="post">
                <div class="col-12">
                    <h2>Please enter a category name</h2>
                    <input id="category" name="category" type="text" maxlength="50" class="col-5">
                </div>
                <div class="col-12">
                    <h2>Please enter a URL of image represent for the category</h2>
                    <input type="text" id="picture" name="picture" maxlength="500" class="col-12">
                </div>
                <div class="col-12" style="margin-top: 5px">
                    <c:if test="${not empty alertMsg}">
                        <small class="6" style="color: red">${alertMsg}</small>
                    </c:if>
                </div>
                <div class="col-12" style="margin-top: 10px">
                    <input type="submit" value="Add" class="btn btn-outline-primary">
                </div>
            </form>
            <div class="col-12" style="margin-top: 10px">
                <a href="/adminQuestionMgmt" class="btn btn-outline-danger">Back</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
