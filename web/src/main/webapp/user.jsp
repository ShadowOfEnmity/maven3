<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: stas
  Date: 16.04.2024
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
<c:set var="user" value="${requestScope.user}"/>
<form action="/user/${user.id}" method="post">
    <dl>
        <dt>ID: </dt>
        <dd><input type="number" name="id" value="${user.id}" placeholder="${user.id}" disabled/></dd>
    </dl>
    <dl>
        <dt>SURNAME: </dt>
        <dd><input type="text" name="surname" value="${user.surname}" placeholder="${user.surname}" /></dd>
    </dl>
    <dl>
        <dt>NAME: </dt>
        <dd><input type="text" name="name" value="${user.name}" placeholder="${user.name}" /></dd>
    </dl>
    <dl>
        <dt>PATRONYMIC: </dt>
        <dd><input type="text" name="patronymic" value="${user.patronymic}" placeholder="${user.patronymic}" /></dd>
    </dl>
    <button type="submit">Save</button>
    </br>
    <a href="/user">Go back to users page </a>
</form>
</body>
</html>
