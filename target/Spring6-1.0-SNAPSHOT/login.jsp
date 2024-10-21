<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.login_page"/></title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 400px;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            text-align: center;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .form-signin {
            width: 100%;
            padding: 15px;
            margin: auto;
        }
        .form-signin .checkbox {
            font-weight: 400;
            margin-bottom: 20px;
            text-align: left;
            margin-left: 15px;
        }
        .form-signin .form-control {
            width: calc(100% - 20px);
            margin: 10px auto;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
            box-sizing: border-box;
        }
        .form-signin input:focus {
            border-color: #007bff;
            outline: none;
        }
        .btn-signin {
            font-size: 18px;
            font-weight: bold;
            padding: 10px 20px;
            border: none;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            border-radius: 5px;
            width: calc(100% - 20px);
            margin: 10px auto;
        }
        .btn-signin:hover {
            background-color: #0056b3;
        }
        .btn-secondary {
            font-size: 18px;
            font-weight: bold;
            padding: 10px 20px;
            border: none;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            border-radius: 5px;
            width: calc(50% - 10px);
            margin: 10px 5px;
        }
        .header {
            margin-bottom: 20px;
            text-align: right;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
        }
        .header a {
            margin: 0 5px;
            text-decoration: none;
            color: white;
            padding: 5px 10px;
            border-radius: 3px;
            border: 1px solid white;
        }
        .header a:hover {
            background-color: #0056b3;
        }
        .link {
            margin-top: 20px;
        }
        .link a {
            color: white;
            text-decoration: none;
        }
        .link a:hover {
            text-decoration: none;
        }
        .footer {
            margin-top: 20px;
            text-align: center;
            color: #666;
            font-size: 1.1em;
        }
        .button-group {
            display: flex;
            justify-content: space-between;
            padding: 0 10px;
        }
    </style>
</head>
<body onload='document.loginForm.login.focus();'>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<div class="header">
    <a href="?lang=pl">PL</a> | <a href="?lang=en">EN</a> | <a href="?lang=de">DE</a>
</div>
<div class="container">
    <h2><spring:message code="label.login_page"/></h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="alert alert-info">${msg}</div>
    </c:if>
    <form class="form-signin" name='loginForm' action="<c:url value='/login' />" method='POST'>
        <input type="text" name='login' id="inputEmail" class="form-control" placeholder="Login" required autofocus/>
        <input type="password" name='password' id="inputPassword" class="form-control" placeholder="Password" required/>
        <div id="remember" class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> <spring:message code="label.rememberMe"/>
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><spring:message code="label.LogIn"/></button>
        <div class="button-group">
            <button class="btn btn-lg btn-primary btn-block btn-signin btn-secondary" onclick="window.location.href='/hello'; return false;"><spring:message code="label.back"/></button>
            <button class="btn btn-lg btn-primary btn-block btn-signin btn-secondary" onclick="window.location.href='/register'; return false;"><spring:message code="label.register"/></button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>

</div>
<div class="footer">
    <p class="server-time"><spring:message code="label.currentServerTime"/>: ${serverTime}</p>
</div>
</body>
</html>

