<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <title>Ошибка!</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/media/css/style-error-page.css" rel="stylesheet">
</head>
<body>
<div class="wrap">
    <div class="logo">
        <h1>500</h1>
        <p>Сервер временно недоступен, попробуйте зайти позже</p>
        <div class="sub">
            <p><a href="${pageContext.request.contextPath}/Controller">На главную страницу</a></p>
        </div>
    </div>
</div>
</body>
</html>