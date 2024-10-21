<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.visits"/></title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #007bff;
            color: white;
            padding: 10px 0;
            text-align: center;
        }
        .header a {
            color: white;
            text-decoration: none;
            padding: 0 15px;
        }
        .header a:hover {
            text-decoration: underline;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            text-align: left;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        td {
            background-color: #f9f9f9;
        }
        tr:hover td {
            background-color: #f1f1f1;
        }
        .actions a {
            margin-right: 10px;
            text-decoration: none;
            color: #007bff;
            transition: color 0.3s ease;
        }
        .actions a:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
<div class="header">
    <a href="/home"><spring:message code="label.homePage"/></a>
    <c:if test="${pageContext.request.isUserInRole('ROLE_USER')}">
        <a href="visits/add"><spring:message code="label.addVisit"/></a>
    </c:if>
</div>
<div class="container">
    <h2>Visits</h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="label.doctor"/></th>
            <th><spring:message code="label.patient"/></th>
            <th><spring:message code="label.date"/></th>
            <th><spring:message code="label.time"/></th>
            <th><spring:message code="label.visitType"/></th>
            <th><spring:message code="label.isPayed"/></th>
            <th><spring:message code="label.actions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="visit" items="${visits}">
            <tr>
                <td>${visit.doctor.lastName}</td>
                <td>${visit.patient.lastName}</td>
                <td><fmt:formatDate value="${visit.date}" pattern="yyyy-MM-dd"/></td>
                <td>${visit.time}</td>
                <td>${visit.visitType.serviceName} - ${visit.visitType.servicePrice}</td>
                <td>
                    <c:choose>
                        <c:when test="${visit.isPayed}">
                            tak
                        </c:when>
                        <c:otherwise>
                            nie
                        </c:otherwise>
                    </c:choose>
                </td>                <td class="actions">
                    <c:if test="${visit.patient.login eq pageContext.request.userPrincipal.name or pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <a href="visits/edit/${visit.id}"><spring:message code="label.edit"/></a>
                        <a href="visits/delete/${visit.id}" onclick="return confirm('Are you sure you want to delete this visit?');"><spring:message code="label.delete"/></a>
                        <a href="visits/pay/${visit.id}"><spring:message code="label.payVisit"/></a>
                        <c:if test="${visit.isPayed}">
                            <a href="/pdf/download/${visit.id}"><spring:message code="label.downloadBill"/></a>
                        </c:if>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
