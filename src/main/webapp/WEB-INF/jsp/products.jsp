<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pgn" uri="/WEB-INF/tld/pagination" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Тарифы</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/media/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/media/css/style-header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/media/css/style-product.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/media/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/media/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/media/js/internet.products.js"></script>
    <c:if test="${sessionScope.role == 'admin'}">
        <script src="${pageContext.request.contextPath}/media/js/checking.js"></script>
        <script src="${pageContext.request.contextPath}/media/js/internet.add.js"></script>
    </c:if>
    <c:if test="${sessionScope.role != 'admin' && sessionScope.role != 'user'}">
        <link href="${pageContext.request.contextPath}/media/css/style-form.css" rel="stylesheet"/>
        <script src="${pageContext.request.contextPath}/media/js/internet.form.js"></script>
        <script src="${pageContext.request.contextPath}/media/js/checking.js"></script>
    </c:if>
</head>
<body>

<c:import url="template/header.jsp"/>

<section>
    <div class="container">
        <div class="row">
            <nav class="col-md-3" id="fix-element">
                <ul class="nav nav-pills nav-stacked">
                    <c:forEach var="category" items="${categories}" varStatus="loop">
                        <li class="clearfix">
                            <a href="${pageContext.request.contextPath}/Controller?cmd=tariffs&category=${category.id}"
                                class="left">${category.name}</a>
                            <c:if test="${sessionScope.role == 'user'}">
                                <div>
                                    <div class="switch">
                                        <input type="checkbox" id="switch-${loop.index}" class="switch-check"
                                            <c:if test="${category.subscribe}">checked</c:if>
                                               data-id="${category.id}" data-name="${category.name}"/>
                                        <label for="switch-${loop.index}" class="switch-label">
                                            <span class="switch-slider switch-slider-on"></span>
                                            <span class="switch-slider switch-slider-off"></span>
                                        </label>
                                    </div>
                                </div>
                            </c:if>
                        </li>
                    </c:forEach>
                </ul>
            </nav>

            <div class="col-md-9 main">
                <c:if test="${addTariffError}">
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
                        <p><strong>Ошибка!</strong> Тариф не был добавлен. Введённые вами данные некорректны.</p>
                    </div>
                </c:if>

                <c:if test="${sessionScope.role == 'admin'}">
                    <div class="clearfix bottom-gap">
                        <button type="button" id="add-button" class="btn btn-info right" data-toggle="collapse"
                                data-target="#form-block">Добавить тариф</button>
                    </div>

                    <div class="row collapse bottom-gap" id="form-block" aria-expanded="false">
                        <form action="${pageContext.request.contextPath}/Controller" method="post"
                              id="new-tariff" onsubmit="return checkNewTariff();">
                            <div class="my-row clearfix">
                                <div class="col-md-6">
                                    <label for="name">Название тарифа:</label>
                                    <span class="glyphicon glyphicon-font"></span>
                                    <input type="text" name="name" title="Название нового тарифа"
                                            class="form-control" id="name" maxlength="100"
                                            placeholder="Название нового тарифного плана"
                                            data-val-error="Название тарифа содержит недопустимые символы"
                                            data-val-required="Пожалуйста, введите название тарифа"
                                            aria-invalid="false" aria-checked="false" aria-required="true"
                                            data-val-pattern="${namePattern}"/>
                                </div>
                                <div class="col-md-6">
                                    <label for="cost">Месячная стоимость:</label>
                                    <span class="glyphicon glyphicon-usd"></span>
                                    <input type="text" name="monthlyCost" title="Месячная стоимость тарифа"
                                            class="form-control" id="cost" maxlength="6"
                                            placeholder="Стоимось тарифа"
                                            data-val-error="Неверный формат. Допустимо xxx.xx или ххх"
                                            data-val-required="Пожалуйста, введите стоимость"
                                            aria-invalid="false" aria-checked="false" aria-required="true"
                                            data-val-pattern="${sumPattern}"/>
                                </div>
                            </div>
                            <div class="my-row clearfix">
                                <div class="col-md-12">
                                    <label for="description">Описание тарифа:</label>
                                    <span class="glyphicon glyphicon-text-size"></span>
                                    <input type="text" name="description" title="Описание тарифа"
                                            class="form-control" id="description" maxlength="500"
                                            placeholder="Описание нового тарифа"
                                            data-val-error="Описание содержит недопустимые символы"
                                            data-val-required="Пожалуйста, введите описание тарифа"
                                            aria-invalid="false" aria-checked="false" aria-required="true"
                                            data-val-pattern="${descriptionPattern}"/>
                                </div>
                            </div>
                            <div class="my-row clearfix">
                                <div class="col-md-12">
                                    <label for="feature1">Особенность:</label>
                                    <span class="glyphicon glyphicon-asterisk"></span>
                                    <input type="text" name="feature1" title="Особенность тарифа"
                                            class="form-control" id="feature1" maxlength="150"
                                            placeholder="Особенность тарифа"
                                            data-val-error="Текст содержит недопустимые символы"
                                            data-val-required="Пожалуйста, введите особенность тарифа"
                                            aria-invalid="false" aria-checked="false" aria-required="true"
                                            data-val-pattern="${featurePattern}"/>
                                </div>
                            </div>
                            <div class="my-row clearfix">
                                <div class="col-md-12">
                                    <label for="feature2">Особенность:</label>
                                    <span class="glyphicon glyphicon-asterisk"></span>
                                    <input type="text" name="feature2" title="Особенность тарифа"
                                           class="form-control" id="feature2" maxlength="150"
                                           placeholder="Особенность тарифа"
                                           data-val-error="Текст содержит недопустимые символы"
                                           data-val-required="Пожалуйста, введите особенность тарифа"
                                           aria-invalid="false" aria-checked="false" aria-required="true"
                                           data-val-pattern="${featurePattern}"/>
                                </div>
                            </div>
                            <div class="my-row clearfix">
                                <div class="col-md-12">
                                    <label for="feature3">Особенность:</label>
                                    <span class="glyphicon glyphicon-asterisk"></span>
                                    <input type="text" name="feature3" title="Особенность тарифа"
                                           class="form-control" id="feature3" maxlength="150"
                                           placeholder="Особенность тарифа"
                                           data-val-error="Текст содержит недопустимые символы"
                                           data-val-required="Пожалуйста, введите особенность тарифа"
                                           aria-invalid="false" aria-checked="false" aria-required="true"
                                           data-val-pattern="${featurePattern}"/>
                                </div>
                            </div>
                            <div class="my-row clearfix">
                                <div class="col-md-12">
                                    <label for="category">Категория:</label>
                                    <span class="glyphicon glyphicon-list-alt"></span>
                                    <select form="new-tariff" name="category" id="category" class="form-control"
                                            title="Категория нового тарифа" required>
                                        <c:forEach var="category" items="${categories}">
                                            <option value="${category.id}">${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <input type="hidden" name="cmd" value="add-tariff">
                                <input type="submit" value="Создать" class="btn">
                            </div>
                        </form>
                    </div>
                </c:if>

                <c:if test="${empty tariffs}">
                    <h1>По данной категории не найдено ни одного тарифа :(</h1>
                </c:if>

                <c:forEach var="tariff" items="${tariffs}">
                    <c:choose>
                        <c:when test="${tariff.used}">
                            <div class="panel panel-success">
                                <div class="panel-heading clearfix">
                                    <h4 class="left">Тарифный план: ${tariff.name}</h4>
                                    <div class="right">
                                        <span class="glyphicon glyphicon-ok" style="font-size: 24px;" title="Используется"></span>
                                    </div>
                                </div>
                        </c:when>
                        <c:otherwise>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h4>Тарифный план: ${tariff.name}</h4>
                                    </div>
                        </c:otherwise>
                    </c:choose>
                        <div class="panel-body">
                            <div class="col-md-9">
                                <h5><span class="glyphicon glyphicon-signal"></span>
                                    <b>Категория:</b> ${tariff.category.name}</h5>
                                <h5><b>Стоимость:</b></h5>
                                <p><span class="glyphicon glyphicon-usd"></span> ${tariff.monthlyCost}</p>
                                <h5><b>Описание:</b></h5>
                                <p><span class="glyphicon glyphicon-tasks"></span> ${tariff.description}</p>
                            </div>
                            <div class="col-md-3">
                                <a href="${pageContext.request.contextPath}/Controller?cmd=tariff&tariffId=${tariff.id}"
                                   class="btn btn-show right">Смотреть
                                    <span class="glyphicon glyphicon glyphicon-menu-right"></span></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <div class="clearfix">
                    <pgn:pagination command="cmd=tariffs" totalPages="${totalPages}" currentPage="${currentPage}"/>
                </div>
            </div>
        </div>
    </div>
</section>

<c:import url="template/footer.jsp"/>

<c:if test="${sessionScope.role != 'admin' && sessionScope.role != 'user'}">
    <c:import url="template/form.jsp"/>
</c:if>
</body>
</html>