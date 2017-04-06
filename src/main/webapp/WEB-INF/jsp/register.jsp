<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Регистрация</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/media/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/media/css/style-form.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/media/css/style-registration.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/media/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/media/js/checking.js"></script>
    <script src="${pageContext.request.contextPath}/media/js/internet.registration.js"></script>
</head>
<body>
<section id="form-container">
    <form action="${pageContext.request.contextPath}/Controller" method="post" id="registration-form"
          class="clearfix" onsubmit="return validateRegistrationForm()">
        <h1>Регистрация</h1>
        <div>
            <div>
                <span class="glyphicon glyphicon-user"></span>
                <input type="text" name="surname" placeholder="Фамилия"
                       title="Ваша фамилия" data-val-pattern="${initialPattern}"
                       data-val-error="Фамилия содержит недопустимые символы"
                       data-val-required="Пожалуйста, введите вашу фамилию"
                       aria-invalid="false" aria-checked="false" aria-required="true" maxlength="30"/>
            </div>
        </div>
        <div>
            <div>
                <span class="glyphicon glyphicon-user"></span>
                <input type="text" name="name" placeholder="Имя"
                       title="Ваше имя" data-val-pattern="${initialPattern}"
                       data-val-error="Имя содержит недопустимые символы"
                       data-val-required="Пожалуйста, введите ваше имя"
                       aria-invalid="false" aria-checked="false" aria-required="true" maxlength="30"/>
            </div>
        </div>
        <div>
            <div>
                <span class="glyphicon glyphicon-user"></span>
                <input type="text" name="lastName" placeholder="Отчество"
                       title="Ваше отчество" data-val-pattern="${middleNamePattern}"
                       data-val-error="Отчество содержит недопустимые символы"
                       aria-invalid="false" aria-checked="false" aria-required="false" maxlength="30"/>
            </div>
        </div>
        <div>
            <div>
                <span class="glyphicon glyphicon-phone"></span>
                <input type="text" name="mobilePhone" placeholder="Мобильный телефон"
                       title="Формат +375ххххххххх" data-val-pattern="${mobilePhonePattern}"
                       data-val-error="Неверный формат номера"
                       data-val-required="Пожалуйста, введите ваш мобильный номер"
                       aria-invalid="false" aria-checked="false" aria-required="true" maxlength="13"/>
            </div>
        </div>
        <div>
            <input type="hidden" name="cmd" value="register">
            <input type="submit" value="Зарегистрироваться"/>
            <a href="${pageContext.request.contextPath}/Controller" class="undo">Отмена</a>
        </div>
    </form>
</section>
</body>
</html>