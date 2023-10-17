<%--
  Created by IntelliJ IDEA.
  User: agnieszka
  Date: 21.09.2023
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete reservation</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h2>Do you like to remove your reservation "${reservation.id}"?</h2>
<form method="post">
    <div class="button-group">
        <button type="submit">Yes</button>|<a href="/user">No</a>
    </div>
    <input type="hidden" name="id" value="${reservation.id}"/>
</form>
</body>
</html>
