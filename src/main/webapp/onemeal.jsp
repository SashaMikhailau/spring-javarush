<%--
  Created by IntelliJ IDEA.
  User: azya
  Date: 12.02.19
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<form method="post" action="meals">
    Date and time:
    <input type="datetime-local" name="datetime" value="<c:out value="${meal.dateTime}"/>"/>
    <br/>
    Description:
    <input type="text" name="description" value="<c:out value="${meal.description}"/>"/>
    <br/>
    Calories:
    <input type="number" name="calories" value="<c:out value="${meal.calories}"/>"/>
    <br/>
    <input type="hidden" name="mealId" value="<c:out value="${meal.id}"/>">
    <input type="submit" value="Submit" >
</form>
</body>
</html>
