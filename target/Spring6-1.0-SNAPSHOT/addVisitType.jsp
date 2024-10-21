<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Visit Type</title>
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
    <a href="/visit_types"><spring:message code="label.visitsTypes"/></a>
</div>
<div class="container">
    <h2>Add Visit Type</h2>
    <form:form method="post" action="/visit_types/add" modelAttribute="visitType">
        <div class="form-group">
            <form:label path="serviceName">Name</form:label>
            <form:input path="serviceName"/>
            <form:errors path="serviceName" cssClass="alert"/>
        </div>
        <div class="form-group">
            <form:label path="servicePrice">Price</form:label>
            <form:input path="servicePrice"/>
            <form:errors path="servicePrice" cssClass="alert"/>
        </div>
        <div>
            <input type="submit" value="Add Visit Type" class="btn"/>
        </div>
    </form:form>
</div>
</body>
</html>
