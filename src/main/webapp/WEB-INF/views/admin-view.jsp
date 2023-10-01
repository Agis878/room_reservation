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
    <title>Zestawienie Rezerwacji</title>
</head>
<body>


<p>Generuj raport</p>

<div>Status rezerwacji</div>
<form action="${pageContext.request.contextPath}/admin/report_1" method="get">
    <%--@declare id="reservationtype"--%><label for="reservationType">Rodzaj Rezerwacji:</label>

    <input type="radio" name="reservationType" value="active" id="active"> <label for="active">Aktywne</label>
    <input type="radio" name="reservationType" value="finished" id="finished"> <label for="finished">Zakończone</label>
    <input type="radio" name="reservationType" value="all" id="all" checked> <label for="all">Wszystkie</label>
    <button type="submit">Generuj Zestawienie</button>
    <br/><br/>
</form>


<div>Rezerwacje użytkowników</div>

<form action="${pageContext.request.contextPath}/admin/report_2" method="get">


<%--@declare id="userselectiontype"--%><label for="userSelectionType">Rodzaj Wyboru Użytkowników:</label>
<input type="radio" name="userSelectionType" value="all" id="wszyscy" checked> <label for="wszyscy">Wszyscy Użytkownicy</label>
<input type="radio" name="userSelectionType" value="current" id="wybrani"> <label for="wybrani">Wybrani Użytkownicy</label>

<!-- Dodaj rozwijaną listę tylko w przypadku, gdy wybrano "Wybrani Użytkownicy" -->
<%--<select name="selectedUsersId" id="selectedUsers" multiple style="display:none;">--%>
    <select name="selectedUsersId" id="selectedUsers"  style="display:none;">
    <c:forEach var="user" items="${userList}">
        <option value="${user.id}">${user.userLogin}</option>
        <!-- Ustaw odpowiednie wartości i etykiety dla swojego modelu User -->
    </c:forEach>
    <!-- Dodaj więcej opcji według potrzeb -->
</select>

    <br/><br/>

    <button type="submit">Generuj Zestawienie</button>
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



