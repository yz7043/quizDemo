<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
<div>

    <form method="post" action="/quiz">
        <%@ include file="navbar.jsp"%>
        <%-- Question description --%>
        <p>${question.description}</p>

        <%-- Dynamically render the choices --%>
        <c:forEach items="${question.choices}" var="choice" varStatus="loop">
            <div>
                <input type="radio"
                       name="selectedChoiceId"
                       value="${choice.id}"/>
                    ${choice.description}
            </div>
        </c:forEach>

        <%-- Button to submit quiz --%>
        <button type="submit">submit</button>

    </form>
</div>
</body>
</html>
