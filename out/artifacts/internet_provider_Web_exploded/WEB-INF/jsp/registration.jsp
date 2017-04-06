<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register form</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="${pageContext.request.contextPath}css/loginstyle.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}js/jquery-3.1.0.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}js/register.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="/Controller" method="post" id="register-form">
            <h1>Registration</h1>
            <p>${message}</p>
            <div>
                <span class="fa-envelope"></span>
                <input type="text" name="login" value="${login}" placeholder="Your login" required/>
            </div>
            <div>
                <span class="fa-lock"></span>
                <input type="password" name="password" placeholder="Your password" required/>
            </div>
            <div>
                <input type="hidden" name="cmd" value="signup">
                <input type="submit" value="Sign Up"/>
                <p class="message">Already registered? <a href="login.jsp">Log in</a></p>
                <%--TODO ссылку выше переделать так, чтобы возвращала на страницу входа--%>
            </div>
        </form>
    </section>
</div>
</body>
<html>