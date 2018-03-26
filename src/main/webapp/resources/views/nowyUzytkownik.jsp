<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrzej
  Date: 3/21/2018
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja nowego uzytkownika</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>
</head>
<body>

<form:form action="nowyUzytkownik/zarejestruj" method="post" modelAttribute="newUser">
    <label>Imie:</label><br>
    <form:input type="text" path="firstName"/><br>

    <label>Nazwisko:</label><br>
    <form:input type="text" path="lastName"/><br>
    <label>E-mail:</label><br>
    <form:input path="email"/><br>
    <label>Login:</label><br>
    <form:input path="login"/><br>

    <label>Haslo:</label><br>
    <form:input type="password" path="password"/><br>
    <input type="submit" value="Wyślij"><br>

</form:form>


</body>
</html>
