<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
        .btn {
            display: block;
            width: 60%;
            margin: 10px auto;
            padding: 10px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
        }
        .btn:hover {
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
    </style>
</head>
<body>
<div class="header">
    <a href="?lang=pl">pl</a> | <a href="?lang=en">en</a> | <a href="?lang=de">de</a>
</div>
<div class="container">
    <h2><spring:message code="label.welcomeToClinic"/></h2>
    <a href="/login" class="btn"><spring:message code="label.login"/></a>
    <a href="register" class="btn"><spring:message code="label.registerNewPatient"/></a>
</div>
</body>
</html>
