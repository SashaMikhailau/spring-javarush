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
    <title>Title</title>
    <link rel="stylesheet" href="meals.css">
</head>
<body>
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
        <%--<c:if test="${item.excess}">
            <c:set var="trclass" value="with_excess"/>
        </c:if>--%>
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
