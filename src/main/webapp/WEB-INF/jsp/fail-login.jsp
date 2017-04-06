<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Ошибка авторизации</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/media/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/media/css/style-header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/media/css/style-form.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/media/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/media/js/checking.js"></script>
    <script src="${pageContext.request.contextPath}/media/js/internet.form.js"></script>
    <style>
        body {
            background: url(../../media/images/background.png);
        }
        #form-container {
            display: block;
            opacity: 1;
            position: relative;
            top: initial;
            left: initial;
            transform: initial;
            z-index: initial;
            margin: 100px auto 0;
        }
    </style>
</head>
<body>
<section id="form-container">
    <form action="${pageContext.request.contextPath}/Controller" method="post" id="login-form"
          class="form-to-change clearfix" onsubmit="return validateLoginForm();">
        <h1 class="rus-width">Добро пожаловать</h1>
        <div>
            <c:choose>
                <c:when test="${busyEmailError != null}">
                    <p class="error-message error">Введённый вами email занят.</p>
                </c:when>
                <c:when test="${signUpDataError != null}">
                    <p class="error-message error">Ошибка регистрации. Проверьте введённые данные.</p>
                </c:when>
                <c:otherwise>
                    <p class="error-message error">Ошибка авторизации. Попробуйте снова.</p>
                </c:otherwise>
            </c:choose>
            <div>
                <span class="glyphicon glyphicon-envelope fa-envelope"></span>
                <input type="text" name="email" title="Ваш email адрес"
                       placeholder="mail@example.com" maxlength="100"
                       data-val-error="Некорректный email" value="${email}"
                       data-val-required="Пожалуйста, введите ваш email адрес"
                       aria-invalid="false" aria-checked="false" aria-required="true" data-val-pattern="${emailPattern}"/>
            </div>
        </div>
        <div>
            <div>
                <span class="glyphicon glyphicon-lock fa-lock"></span>
                <input type="password" name="password" title="Ваш пароль"
                       placeholder="Пароль" maxlength="50" data-val-error="Пароль должен содержать хотя бы 4 символа"
                       data-val-required="Пожалуйста, введите пароль"
                       aria-invalid="false" aria-checked="false" aria-required="true" data-val-pattern="${passwordPattern}"/>
            </div>
        </div>
        <div class="clearfix">
            <input type="hidden" name="cmd" value="log-in">
            <input type="submit" value="Войти"/>
            <p class="message">Нет аккаунта? <a href="javascript:void(0);">Зарегистрироваться</a></p>
        </div>
        <div class="clearfix">
            <a href="${pageContext.request.contextPath}/Controller" class="undo wide">Отмена</a>
        </div>
    </form>
    <form action="${pageContext.request.contextPath}/Controller" method="post" id="register-form"
          class="form-to-change clearfix" onsubmit="return validateRegisterForm();">
        <h1>Регистрация</h1>
        <div>
            <div>
                <span class="glyphicon glyphicon-envelope fa-envelope"></span>
                <input type="text" name="email" title="Ваш email адрес"
                       placeholder="mail@example.com" maxlength="100"
                       data-val-error="Некорректный email"
                       data-val-used-error="Это email уже используется, попробуйте другой"
                       data-val-required="Пожалуйста, введите ваш email адрес"
                       aria-invalid="false" aria-checked="false" aria-required="true" data-val-pattern="${emailPattern}"/>
            </div>
        </div>
        <div>
            <div>
                <span class="glyphicon glyphicon-lock fa-lock"></span>
                <input type="password" name="password" title="Ваш пароль"
                       placeholder="Пароль" maxlength="50" data-val-error="Пароль должен содержать хотя бы 4 символа"
                       data-val-required="Пожалуйста, введите пароль"
                       aria-invalid="false" aria-checked="false" aria-required="true" data-val-pattern="${passwordPattern}"/>
            </div>
        </div>
        <div class="clearfix">
            <input type="hidden" name="cmd" value="sign-up">
            <input type="submit" value="Зарегистрироваться"/>
            <p class="message">Зарегистрированы? <a href="javascript:void(0);">Войдите</a></p>
        </div>
        <div class="clearfix">
            <a href="${pageContext.request.contextPath}/Controller" class="undo wide">Отмена</a>
        </div>
    </form>
</section>
</body>
</html>