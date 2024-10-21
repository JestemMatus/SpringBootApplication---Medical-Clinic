<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Role</title>
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
      margin: 0 5px;
      text-decoration: none;
      color: white;
      background-color: #007bff;
      padding: 5px 10px;
      border-radius: 3px;
      border: 1px solid #007bff;
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
    .form-group {
      margin-bottom: 15px;
    }
    .form-group label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }
    .form-group input {
      width: 100%;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 5px;
      box-sizing: border-box;
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
  </style>
</head>
<body>
<div class="header">
  <a href="/appUserRole"><spring:message code="label.Back"/></a>
  <a href="/home"><spring:message code="label.homePage"/></a>
</div>
<div class="container">
  <h2><spring:message code="label.editRole"/></h2>
  <form:form method="post" action="/editAppUserRole" modelAttribute="appUserRole">
    <form:hidden path="id"/>
    <div class="form-group">
      <form:label path="role"><spring:message code="label.roleName"/></form:label>
      <form:input path="role" class="form-control"/>
    </div>
    <div>
      <input type="submit" value="Update Role" class="btn"/>
    </div>
  </form:form>
</div>
</body>
</html>
