<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <title>Reservation update</title>
</head>
<body>

<h2>Update reservation</h2>
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
<%--@elvariable id="reservation" type=""--%>
<form:form method="post" modelAttribute="reservation">

    Room: <form:select path="room.id" items="${rooms}" itemLabel="name" itemValue="id"/><br/>
    Reservation start date <form:input path="reservationStartDate" type="date" class="form-control form-control-lg"
                                       id="fromBookingDate"/><br/><form:errors path="reservationStartDate"/> <br/>
    Reservation finish date: <form:input path="reservationEndDate" type="date" class="form-control form-control-lg"
                                         id="fromBookingDate"/><br/><form:errors path="reservationEndDate"/> <br/>
    <form:hidden path="reservationStatus" value="active"/></td>

    <form:hidden path="user" value="${loggedUser.id}" id="user"/>
    <form:hidden path="id"/>
    <form:button>Save changes</form:button>
</form:form>
<a href="/user">Go back</a>
</body>
</html>