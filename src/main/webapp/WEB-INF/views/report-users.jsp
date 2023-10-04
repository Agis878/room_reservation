<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <title>report-users</title>
</head>
<body>

<h2>Users reservations</h2>

<c:choose>
    <c:when test="${not empty reservations}">
        <c:set var="noReservationsMessage" value="${reservations.isEmpty() ? 'No reservations available' : null}" />
    </c:when>
    <c:when test="${not empty reservationsForCurrentUser}">
        <c:set var="noReservationsMessage" value="${reservationsForCurrentUser.isEmpty() ? 'No reservations for the current user' : null}" />
    </c:when>
    <c:otherwise>
        <c:set var="noReservationsMessage" value="${empty reservations ? 'No reservations available' : null}" />
    </c:otherwise>
</c:choose>

<c:if test="${not empty reservations}">
    <table class="styled-table">>
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
</c:if>

<c:if test="${not empty reservationsForCurrentUser}">
    <table>
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
        <c:forEach var="reservation" items="${reservationsForCurrentUser}">
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
</c:if>


<a href="/admin">Go back</a>
</body>
</html>


