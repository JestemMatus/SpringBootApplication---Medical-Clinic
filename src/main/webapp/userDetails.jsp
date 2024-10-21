<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.userDetails"/></title>
    <style>
        body {
            font-family: Arial, sans-serif;
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
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .details {
            margin-bottom: 15px;
        }
        .details label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .details p,
        .details input,
        .details select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #f9f9f9;
        }
        .details input,
        .details select {
            display: none;
        }
        .edit-checkbox {
            margin-bottom: 20px;
        }
        .submit-button {
            display: none;
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            border: none;
            color: white;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
        }
        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: red;
            font-size: 12px;
        }
    </style>
    <script>
        function toggleEditMode(checkbox) {
            var elements = document.querySelectorAll('.details p, .details input, .details select');
            var submitButton = document.querySelector('.submit-button');
            elements.forEach(function(element) {
                if (element.tagName === 'P') {
                    element.style.display = checkbox.checked ? 'none' : 'block';
                } else {
                    element.style.display = checkbox.checked ? 'block' : 'none';
                }
            });
            submitButton.style.display = checkbox.checked ? 'block' : 'none';
        }
    </script>
</head>
<body>
<div class="header">
    <a href="/appUserRole"><spring:message code="label.homePage"/></a>
</div>
<div class="container">
    <h2><spring:message code="label.userDetails"/></h2>
    <form:form method="post" action="/updateAppUser" modelAttribute="appUser">
        <form:hidden path="id"/>
        <div class="edit-checkbox">
            <label>
                <input type="checkbox" onchange="toggleEditMode(this)"> <spring:message code="label.editUser"/>
            </label>
        </div>
        <div class="details">
            <label><spring:message code="label.login"/>:</label>
            <p>${appUser.login}</p>
            <form:input path="login"/>
            <form:errors path="login" cssClass="error-message"/>
        </div>
        <div class="details">
            <label><spring:message code="label.firstName"/>:</label>
            <p>${appUser.firstName}</p>
            <form:input path="firstName"/>
            <form:errors path="firstName" cssClass="error-message"/>
        </div>
        <div class="details">
            <label><spring:message code="label.lastName"/>:</label>
            <p>${appUser.lastName}</p>
            <form:input path="lastName"/>
            <form:errors path="lastName" cssClass="error-message"/>
        </div>
        <div class="details">
            <label><spring:message code="label.email"/>:</label>
            <p>${appUser.email}</p>
            <form:input path="email"/>
            <form:errors path="email" cssClass="error-message"/>
        </div>
        <div class="details">
            <label><spring:message code="label.telephoneNumber"/>:</label>
            <p>${appUser.telephoneNumber}</p>
            <form:input path="telephoneNumber"/>
            <form:errors path="telephoneNumber" cssClass="error-message"/>
        </div>
        <div class="details">
            <label><spring:message code="label.pesel"/>:</label>
            <p>${appUser.pesel}</p>
            <form:input path="pesel"/>
            <form:errors path="pesel" cssClass="error-message"/>
        </div>
        <div class="details">
            <label><spring:message code="label.city"/>:</label>
            <p>${appUser.city}</p>
            <form:input path="city"/>
            <form:errors path="city" cssClass="error-message"/>
        </div>
        <div class="details">
            <label><spring:message code="label.street"/>:</label>
            <p>${appUser.street}</p>
            <form:input path="street"/>
            <form:errors path="street" cssClass="error-message"/>
        </div>
        <div class="details">
            <label><spring:message code="label.buildingNumber"/>:</label>
            <p>${appUser.buildingNumber}</p>
            <form:input path="buildingNumber"/>
            <form:errors path="buildingNumber" cssClass="error-message"/>
        </div>
        <div class="details">
            <label><spring:message code="label.apartmentNumber"/>:</label>
            <p>${appUser.apartmentNumber}</p>
            <form:input path="apartmentNumber"/>
        </div>
        <div class="details">
            <label><spring:message code="label.defaultClinic"/>:</label>
            <p>${appUser.defaultClinic}</p>
            <form:select path="defaultClinic" cssClass="${not empty errors.fieldError('defaultClinic') ? 'has-error' : ''}">
                <form:option value=""><spring:message code="label.choseDefaultClinic"/></form:option>
                <form:option value="Klinika dla dorosłych">Klinika dla dorosłych</form:option>
                <form:option value="Klinika dla dzieci">Klinika dla dorosłych</form:option>
                <form:option value="Klinika dla osób starszych">Klinika dla dorosłych</form:option>
            </form:select>
            <form:errors path="defaultClinic" cssClass="error-message"/>
        </div>
        <button type="submit" class="submit-button"><spring:message code="label.submit"/></button>
    </form:form>
    <a class="back-button" href="/appUserRole"><spring:message code="label.backToList"/></a>
</div>
</body>
</html>
