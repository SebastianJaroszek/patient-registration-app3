<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PokazywarkaWizyt</title>
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
    Lista wizyt:
    <ul class="b">
        <c:forEach items="${wizyty}" var="wizyta">
            <li class="b">${wizyta.dayOfVisit},
                    ${wizyta.hourOfVisit},
                Dr. ${wizyta.doctor.lastName}</li>
        </c:forEach>

        User ID = ${userID}
    </ul>
</div>

<div class="page-footer">
    <%--stopka--%>
    <footer>Copyright © 2018 Design GangOfThree</footer>
</div>


</body>
</html>
