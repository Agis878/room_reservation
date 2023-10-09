<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <title>Home</title>
</head>
<body>

<h2>Welcome to the booking page</h2>

<p>To use the booking feature, please log in or register:</p>
<div class="button-group">
    <div><a href="/login">Login</a> |

        <a href="/register">Register</a></div>
</div>

<%--<ul>--%>
<%--    <li><a href="/login">Login</a></li>--%>
<%--    <li><a href="/register">Register</a></li>--%>
<%--</ul>--%>

</body>

</html>