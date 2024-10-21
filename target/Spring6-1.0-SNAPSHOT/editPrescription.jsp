<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Prescription</title>
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
        .form-group input, .form-group select, .form-group textarea {
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
    <a href="/prescriptions"><spring:message code="label.showDetails"/></a>
</div>
<div class="container">
    <h2>Edit Prescription</h2>
    <c:if test="${not empty errorMessage}">
        <div class="alert">
                ${errorMessage}
        </div>
    </c:if>
    <form:form method="post" action="/prescriptions/edit" modelAttribute="prescription">
        <form:hidden path="id"/>
        <div class="form-group">
            <form:label path="doctor.id"><spring:message code="label.doctor"/></form:label>
            <form:select path="doctor.id">
                <form:options items="${doctors}" itemValue="id" itemLabel="lastName"/>
            </form:select>
        </div>
        <div class="form-group">
            <form:label path="patient.id"><spring:message code="label.patient"/></form:label>
            <form:select path="patient.id">
                <form:options items="${patients}" itemValue="id" itemLabel="lastName"/>
            </form:select>
        </div>
        <div class="form-group">
            <form:label path="medicines">Medicines</form:label>
            <form:textarea path="medicines" rows="3"/>
        </div>
        <div>
            <input type="submit" value="Edit Prescription" class="btn"/>
        </div>
    </form:form>
</div>
</body>
</html>
