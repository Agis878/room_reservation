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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <title>Reports</title>
</head>
<body>
<div  class="navbar">
<a href="/logout">Logout</a><br>
<h2>Dashboard</h2>
</div>
<h3>Select report</h3>

<h4>Reservation status</h4>
<form action="${pageContext.request.contextPath}/admin/report_1" method="get">
    <%--@declare id="reservationtype"--%><label for="reservationType">Reservation status:</label>

    <input type="radio" name="reservationType" value="active" id="active"> <label for="active">Active</label>
    <input type="radio" name="reservationType" value="finished" id="finished"> <label for="finished">Finished</label>
    <input type="radio" name="reservationType" value="all" id="all" checked> <label for="all">All</label><br/><br/>
    <button type="submit">Generate report</button>
    <br/><br/>
</form>


<h4>Users reservations</h4>

<form action="${pageContext.request.contextPath}/admin/report_2" method="get">


<%--@declare id="userselectiontype"--%><label for="userSelectionType">Select users:</label>
<input type="radio" name="userSelectionType" value="all" id="allUsers" checked> <label for=allUsers>All</label>
<input type="radio" name="userSelectionType" value="current" id="chosen"> <label for="chosen">Select user</label>


    <select name="selectedUsersId" id="selectedUsers"  style="display:none;">
    <c:forEach var="user" items="${userList}">
        <option value="${user.id}">${user.userLogin}</option>

    </c:forEach>

</select>

    <br/><br/>

    <button type="submit">Generate report</button>
    <br/><br/>

</form>

<script>
    const userSelectionType = document.getElementsByName('userSelectionType');
    const selectedUsers = document.getElementById('selectedUsers');

    for (let i = 0; i < userSelectionType.length; i++) {
        userSelectionType[i].addEventListener('change', function () {
            selectedUsers.style.display = this.value === 'current' ? 'block' : 'none';
        });
    }
</script>

</body>
</html>



