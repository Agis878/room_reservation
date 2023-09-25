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
    <title>Title</title>
</head>
<body>
<h1>Witaj na stronie rezerwacji</h1>
<a href="<c:url value="/login/user/add"/>">Dodaj rezerwację</a>


<h1>Lista moich rezerwacji</h1>
<table border="1">

    <thead>
    <th>ID</th>

    <th>isbn</th>

    <th>title</th>

    <th>author</th>

    <th>action</th>

    </thead>

    <tbody>

<%--    <c:forEach items="${}" var="book">--%>

<%--        <tr>--%>

<%--            <td><c:out value="${book.id}"/></td>--%>
<%--            <td><c:out value="${book.isbn}"/></td>--%>

<%--            <td><c:out value="${book.title}"/></td>--%>

<%--            <td><c:out value="${book.author}"/></td>--%>

<%--            <td>--%>

<%--                <a href="<c:out value="/login/user/update?id=${book.id}"/>">Edytuj</a>--%>

<%--                <a href="<c:out value="/login/user/delete?id=${book.id}"/>">Usuń</a>--%>



<%--            </td>--%>

<%--        </tr>--%>

<%--    </c:forEach>--%>

    </tbody>

</table>
</body>
</html>
