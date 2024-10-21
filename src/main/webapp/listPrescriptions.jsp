<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List Prescriptions</title>
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
    <a href="/home">Home</a>
    <c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
        <a href="/prescriptions/add">Add Prescription</a>
    </c:if>
</div>
<div class="container">
    <h2>List Prescriptions</h2>
    <table>
        <thead>
        <tr>
            <th>Doctor</th>
            <th>Patient</th>
            <th>Medicines</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="prescription" items="${prescriptions}">
            <tr>
                <td>${prescription.doctor.lastName}</td>
                <td>${prescription.patient.lastName}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty prescription.medicines}">
                            <c:forEach var="medicine" items="${prescription.medicines}">
                                ${medicine}<br/>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            No medicines prescribed
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="actions">
                    <c:if test="${prescription.doctor.id == currentUser.id or pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <a href="/prescriptions/edit/${prescription.id}">Edit</a>
                        <a href="/prescriptions/delete/${prescription.id}" onclick="return confirm('Are you sure you want to delete this prescription?');">Delete</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
