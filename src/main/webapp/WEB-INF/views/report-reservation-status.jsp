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
    <title>Title</title>
</head>
<body>

<h2>Lista Rezerwacji</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Data Rozpoczęcia</th>
        <th>Data Zakończenia</th>
        <th>Status</th>
        <th>Data Rezerwacji</th>
        <th>Pokój</th>
        <th>Użytkownik</th>
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
            <td>${reservation.user}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
