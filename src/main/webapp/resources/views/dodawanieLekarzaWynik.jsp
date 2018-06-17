<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie lekarza</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
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
    E-mail: ${addedDoctor.email}<br>
    Login: ${addedDoctor.login}<br>
</p>

</body>
</html>
