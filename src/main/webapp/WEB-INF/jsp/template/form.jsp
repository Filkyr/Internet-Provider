<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section id="form-container">
    <form action="${pageContext.request.contextPath}/Controller" method="post" id="login-form"
          class="form-to-change clearfix" onsubmit="return validateLoginForm();">
        <h1 class="rus-width">Добро пожаловать</h1>
        <div>
            <div>
                <span class="glyphicon glyphicon-envelope fa-envelope"></span>
                <input type="text" name="email" title="Ваш email адрес"
                       placeholder="mail@example.com" maxlength="100"
                       data-val-error="Некорректный email"
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
        <div>
            <input type="hidden" name="cmd" value="log-in">
            <input type="submit" value="Войти"/>
            <p class="message">Нет аккаунта? <a href="javascript:void(0);">Зарегистрироваться</a></p>
        </div>
    </form>
    <form action="#" method="post" id="register-form"
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
        <div>
            <input type="hidden" name="cmd" value="sign-up">
            <input type="submit" value="Зарегистрироваться"/>
            <p class="message">Зарегистрированы? <a href="javascript:void(0);">Войдите</a></p>
        </div>
    </form>
</section>
<div id="overlay"></div>