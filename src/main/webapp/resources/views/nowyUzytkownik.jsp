<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja nowego uzytkownika</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>
</head>
<body>
<div class="page-header">
    <%--Header strony--%>
    <h1>TwojeZdrowie</h1>
</div>

<div class="page-menu">
    <jsp:include page="menu.jsp"/>
</div>
<div class="page-text">
    <%--Część odpowiedzialna za wyświetlanie treści strony--%>
    <form:form action="nowyUzytkownik/zarejestruj" method="post" modelAttribute="newUser">
        <label>Imie:</label><br>
        <form:input type="text" path="firstName"/><br>
        <label>Nazwisko:</label><br>
        <form:input type="text" path="lastName"/><br>
        <label>E-mail:</label><br>
        <form:input path="email"/><br>
        <label>Login:</label><br>
        <form:input path="login"/><br>

        <label>Haslo, minimum 7 znakow:</label><br>
        <form:input type="password" path="password"/><br>
        <label>Powtorz haslo:</label><br>
        <form:input type="password" path="matchingPassword"/><br>
        <input type="submit" value="Wyślij"><br>

    </form:form>
</div>

<div class="page-footer">
    <%--stopka--%>
    <footer>Copyright © 2018 Design GangOfThree</footer>
</div>

</body>
</html>
