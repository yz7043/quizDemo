<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a question</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
    .center {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    }
    h1{
        background-color: #f2f2f2;
    }
    </style>

</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="container" style="margin-top: 10px">
    <div class="card">
        <div class="card-body">
        <form action="/adminAddQuestion" method="post">
            <div class="col-12">
                <h1>Please select a category</h1>
                <table>
                    <c:forEach items="${adminQuestionMgmtCategories}" var="category">
                        <tr>
                            <td>
                                <div>
                                    <input type="radio"
                                           id="${category.getName()}"
                                           name="category"
                                           value="${category.getCategory_id()}"
                                    >
                                    <label>
                                            ${category.getName()}
                                    </label>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="col-12">
                <h1>Please enter a question description</h1>
                <textarea id="questionDescription" name="questionDescription" rows="4" cols="200"
                          style="overflow-y: scroll;" maxlength="1000" class="form-control"></textarea>
                <h1>Please select one correct choice and fill the choices</h1>
                <c:forEach varStatus="status" begin="1" end="${adminAddQuestionNumber}">
                    <div>
                        <input type="radio"
                               id="correctChoice${status.index}"
                               name="correctChoice"
                               value="${status.index}"
                        >
                        <label>
                            Correct Choice
                        </label>
                        <input type="text"
                               id="choice${status.index}"
                               name="choice${status.index}"
                               placeholder="Choice ${status.index}"
                               maxlength="300"
                        >
                    </div>
                </c:forEach>
            </div>
            <div class="col-12">
                <input type="submit" value="Submit Change" class="btn btn-outline-primary">
            </div>
        </form>
            <div class="col-12" style="margin-top: 10px">
                <a href="/adminQuestionMgmt" class="btn btn-outline-danger">Back</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="alert.jsp"%>
</body>
</html>
