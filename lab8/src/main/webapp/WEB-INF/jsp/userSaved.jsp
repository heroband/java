<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User saved</title>
</head>
<body>
<c:if test="${userEmail != null}">
    <p>User with email <c:out value="${userEmail}"/> was added</p>
</c:if>
<c:if test="${userEmail == null}">
    <p>User was not added. Email is empty!</p>
</c:if>
</body>
</html>
