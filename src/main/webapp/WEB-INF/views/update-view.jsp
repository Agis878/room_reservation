<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edytuj autora</title>
</head>
<body>
<%--@elvariable id="reservation" type=""--%>
<form:form method="post" modelAttribute="reservation">

    Room: <form:select path="room.id" items="${rooms}" itemLabel="name" itemValue="id"/><br/>
    Reservation start date <form:input path="reservationStartDate" type="date" class="form-control form-control-lg"
                                       id="fromBookingDate"/><br/>
    Reservation finish date: <form:input path="reservationEndDate" type="date" class="form-control form-control-lg"
                                         id="fromBookingDate"/><br/>
    <form:hidden path="reservationStatus"/></td>
    <form:hidden path="user" value="${loggedUser.id}" id="user"/>
    <form:hidden path="id"/>
    <form:button>Save</form:button>
</form:form>
</body>
</html>