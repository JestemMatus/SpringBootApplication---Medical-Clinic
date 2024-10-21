<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.addAppUser"/></title>
    <script src="https://www.google.com/recaptcha/api.js"></script>
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
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input,
        .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .has-error {
            border-color: red !important;
        }
        .form-group .error-message {
            color: red;
            font-size: 12px;
        }
        .form-group button,
        .form-group .action-buttons a {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
            text-decoration: none;
            display: inline-block;
            text-align: center;
            margin-top: 10px;
        }
        .form-group button:hover,
        .form-group .action-buttons a:hover {
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
        .footer {
            text-align: center;
            margin-top: 20px;
        }
        .footer a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .footer a:hover {
            background-color: #0056b3;
        }
        .action-buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .action-buttons a {
            flex: 1;
            margin: 0 5px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
        }
        .action-buttons a:hover {
            background-color: #0056b3;
        }
        .success-message {
            color: green;
            font-size: 14px;
            margin-bottom: 20px;
            text-align: center;
        }
        .error-message {
            color: red;
            font-size: 12px;
        }
    </style>
</head>
<body>
<div class="header">
    <a href="?lang=pl">pl</a> | <a href="?lang=en">en</a> | <a href="?lang=de">de</a>
</div>
<div class="container">
    <h2><spring:message code="label.addAppUser"/></h2>

    <c:if test="${not empty successMessage}">
        <div id="successMessage" class="success-message">${successMessage}</div>
    </c:if>

    <form:form action="addAppUser" method="post" modelAttribute="appUser">
        <div class="form-group">
            <form:label path="login"><spring:message code="label.login"/></form:label>
            <form:input path="login" cssClass="${not empty errors.fieldError('login') ? 'has-error' : ''}"/>
            <form:errors path="login" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="password"><spring:message code="label.password"/></form:label>
            <form:input path="password" type="password" cssClass="${not empty errors.fieldError('password') ? 'has-error' : ''}"/>
            <form:errors path="password" cssClass="error-message" element="div" htmlEscape="false"/>
        </div>
        <div class="form-group">
            <form:label path="firstName"><spring:message code="label.firstName"/></form:label>
            <form:input path="firstName" cssClass="${not empty errors.fieldError('firstName') ? 'has-error' : ''}"/>
            <form:errors path="firstName" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="lastName"><spring:message code="label.lastName"/></form:label>
            <form:input path="lastName" cssClass="${not empty errors.fieldError('lastName') ? 'has-error' : ''}"/>
            <form:errors path="lastName" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="email"><spring:message code="label.email"/></form:label>
            <form:input path="email" cssClass="${not empty errors.fieldError('email') ? 'has-error' : ''}"/>
            <form:errors path="email" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="telephoneNumber"><spring:message code="label.telephoneNumber"/></form:label>
            <form:input path="telephoneNumber" cssClass="${not empty errors.fieldError('telephoneNumber') ? 'has-error' : ''}"/>
            <form:errors path="telephoneNumber" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="pesel"><spring:message code="label.pesel"/></form:label>
            <form:input path="pesel" cssClass="${not empty errors.fieldError('pesel') ? 'has-error' : ''}"/>
            <form:errors path="pesel" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="city"><spring:message code="label.city"/></form:label>
            <form:input path="city" cssClass="${not empty errors.fieldError('city') ? 'has-error' : ''}"/>
            <form:errors path="city" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="street"><spring:message code="label.street"/></form:label>
            <form:input path="street" cssClass="${not empty errors.fieldError('street') ? 'has-error' : ''}"/>
            <form:errors path="street" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="buildingNumber"><spring:message code="label.buildingNumber"/></form:label>
            <form:input path="buildingNumber" cssClass="${not empty errors.fieldError('buildingNumber') ? 'has-error' : ''}"/>
            <form:errors path="buildingNumber" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="apartmentNumber"><spring:message code="label.apartmentNumber"/></form:label>
            <form:input path="apartmentNumber"/>
        </div>
        <div class="form-group">
            <form:label path="defaultClinic"><spring:message code="label.defaultClinic"/></form:label>
            <form:select path="defaultClinic" cssClass="${not empty errors.fieldError('defaultClinic') ? 'has-error' : ''}">
                <form:option value=""><spring:message code="label.choseDefaultClinic"/></form:option>
                <form:option value="Klinika dla dorosłych">Klinika dla dorosłych</form:option>
                <form:option value="Klinika dla dzieci">Klinika dla dorosłych</form:option>
                <form:option value="Klinika dla osób starszych">Klinika dla dorosłych</form:option>
            </form:select>
            <form:errors path="defaultClinic" cssClass="error-message"/>
        </div>
        <div class="g-recaptcha" data-sitekey="6Le8bnwlAAAAAOt9ABkjYaYipNStknIIRLqHf5Gi"></div>
        <c:if test="${not empty recaptchaError}">
            <div class="error-message">${recaptchaError}</div>
        </c:if>
        <div class="form-group">
            <button type="submit"><spring:message code="label.addAppUser"/></button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="form-group action-buttons">
            <a href="/" class="return"><spring:message code="label.returnToHello"/></a>
            <a href="/login" class="login"><spring:message code="label.doYouHaveAccount"/></a>
        </div>
    </form:form>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var successMessage = document.getElementById("successMessage");
        if (successMessage) {
            var inputs = document.querySelectorAll("input, select");
            inputs.forEach(function(input) {
                input.addEventListener("input", function() {
                    successMessage.style.display = "none";
                });
            });
        }
    });
</script>
</body>
</html>
