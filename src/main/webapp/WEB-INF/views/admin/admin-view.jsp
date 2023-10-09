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
<div class="navbar">
    <div class="left">
        <h2>Dashboard</h2>
    </div>
    <div class="right">
        <a href="/logout">Logout</a>
    </div>
</div>

<h3>Generate report</h3>

<h4>Reservation status report</h4>
<form action="${pageContext.request.contextPath}/admin/report_1" method="get">
    <div class="form-group">
        <label for="reservationType" class="form-check-label">Reservation status:</label>
        <div class="form-check">
            <input type="radio" name="reservationType" value="active" id="active" class="form-check-input">
            <label for="active" class="form-check-label">Active</label>
        </div>
        <div class="form-check">
            <input type="radio" name="reservationType" value="finished" id="finished" class="form-check-input">
            <label for="finished" class="form-check-label">Finished</label>
        </div>
        <div class="form-check">
            <input type="radio" name="reservationType" value="all" id="all" checked class="form-check-input">
            <label for="all" class="form-check-label">All</label>
        </div>
    </div>
    <button type="submit" class="small-button">Generate report</button>
    <br/><br/>
</form>

<hr>
<h4>Users reservations report</h4>

<form action="${pageContext.request.contextPath}/admin/report_2" method="get">
    <div class="form-group">
        <label for="userSelectionType" class="form-check-label">Select users:</label>
        <div class="form-check">
            <input type="radio" name="userSelectionType" value="all" id="allUsers" checked class="form-check-input">
            <label for="allUsers" class="form-check-label">All</label>
        </div>
        <div class="form-check">
            <input type="radio" name="userSelectionType" value="current" id="chosen" class="form-check-input">
            <label for="chosen" class="form-check-label">Select user</label>
        </div>
        <select name="selectedUsersId" id="selectedUsers" style="display:none;">
            <c:forEach var="user" items="${userList}">
                <option value="${user.id}">${user.username}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="small-button">Generate report</button>
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

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">--%>
<%--    <title>Reports</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="navbar">--%>
<%--    <div class="left">--%>
<%--        <h2>Dashboard</h2>--%>
<%--    </div>--%>
<%--    <div class="right">--%>
<%--        <a href="/logout">Logout</a>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<h3>Generate report</h3>--%>

<%--<h4>Reservation status report</h4>--%>
<%--<form action="${pageContext.request.contextPath}/admin/report_1" method="get">--%>
<%--    &lt;%&ndash;@declare id="reservationtype"&ndash;%&gt;<label for="reservationType">Reservation status:</label>--%>

<%--    <input type="radio" name="reservationType" value="active" id="active"> <label for="active">Active</label>--%>
<%--    <input type="radio" name="reservationType" value="finished" id="finished"> <label for="finished">Finished</label>--%>
<%--    <input type="radio" name="reservationType" value="all" id="all" checked> <label for="all">All</label><br/><br/>--%>
<%--    <button type="submit" class="small-button">Generate report</button>--%>
<%--    <br/><br/>--%>
<%--</form>--%>

<%--<hr>--%>
<%--<h4>Users reservations report</h4>--%>

<%--<form action="${pageContext.request.contextPath}/admin/report_2" method="get">--%>


<%--    &lt;%&ndash;@declare id="userselectiontype"&ndash;%&gt;<label for="userSelectionType">Select users:</label>--%>
<%--    <input type="radio" name="userSelectionType" value="all" id="allUsers" checked> <label for=allUsers>All</label>--%>
<%--    <input type="radio" name="userSelectionType" value="current" id="chosen"> <label for="chosen">Select user</label>--%>


<%--    <select name="selectedUsersId" id="selectedUsers"  style="display:none;">--%>
<%--        <c:forEach var="user" items="${userList}">--%>
<%--            <option value="${user.id}">${user.username}</option>--%>

<%--        </c:forEach>--%>

<%--    </select>--%>

<%--    <br/><br/>--%>

<%--    <button type="submit" class="small-button">Generate report</button>--%>
<%--    <br/><br/>--%>

<%--</form>--%>

<%--<script>--%>
<%--    const userSelectionType = document.getElementsByName('userSelectionType');--%>
<%--    const selectedUsers = document.getElementById('selectedUsers');--%>

<%--    for (let i = 0; i < userSelectionType.length; i++) {--%>
<%--        userSelectionType[i].addEventListener('change', function () {--%>
<%--            selectedUsers.style.display = this.value === 'current' ? 'block' : 'none';--%>
<%--        });--%>
<%--    }--%>
<%--</script>--%>

<%--</body>--%>
<%--</html>--%>



