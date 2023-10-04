<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 01.10.2023
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>report2</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<h2>Reservation list</h2>

<table class="styled-table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Start date</th>
        <th>End date</th>
        <th>Status</th>
        <th>Reservation date</th>
        <th>Room</th>
        <th>User</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reservation" items="${reservations}">
        <tr>
            <td>${reservation.id}</td>
            <td>${reservation.reservationStartDate}</td>
            <td>${reservation.reservationEndDate}</td>
            <td>${reservation.reservationStatus}</td>
            <td>${reservation.reservationDate}</td>
            <td>${reservation.room.name}</td>
            <td>${reservation.user.username}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/admin">Go back</a>

</body>
</html>
