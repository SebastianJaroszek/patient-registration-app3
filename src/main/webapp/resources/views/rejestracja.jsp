<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>
<body>
<div class="page-header">
    <%--Header strony--%>
    <h1>TwojeZdrowie</h1>
</div>
<jsp:include page="menu.jsp"/>
<div class="page-text">
    <div class="a">
        <form action="rejestracja/specjalista" method="get">
            <label>Specjalizacja:</label>
            <select name="specType">
                <c:forEach items="${docSpecEnum}" var="spec">
                    <option value="${spec}">${spec}</option>
                </c:forEach>
            </select>
            <input type="submit" value="wybierz">
        </form>
    </div>
</div>
<div class="page-footer">
    <%--stopka--%>
    <footer>Copyright © 2018 Design GangOfThree</footer>
</div>
</body>
</html>
