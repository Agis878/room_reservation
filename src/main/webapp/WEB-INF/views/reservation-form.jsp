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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <title>Add reservation</title>
</head>
<body>

<h1>Add reservation</h1>
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form:form method="post" modelAttribute="reservation">
    Room: <form:select path="room.id" items="${rooms}" itemLabel="name" itemValue="id"/><br/>
    Reservation start date <form:input path="reservationStartDate" type="date" class="form-control form-control-lg"
                                       id="fromBookingDate"/><form:errors path="reservationStartDate"/> <br/>
    Reservation finish date: <form:input path="reservationEndDate" type="date" class="form-control form-control-lg"
                                         id="fromBookingDate"/><form:errors path="reservationEndDate"/> <br/>
    <form:hidden path="user" value="${loggedUser.id}" id="user"/>

    <form:button>Add reservation</form:button>
</form:form>
<a href="/user">Go back</a>
</body>
</html>

