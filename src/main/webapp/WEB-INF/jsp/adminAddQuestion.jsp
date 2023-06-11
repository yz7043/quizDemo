<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a question</title>
</head>
<body>
<%@ include file="navbar.jsp"%>
<form action="/adminAddQuestion" method="post">
    <div>
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
    <div>
        <h1>Please enter a question description</h1>
        <input type="text" id="questionDescription" name="questionDescription">
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
                >
            </div>
        </c:forEach>
    </div>

    <input type="submit" value="Submit Question">
</form>

<button><a href="/adminQuestionMgmt">Back</a></button>
<%@ include file="alert.jsp"%>
</body>
</html>
