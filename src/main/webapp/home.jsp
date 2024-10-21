<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.medicalClinic"/></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            text-align: center;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .server-time {
            font-size: 1.2em;
            color: #666;
        }
        .main-link, .logout-button {
            display: block;
            width: calc(60% - 100px);
            margin: 10px auto;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
        }
        .main-link:hover, .logout-button:hover {
            background-color: #0056b3;
        }
        .header {
            margin-bottom: 20px;
            text-align: right;
            padding: 10px;
            background-color: #f4f4f4;
        }
        .header a {
            margin: 0 5px;
            text-decoration: none;
            color: white;
            background-color: #007bff;
            padding: 5px 10px;
            border-radius: 3px;
            border: 1px solid #007bff;
        }
        .header a:hover {
            background-color: #0056b3;
        }
        .footer {
            margin-top: 20px;
            text-align: center;
            color: #666;
            font-size: 1.1em;
        }
    </style>
</head>
<body>
<div class="header">
    <a href="?lang=pl">pl</a> | <a href="?lang=en">en</a> | <a href="?lang=de">de</a>
</div>
<div class="container">
    <h2><spring:message code="label.welcomeToClinic"/></h2>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="appUserRole" class="main-link"><spring:message code="label.role"/> </a>
    <a href="/visit_types" class="main-link"><spring:message code="label.visit_types"/> </a>
    </sec:authorize>

    <sec:authorize access="hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')">
        <a href="/visits" class="main-link"><spring:message code="label.visits"/> </a>
        <a href="/prescriptions" class="main-link"><spring:message code="label.prescriptions"/> </a>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_USER')">
        <a href="/visits" class="main-link"><spring:message code="label.visits"/> </a>
        <a href="/visits/add" class="main-link"><spring:message code="label.addVisit"/> </a>
        <a href="/prescriptions" class="main-link"><spring:message code="label.prescriptions"/> </a>
    </sec:authorize>


    <form action="/logout" method="post" style="display: inline;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="<spring:message code='label.logout' />" class="logout-button"/>
    </form>
</div>
<div class="footer">
    <p class="server-time"><spring:message code="label.currentServerTime"/>: ${serverTime}</p>
</div>
</body>
</html>
