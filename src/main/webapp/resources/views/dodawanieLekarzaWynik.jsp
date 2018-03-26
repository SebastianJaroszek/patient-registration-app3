<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2018-03-26
  Time: 18:42
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
    Lekarz został dodany pomyślnie.
</p>
<p>
    Identyfikator: ${addedDoctor.id}<br>
    Imię: ${addedDoctor.name}<br>
    Nazwisko: ${addedDoctor.lastName}<br>
    Specjalizaja: ${addedDoctor.specialization.name}<br>
    Login: ${addedDoctor.login}<br>
</p>

</body>
</html>
