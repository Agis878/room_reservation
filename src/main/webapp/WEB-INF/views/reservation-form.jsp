<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 25.09.2023
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie rezerwacji</title>
</head>
<body>
<%-- Display error message if present --%>
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
<%-- Wyświetl błędy walidacji dla pola reservationStartDate --%>
<c:if test="${not empty errors.reservationStartDate}">
    <p style="color: red;">${errors.reservationStartDate[0]}</p>
</c:if>

<%-- Wyświetl błędy walidacji dla pola reservationEndDate --%>
<c:if test="${not empty errors.reservationEndDate}">
    <p style="color: red;">${errors.reservationEndDate[0]}</p>
</c:if>
<%--@elvariable id="reservation" type=""--%>
<form:form method="post" modelAttribute="reservation">
    Room: <form:select path="room.id" items="${rooms}" itemLabel="name" itemValue="id"/><br/>
    Reservation start date <form:input path="reservationStartDate" type="date" class="form-control form-control-lg"
                                       id="fromBookingDate"/><form:errors path="reservationStartDate"/> <br/>
    Reservation finish date: <form:input path="reservationEndDate" type="date" class="form-control form-control-lg"
                                         id="fromBookingDate"/><form:errors path="reservationEndDate"/> <br/>
    <form:hidden path="user" value="${loggedUser.id}" id="user"/>
    <form:errors path="*"/>
    <form:button>Dodaj</form:button>
</form:form>

</body>
</html>
