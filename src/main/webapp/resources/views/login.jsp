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
        <c:url value="/login" var="loginUrl"/>
        <form action="${loginUrl}" method="post">
            <p>
                <label for="username">Login</label>
                <input type="text" id="username" name="username"/>
            </p>
            <p>
                <label for="password">Hasło</label>
                <input type="password" id="password" name="password"/>
            </p>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" class="btn">Log in</button>
        </form>
</div>

<div class="page-footer">
    <%--stopka--%>
    <footer>Copyright © 2018 Design GangOfThree</footer>
</div>

</body>
</html>
