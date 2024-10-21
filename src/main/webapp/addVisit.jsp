<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.addVisit"/></title>
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
        .form-group input, .form-group select {
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
    <a href="/visits"><spring:message code="label.showDetails"/></a>
</div>
<div class="container">
    <h2>Add Visit</h2>
    <c:if test="${not empty errorMessage}">
        <div class="alert">
                ${errorMessage}
        </div>
    </c:if>
    <form:form method="post" action="/visits/add" modelAttribute="visit">
        <div class="form-group">
            <form:label path="doctor.id"><spring:message code="label.doctor"/></form:label>
            <form:select path="doctor.id">
                <form:options items="${doctors}" itemValue="id" itemLabel="lastName"/>
            </form:select>
        </div>
        <div class="form-group">
            <form:label path="patient.id"><spring:message code="label.patient"/></form:label>
            <input type="text" value="${currentUser.firstName} ${currentUser.lastName}" readonly="true"/>
            <form:hidden path="patient.id" value="${currentUser.id}"/>
        </div>
        <div class="form-group">
            <form:label path="visitType.id"><spring:message code="label.visitType"/></form:label>
            <form:select path="visitType.id">
                <c:forEach var="visitType" items="${visitTypes}">
                    <form:option value="${visitType.id}">${visitType.serviceName} - ${visitType.servicePrice}</form:option>
                </c:forEach>
            </form:select>
        </div>
        <div class="form-group">
            <form:label path="date"><spring:message code="label.date"/></form:label>
            <form:input path="date" type="date"/>
        </div>
        <div class="form-group">
            <form:label path="time"><spring:message code="label.time"/></form:label>
            <form:select path="time">
                <c:forEach var="hour" begin="8" end="18">
                    <form:option value="${hour}:00">${hour}:00</form:option>
                    <form:option value="${hour}:15">${hour}:15</form:option>
                    <form:option value="${hour}:30">${hour}:30</form:option>
                    <form:option value="${hour}:45">${hour}:45</form:option>
                </c:forEach>
            </form:select>
        </div>
        <div class="form-group">
            <form:label path="isPayed"><spring:message code="label.isPayed"/></form:label>
            <form:checkbox path="isPayed"/>
        </div>
        <div>
            <input type="submit" value="Add Visit" class="btn"/>
        </div>
    </form:form>
</div>
</body>
</html>
