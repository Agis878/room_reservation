<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".delete-button").click(function (event) {
                event.preventDefault();
                var confirmation = confirm("Are you sure you want to delete this reservation?");
                if (!confirmation) {
                    return;
                }

                var reservationId = $(this).data("reservation-id");
                var deleteUrl = "/admin/delete/" + reservationId;

                $.ajax({
                    type: "DELETE",
                    url: deleteUrl,
                    success: function (data) {
                        alert("Reservation deleted successfully");

                        var deletedRow = $("tr[data-reservation-id='" + reservationId + "']");
                        deletedRow.remove();
                        window.location.reload();
                    },
                    error: function (error) {
                        alert("Error deleting reservation");
                    },
                    complete: function () {

                    }
                });
            });
        });
    </script>

    <title>report-users</title>
</head>
<body>

<h2>Users reservations</h2>
<c:choose>
    <c:when test="${not empty noReservationsMessage}">
        <p>${noReservationsMessage}</p>
    </c:when>
    <c:when test="${not empty reservations}">
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
                <th>Action</th>
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
                    <td>
                        <sec:authorize access="isAuthenticated()">
                            <button class="small-button delete-button" data-reservation-id="${reservation.id}">Delete
                            </button>
                        </sec:authorize>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>

    <c:when test="${not empty noReservationsMessageCurrentUser}">
        <p>${noReservationsMessageCurrentUser}</p>
    </c:when>
    <c:when test="${not empty reservationsForCurrentUser}">
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
                <th>Action</th>
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
                    <td>
                        <sec:authorize access="isAuthenticated()">
                            <button class="small-button delete-button" data-reservation-id="${reservation.id}">Delete
                            </button>
                        </sec:authorize>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
</c:choose>

<a href="/admin">Go back</a>
</body>
</html>


