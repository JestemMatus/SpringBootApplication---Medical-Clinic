<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.manageUser"/></title>
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
        h2, h3 {
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
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .form-group .error {
            color: red;
            margin-top: 5px;
        }
        .btn {
            font-size: 16px;
            font-weight: bold;
            padding: 10px 20px;
            border: none;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
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
        .alert {
            padding: 15px;
            background-color: #f44336;
            color: white;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="header">
    <a href="/home"><spring:message code="label.homePage"/></a>
</div>
<div class="container">
    <h2><spring:message code="label.role"/></h2>
    <c:if test="${not empty errorMessage}">
        <div class="alert">
                ${errorMessage}
        </div>
    </c:if>
    <form:form method="post" action="addAppUserRole" modelAttribute="appUserRole">
        <div class="form-group">
            <form:hidden path="id"/>
        </div>
        <div class="form-group">
            <form:label path="role"><spring:message code="label.addRole"/></form:label>
            <form:input path="role"/>
            <form:errors path="role" cssClass="error"/>
        </div>
        <div>
            <input type="submit" value="<spring:message code="label.addAppUserRole"/>" class="btn"/>
        </div>
    </form:form>

    <h3><spring:message code="label.existingRoles"/></h3>
    <table>
        <thead>
        <tr>
            <th><spring:message code="label.role"/></th>
            <th><spring:message code="label.actions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="role" items="${roles}">
            <tr>
                <td>${role.role}</td>
                <td class="actions">
                    <a href="editAppUserRole/${role.id}"><spring:message code="label.edit"/></a>
                    <a href="deleteAppUserRole/${role.id}"><spring:message code="label.delete"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h3><spring:message code="label.existingUsers"/></h3>
    <table>
        <thead>
        <tr>
            <th><spring:message code="label.login"/></th>
            <th><spring:message code="label.firstName"/></th>
            <th><spring:message code="label.lastName"/></th>
            <th><spring:message code="label.email"/></th>
            <th><spring:message code="label.roles"/></th>
            <th><spring:message code="label.actions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${userRoles[user.id]}</td>
                <td class="actions">
                    <a href="userDetails/${user.id}"><spring:message code="label.showDetails"/></a>
                    <a href="delete/${user.id}"><spring:message code="label.delete"/></a>
                    <a href="assignRole/${user.id}"><spring:message code="label.assignRole"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
