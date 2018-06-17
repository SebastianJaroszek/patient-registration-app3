<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DJ
  Date: 2018-03-19
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tabela wizyt</title>
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
    <br><br>
    Ten harmonogram dotyczy daty: ${dateOfVisits}, ${weekDayName}
    <br><br>

    <table id="visitTable">
        <thead>
        <tr>
            <td>Lekarz/Godzina</td>
            <c:forEach items="${hours}" var="hours">
                <td> ${hours}
                </td>
            </c:forEach>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${doctorDayDtoList}" var="doctor">
            <tr>
                <td>${doctor.doctorDto.name} ${doctor.doctorDto.lastName}
                </td>

                <c:forEach items="${doctor.visits}" var="visit">
                    <td>
                        <c:if test="${!visit.status.name.equals(\"Brak\")}">
                            ${visit.status.name}
                            <c:if test="${!visit.status.name.equals(\"Zajeta\")}">
                                <%--<form action="specjalista" method="post">
                                    <input type="hidden" name="visit" value="${visit}">
                                    <input type="submit" value="zarejestruj się">
                                </form>--%>

                                <%--<form action="specjalista" method="post">
                                    <input type="hidden" name="time" value="${visit.hourOfVisit}">
                                    <input type="hidden" name="date" value="${visit.dayOfVisit}">
                                    <input type="hidden" name="doctorId" value="${visit.doctor.id}">
                                    <input type="submit" value="zarejestruj się">
                                </form>--%>

                                <form:form action="specjalista" modelAttribute="registerDto" method="POST">
                                    <form:input type="hidden" path="date" value="${visit.dayOfVisit}"/>
                                    <form:input type="hidden" path="time" value="${visit.hourOfVisit}"/>
                                    <form:input type="hidden" path="doctorId" value="${visit.doctor.id}"/>
                                    <input type="submit" name="register" value="zarejestruj się">
                                </form:form>

                                <%--<a href="${pageContext.servletContext.contextPath}/rejestracja/specjalista/${visit.hourOfVisit}/${visit.dayOfVisit}/${visit.doctor.id}">zarejestruj się</a>--%>

                            </c:if>
                        </c:if>
                    </td>
                </c:forEach>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <br><br>

    <form action="specjalista" method="GET">
        <input type="hidden" name="date" value="${dateOfVisits.plusDays(1)}">
        <input type="hidden" name="specType" value="${specType}">
        <input type="submit" name="nextday" value="następny dzień">
    </form>
    <form action="specjalista" method="GET">
        <input type="hidden" name="date" value="${dateOfVisits.minusDays(1)}">
        <input type="hidden" name="specType" value="${specType}">
        <input type="submit" name="previousday" value="poprzedni dzień">
    </form>
    <%--<a href="${pageContext.servletContext.contextPath}/rejestracja/nastepnyDzien/${dateOfVisits}">następny dzień</a>--%>
    <form action="specjalista" method="GET">
        Wybierz datę wizyty: <input title="date" name="date" type="date" value="${dateOfVisits}">
        <input type="hidden" name="specType" value="${specType}">
        <input type="submit" value="przejdź do wybranego dnia">
    </form>

</div>
<div class="page-footer">
    <%--stopka--%>
    <footer>Copyright © 2018 Design GangOfThree</footer>
</div>
</body>
</html>
