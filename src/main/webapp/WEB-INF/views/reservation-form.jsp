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
<form:form method="post" modelAttribute="reservation">
    Room: <form:select path="room" items="${rooms}" itemLabel="name" itemValue="id"/><br/>
    Reservation start date <form:input path="reservationStartDate" type="date" class="form-control form-control-lg" id="fromBookingDate" placeholder="dd/MM/yyyy"/><br/>
    Reservation finish date: <form:input path="reservationEndDate" type="date" class="form-control form-control-lg" id="fromBookingDate" placeholder="dd/MM/yyyy"/><br/>
<form:hidden path="user"/>
    <form:button>Dodaj</form:button>
</form:form>

</body>
</html>