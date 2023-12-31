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

    <!-- Include jQuery library -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <!-- JavaScript for handling deletion confirmation and AJAX call -->
    <script type="text/javascript">
        $(document).ready(function () {
            // Function to handle delete button click
            $(".delete-button").click(function (event) {
                event.preventDefault();
                var confirmation = confirm("Are you sure you want to delete this reservation?");
                if (!confirmation) {
                    return;
                }

                var reservationId = $(this).data("reservation-id");
                var deleteUrl = "/admin/delete/" + reservationId;
                // AJAX call to delete the reservation
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


</head>
<body>

<h2>Reservation list</h2>
<!-- Table to display reservation information -->
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
                        <!-- Display delete button for authenticated users -->
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
