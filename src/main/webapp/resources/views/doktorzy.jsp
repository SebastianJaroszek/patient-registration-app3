<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrzej
  Date: 3/18/2018
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>doctors</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>

<body>
<jsp:include page="menu.jsp"/>
Lista lekarzy:

<ol class="a">
    <c:forEach items="${doctors}" var="doctor">
        <li class="a">Dr
                ${doctor.name}
                ${doctor.lastName},
                ${doctor.specialization.name}
        </li>
    </c:forEach>
</ol>

</body>
</html>
