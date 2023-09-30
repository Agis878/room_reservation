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
<%--@elvariable id="reservation" type=""--%>
<form:form method="post" modelAttribute="reservation">
    Room: <form:select path="room.id" items="${rooms}" itemLabel="name" itemValue="id"/><br/>
    Reservation start date <form:input path="reservationStartDate" type="date" class="form-control form-control-lg"
                                       id="fromBookingDate"/><br/>
    Reservation finish date: <form:input path="reservationEndDate" type="date" class="form-control form-control-lg"
                                         id="fromBookingDate"/><br/>
    <form:hidden path="user" value="${loggedUser.id}" id="user"/>
    <form:button>Dodaj</form:button>
</form:form>

</body>
</html>
