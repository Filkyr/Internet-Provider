<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <title><fmt:message key="login.title" bundle="${loc}"/></title>
    <meta charset="utf-8"/>
    <%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->--%>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/header.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
    <script src="js/jquery-3.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/login.js"></script>
</head>
<body>
<header>
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#"><img src="images/logo.png" alt="logo"></a>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">Тарифы</a>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a id="enter-button" href="#">Войти</a></li>
                    <!--data-toggle="modal" data-target="#content"-->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Language <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><span class="glyphicon glyphicon-ok"></span> English</a></li>
                            <li><a href="#">Русский</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<section id="carousel-example-generic" class="carousel slide carousel-fade" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    </ol>

    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="images/slide02.jpg" alt="First caption"/>
            <div class="carousel-caption">
                <h2>Простой в использовании</h2>
                <p>Выбрать нужный вам тариф и начать им пользоваться очень просто</p>
            </div>
        </div>
        <div class="item">
            <img src="images/slide03.jpg" alt="Second caption">
            <div class="carousel-caption">
                <h2>Быстрый и лёгкий</h2>
                <p>Скорость и цены на наши тарифы самые лучшие</p>
            </div>
        </div>
    </div>

    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    </a>
</section>

<div class="cfa">
    <div class="container">
        <div class="col-md-8">
            <span class="cfa-text">Some text here</span>
        </div>
        <div class="col-md-4">
            <a class="btn btn-lg cfa-button" href="#">Come now</a>
        </div>
    </div>
</div>

<footer>
    <div class="container">
        <div class="copyright col-md-6 right">
            <p><a href="#">Pinterest company.</a> Konsky incorporation &#169 All rights Reserved</p>
        </div>
    </div>
</footer>

<section id="content">
    <form action="/" method="post" id="login-form" class="form-to-change">
        <!--Welcome!-->
        <h1>Добро пожаловать!</h1>
        <!--<p class="error-message">Неверно введённые данные</p>-->
        <div>
            <span class="fa-envelope"></span>
            <input type="text" name="login" placeholder="Username" required/>
        </div>
        <div>
            <span class="fa-lock"></span>
            <input type="password" name="password" placeholder="Password" required/>
        </div>
        <div>
            <input type="hidden" name="cmd" value="login">
            <input type="submit" value="Войти"/> <!--Log In-->
            <!--Not registered? <a href="#">Create an account</a>-->
            <p class="message"><a href="#">Зарегистрироваться</a></p>
        </div>
    </form>
    <form action="/" method="post" id="register-form" class="form-to-change">
        <!--Registration-->
        <h1>Регистрация</h1>
        <div>
            <span class="fa-envelope"></span>
            <input type="text" name="login" placeholder="Your login" required/>
        </div>
        <div>
            <span class="fa-lock"></span>
            <input type="password" name="password" placeholder="Your password" required/>
        </div>
        <div>
            <input type="hidden" name="cmd" value="signup">
            <input type="submit" value="Зарегистрироваться"/> <!--Sign Up-->
            <!--Already registered? <a href="#">Log in</a>-->
            <p class="message">Есть аккаунт? <a href="#">Войти</a></p>
        </div>
    </form>
</section>

<div id="overlay"></div>
</body>
<html>