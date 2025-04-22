<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<h2>Users</h2>
<c:forEach var="user" items="${users}">
    <p><c:out value="${user.username} - ${user.email}"/></p>
</c:forEach>
<h1>Add user</h1>
<form action="users" method="POST">
    <p>Enter username:</p>
    <input type="text" name="username"/>
    <p>Enter user email:</p>
    <input type="text" name="email"/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>