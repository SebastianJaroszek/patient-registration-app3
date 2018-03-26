<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2018-03-26
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie lekarza</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>
</head>
<body>
<jsp:include page="menu.jsp"/>
<p>
    Dodaj nowego lekarza:
</p>
<p>
    <form:form action="dodajLekarza" method="post" modelAttribute="newDoctor">
        <label>Imię:</label><br>
        <form:input type="text" path="name"/><br>

        <label>Nazwisko:</label><br>
        <form:input type="text" path="lastName"/><br>

        <label>Specjalizacjca:</label><br>
        <select title="specialization" name="specType">
            <c:forEach items="${docSpecEnum}" var="spec">
                <option value="${spec}">${spec}</option>
            </c:forEach>
        </select><br>

        <label>Login:</label><br>
        <form:input path="login"/><br>

        <label>Hasło:</label><br>
        <form:input type="password" path="password"/><br>
        <input type="submit" value="Zatwierdź"><br>

    </form:form>
</p>
</body>
</html>
