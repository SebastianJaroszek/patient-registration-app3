<%--
  Created by IntelliJ IDEA.
  User: barto
  Date: 26.03.2018
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logowanie</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/resources/css/loginForm.css" rel="stylesheet">

</head>

<body>
<div class="page-header">
    <%--Header strony--%>
    <h1>TwojeZdrowie</h1>
</div>

<div class="page-menu">
    <jsp:include page="menu.jsp"/>
</div>

<div id="panel">
    <form>
        <label for="username">Nazwa użytkownika:</label>
        <input type="text" id="username" name="username">
        <label for="password">Hasło:</label>
        <p><a href="#">Zapomniałeś hasła?</a></p>
        <input type="password" id="password" name="password">
        <div id="lower">
            <input type="submit" value="Login">
        </div>
    </form>
</div>

<div class="page-footer">
    <%--stopka--%>
    <footer>Copyright © 2018 Design GangOfThree</footer>
</div>

</body>
</html>
