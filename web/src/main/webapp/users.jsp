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
<html>
<head>
    <title>List of users</title>
    <style>
        table,
        th,
        td {
            padding: 10px;
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<c:set var="users" value="${requestScope.users}"/>

<h1>List of users</h1>

<table>
        <tr>
            <th>Employee ID</th>
            <th>Surname</th>
            <th>Name</th>
            <th>Patronymic</th>
        </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.surname}</td>
            <td>${user.name}</td>
            <td>${user.patronymic}</td>
            <td>
                <form action="/user/${user.id}" method="get" >
                    <input type="submit" value="Edit">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
