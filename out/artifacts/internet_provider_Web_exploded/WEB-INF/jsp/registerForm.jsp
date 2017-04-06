<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
</head>
<body>
<p>${message}</p>
<form action="/Controller" method="post">
    Surname: <input type="text" name="surname" required/> <br/>
    Name: <input type="text" name="name" required/> <br/>
    Middle name: <input type="text" name="middlename" required/> <br/>
    Mobile phone: <input type="text" name="mobilephone" required/> <br/>
    City: <input type="text" name="city" required/> <br/>
    Street: <input type="text" name="street" required/> <br/>
    Home number: <input type="text" name="homenumber" required/> <br/>
    Flat number: <input type="text" name="flatnumber" required/> <br/>
    Tariff: <input type="text" name="tariff" required/> <br/>
    <input type="hidden" name="cmd" value="register">
    <input type="submit" value="Зарегистрироваться">
</form>
</body>
</html>
