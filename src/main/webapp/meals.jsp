<%--
  Created by IntelliJ IDEA.
  User: azya
  Date: 10.02.19
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${meallist} " var="mealto">
        <tr>
            <%--<td><c:out value="${item.dateTime}"/></td>--%>
            <td>${mealto}</td>
            <td><c:out value="${mealto.calories}"/></td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
