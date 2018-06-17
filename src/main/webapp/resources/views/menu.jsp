<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<ul class="m">
    <li class="m"><a href="${pageContext.servletContext.contextPath}/main">Home</a></li>
    <li class="m"><a href="${pageContext.servletContext.contextPath}/login">Logowanie</a></li>
    <li class="m"><a href="${pageContext.servletContext.contextPath}/nowyUzytkownik">Załóż konto</a></li>
    <li class="m"><a href="${pageContext.servletContext.contextPath}/rejestracja">Rejestracja Wizyty</a></li>
    <li class="m"><a href="${pageContext.servletContext.contextPath}/wizytyPacjenta">Historia Wizyt</a></li>
    <li class="m"><a href="${pageContext.servletContext.contextPath}/doktorzy">Lekarze</a></li>
    <li class="m"><a href="${pageContext.servletContext.contextPath}/dodajLekarza">Dodaj lekarza</a></li>
    <li class="m"><a href="<c:url value="/logout" />">Wyloguj</a></li>

</ul>
