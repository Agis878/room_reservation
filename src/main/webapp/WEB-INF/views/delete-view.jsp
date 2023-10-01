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
    <link rel="stylesheet" type="text/css" href="href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<p>Do you like to remove your reservation "${reservation.id}"?</p>
<form method="post">
  <input type="hidden" name="id" value="${reservation.id}"/>
  <button type="submit">Tak</button> | <a href="/user">Nie</a>
</form>
</body>
</html>
