<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>List Visit Types</title>
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
  <a href="/visit_types/add"><spring:message code="label.addVisitType"/></a>
</div>
<div class="container">
  <h2><spring:message code="label.visitTypes"/></h2>
  <c:if test="${not empty errorMessage}">
    <div class="alert">
        ${errorMessage}
    </div>
  </c:if>
  <table>
    <thead>
    <tr>
      <th><spring:message code="label.serviceName"/></th>
      <th><spring:message code="label.servicePrice"/></th>
      <th><spring:message code="label.actions"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="visitType" items="${visitTypes}">
      <tr>
        <td>${visitType.serviceName}</td>
        <td>${visitType.servicePrice}</td>
        <td class="actions">
          <a href="/visit_types/edit/${visitType.id}"><spring:message code="label.edit"/></a>
          <a href="/visit_types/delete/${visitType.id}" onclick="return confirm('Are you sure you want to delete this visit type?');"><spring:message code="label.delete"/></a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
