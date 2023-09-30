<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 24.09.2023
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Witaj na stronie rezerwacji</h1>
<a href="<c:url value="/user/add"/>">Dodaj rezerwację</a>


<h1>Lista moich rezerwacji</h1>
<c:if test="${not empty reservationList}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Room</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Status</th>
            <th>Action</th>
            <!-- Add more columns if needed -->
        </tr>
        </thead>
        <tbody>
        <c:forEach var="reservation" items="${reservationList}">
            <tr>
                <td>${reservation.id}</td>
                <td>${reservation.room.name}</td>
                <td>${reservation.reservationStartDate}</td>
                <td>${reservation.reservationEndDate}</td>
                <td>${reservation.reservationStatus}</td>
                <td>
                     <a href="<c:out value="/user/update?id=${reservation.id}"/>">Edytuj</a>
                     <a href="<c:out value="/user/delete?id=${reservation.id}"/>">Usuń</a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
