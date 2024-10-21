<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.activationResult"/></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .message {
            font-size: 1.2em;
            color: #666;
        }
    </style>
</head>
<body>
<div class="container">
    <h2><spring:message code="label.activationResult"/></h2>
    <div class="message">
        ${message}
    </div>
</div>
</body>
</html>
