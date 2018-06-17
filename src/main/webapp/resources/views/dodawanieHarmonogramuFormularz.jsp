<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tworzenie harmonogramu</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp"/>
<p>
    Dodaj harmonogram dla wybranych lekarzy:
</p>
<p>
    <form:form action="dodawanieHarmonogramu" method="POST" modelAttribute="newTimetable">
        <label>Dzień tygodnia:</label><br>
        <form:select path="dayOfWeek">
            <c:forEach items="${daysOfWeek}" var="day">
                <option value="${day}">${day}</option>
            </c:forEach>
        </form:select><br><br>

        <label>Godzina rozpoczęcia:</label><br>
        <form:select path="fromTime">
            <c:forEach items="${openHours}" var="hour">
                <option value="${hour}">${hour}</option>
            </c:forEach>
        </form:select><br><br>

        <label>Godzina zakończenia:</label><br>
        <form:select path="toTime">
            <c:forEach items="${openHours}" var="hour">
                <option value="${hour}">${hour}</option>
            </c:forEach>
        </form:select><br><br>
        <c:forEach items="${doctorsId}" var="doctorId" varStatus="doctorLoop">
            <input type="hidden" name="doctorsId[${doctorLoop.index}]" value="${doctorId}">
        </c:forEach>
        <input type="submit" value="Zatwierdź"><br>

    </form:form>
</p>
</body>
</html>
