<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>doctors</title>
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
    Lista lekarzy:

    <form:form modelAttribute="newTimetable" method="GET" action="dodawanieHarmonogramu">
        <ol class="a">
            <c:forEach items="${doctors}" var="doctor" varStatus="doctorLoop">
                <li class="a">${doctor.name} ${doctor.lastName}, ${doctor.specialization}
                    <input type="hidden" name="doctorsId[${doctorLoop.index}]" value="${doctor.id}">
                    <input type="checkbox" name="isChecked[${doctorLoop.index}]">
                </li>
            </c:forEach>
        </ol>
        <input type="submit" value="dodaj harmonogram">
    </form:form>
</div>

<div class="page-footer">
    <%--stopka--%>
    <footer>Copyright © 2018 Design GangOfThree</footer>
</div>


</body>
</html>
