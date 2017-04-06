<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Main</title>
</head>
<body>
<h2>Hello World!</h2>
<p>${user}</p>
<form action="/Controller" method="get">
    <input type="hidden" name="cmd" value="alltariffs">
    <input type="submit" value="Все тарифы">
</form>
<form action="/Controller" method="get">
    <input type="hidden" name="cmd" value="logout">
    <input type="submit" value="Выйти">
</form>
</body>
</html>
