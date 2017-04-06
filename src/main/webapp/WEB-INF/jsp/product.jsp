<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Тариный план</title>
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
        <script src="${pageContext.request.contextPath}/media/js/internet.product.js"></script>
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
                <c:if test="${removeUsageError}">
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
                        <p><strong>Ошибка!</strong> При отключении тарифа произошла ошибка. Попробуйте снова.</p>
                    </div>
                </c:if>
                <c:if test="${insertUsageError}">
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
                        <p><strong>Ошибка!</strong> При подключении тарифа произошла ошибка. Попробуйте снова.</p>
                    </div>
                </c:if>

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
                        <div class="clearfix">
                            <h5 class="right"><span class="glyphicon glyphicon-signal"></span>
                                <b>Категория:</b> ${tariff.category.name}</h5>
                        </div>
                        <h5><b>Стоимость (BYN):</b></h5>
                        <ul class="list-group">
                            <li class="list-group-item list-group-item-warning">
                                <c:choose>
                                    <c:when test="${sessionScope.role == 'admin'}">
                                        <div class="clearfix">
                                            <div class="left"><span class="glyphicon glyphicon-usd"></span>
                                                <span class="content">${tariff.monthlyCost}</span></div>
                                            <div class="right changeable">
                                        <span class="glyphicon glyphicon-pencil"
                                              data-toggle="tooltip" data-placement="top" title="Редактировать"></span></div>
                                        </div>
                                        <div class="input-group hide">
                                            <input type="text" class="form-control" name="sum" title="Новая стоимость"
                                                   placeholder="Новая стоимость тарифа" maxlength="6"
                                                   data-val-error="Денежный формат xxx.xx или xxx"
                                                   data-val-required="Пожалуйста, введите новую стоимость"
                                                   data-server-error="Произошла ошибка. Попробуйте снова позднее"
                                                   aria-invalid="false" aria-checked="true" aria-required="true"
                                                   data-val-pattern="${sumPattern}" data-id="${tariff.id}"/>
                                            <div class="input-group-btn">
                                                <button class="btn btn-danger cancel" type="button">
                                                    <span class="glyphicon glyphicon-remove"></span></button>
                                                <button id="cost-btn" class="btn btn-success" type="button">
                                                    <span class="glyphicon glyphicon-ok"></span></button>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="glyphicon glyphicon-usd"></span>
                                        <span class="content">${tariff.monthlyCost}</span>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                        <h5><b>Описание:</b></h5>
                        <ul class="list-group">
                            <li class="list-group-item list-group-item-warning">
                                <c:choose>
                                    <c:when test="${sessionScope.role == 'admin'}">
                                        <div class="clearfix">
                                            <div class="left custom-width">
                                                <span class="glyphicon glyphicon-tasks"></span>
                                                <span class="content">${tariff.description}</span>
                                            </div>
                                            <div class="right changeable">
                                        <span class="glyphicon glyphicon-pencil"
                                              data-toggle="tooltip" data-placement="top" title="Редактировать"></span></div>
                                        </div>
                                        <div class="input-group hide">
                                            <input type="text" class="form-control" name="sum" title="Новое описание"
                                                   placeholder="Новое описание тарифа" maxlength="500"
                                                   data-val-error="Описание содержит недопустимые символы"
                                                   data-val-required="Пожалуйста, напишите описание для тарифа"
                                                   data-server-error="Произошла ошибка. Попробуйте снова позднее"
                                                   aria-invalid="false" aria-checked="true" aria-required="true"
                                                   data-val-pattern="${descriptionPattern}" data-id="${tariff.id}"/>
                                            <div class="input-group-btn">
                                                <button class="btn btn-danger cancel" type="button">
                                                    <span class="glyphicon glyphicon-remove"></span></button>
                                                <button id="description-btn" class="btn btn-success" type="button">
                                                    <span class="glyphicon glyphicon-ok"></span></button>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="glyphicon glyphicon-tasks"></span>
                                        <span class="content">${tariff.description}</span>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                        <h5><b>Особенности:</b></h5>
                        <ul class="list-group">
                            <c:forEach var="feature" items="${tariff.features}">
                                <li class="list-group-item">
                                    <c:choose>
                                        <c:when test="${sessionScope.role == 'admin'}">
                                            <div class="clearfix">
                                                <div class="left custom-width">
                                                    <span class="glyphicon glyphicon-asterisk"></span>
                                                    <span class="content">${feature.feature}</span>
                                                </div>
                                                <div class="right changeable">
                                        <span class="glyphicon glyphicon-pencil"
                                              data-toggle="tooltip" data-placement="top" title="Редактировать"></span></div>
                                            </div>
                                            <div class="input-group hide">
                                                <input type="text" class="form-control" name="feature" title="Новая особенность"
                                                       placeholder="Особенность данного тарифа" maxlength="150"
                                                       data-val-error="Текст содержит недопустимые символы"
                                                       data-val-required="Пожалуйста, укажите особенность для тарифа"
                                                       data-server-error="Произошла ошибка. Попробуйте снова позднее"
                                                       aria-invalid="false" aria-checked="true" aria-required="true"
                                                       data-val-pattern="${featurePattern}" data-id="${feature.id}"/>
                                                <div class="input-group-btn">
                                                    <button class="btn btn-danger cancel" type="button">
                                                        <span class="glyphicon glyphicon-remove"></span></button>
                                                    <button class="btn btn-success feature-btn" type="button">
                                                        <span class="glyphicon glyphicon-ok"></span></button>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-asterisk"></span>
                                            <span class="content">${feature.feature}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="clearfix">
                            <c:choose>
                                <c:when test="${tariff.used}">
                                    <form id="remove" action="${pageContext.request.contextPath}/Controller" method="post">
                                        <input type="hidden" name="tariffId" value="${tariff.id}"/>
                                        <input type="hidden" name="cmd" value="remove-usage"/>
                                    </form>
                                    <button class="btn btn-delete right" onclick="document.getElementById('remove').submit()">
                                        <span class="glyphicon glyphicon-minus-sign"></span> Отключиться</button>
                                </c:when>
                                <c:when test="${sessionScope.role == 'user'}">
                                    <form id="insert" action="${pageContext.request.contextPath}/Controller" method="post">
                                        <input type="hidden" name="tariffId" value="${tariff.id}"/>
                                        <input type="hidden" name="cmd" value="insert-usage"/>
                                    </form>
                                    <button class="btn btn-buy right" onclick="document.getElementById('insert').submit()">
                                        <span class="glyphicon glyphicon-plus-sign"></span>
                                        Подключить</button>
                                </c:when>
                                <c:when test="${sessionScope.role == 'admin'}"></c:when>
                                <c:otherwise>
                                    <p class="right error">Чтобы приобрести что-то необходимо авторизоваться</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
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