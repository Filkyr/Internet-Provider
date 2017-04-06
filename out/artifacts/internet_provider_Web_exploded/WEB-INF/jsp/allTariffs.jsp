<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Tariffs</title>
</head>
<body>
<table>
    <c:forEach var="tariff" items="${tariffs}">
        <tr>
            <td><c:out value="${tariff.id}"/></td>
            <td><c:out value="${tariff.name}"/></td>
            <td><c:out value="${tariff.monthlyCost}"/></td>
            <td><c:out value="${tariff.period}"/></td>
            <td><c:out value="${tariff.loadSpeed}"/></td>
            <td><c:out value="${tariff.uploadSpeed}"/></td>
            <td><c:out value="${tariff.description}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
