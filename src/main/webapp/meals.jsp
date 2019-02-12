<%--
  Created by IntelliJ IDEA.
  User: azya
  Date: 10.02.19
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>MealList</title>
    <link rel="stylesheet" href="meals.css">
</head>
<body>
<c:set var="name" value="Sasha"/>
<h1>Hello ${name} </h1>
<table>
    <tr>
        <th>
            Date Time
        </th>
        <th>
            Description
        </th>
        <th>
            Calories
        </th>
    </tr>
    <c:forEach var="item" items="${list}">
        <c:set var="trclass" value="${item.excess?'with_excess':'without_excess'}"/>
    <tr class="${trclass}">
        <td>
            <c:out value="${fn:replace(item.dateTime,'T',' ')}"/>
        </td>
        <td>
            <c:out value="${item.description}"/>
        </td>
        <td>
            <c:out value="${item.calories}"/>
        </td>
    </tr>

    </c:forEach>
</table>

</body>
</html>
