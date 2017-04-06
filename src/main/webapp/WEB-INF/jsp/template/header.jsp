<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/Controller">
                    <img src="${pageContext.request.contextPath}/media/images/logo.png" alt="Логотип" title="На главную страницу">
                </a>
            </div>

            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#nav-bar-collapse" aria-expanded="false">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <div class="collapse navbar-collapse" id="nav-bar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/Controller?cmd=tariffs">Тарифы</a>
                    </li>
                    <li>
                        <c:if test="${sessionScope.role == 'user'}">
                            <a href="${pageContext.request.contextPath}/Controller?cmd=my-tariffs">Мои тарифы</a>
                        </c:if>
                    </li>
                </ul>

                <c:choose>
                    <c:when test="${sessionScope.role == 'admin' || sessionScope.role == 'user'}">
                        <div class="navbar-form navbar-right">
                            <form id="logout" action="${pageContext.request.contextPath}/Controller" method="post">
                                <input type="hidden" name="cmd" value="log-out">
                            </form>
                            <button type="submit" class="btn btn-custom" onclick="document.getElementById('logout').submit()">
                                <span class="glyphicon glyphicon-log-out"></span> Выйти</button>
                        </div>
                        <p class="navbar-text navbar-right"><span class="glyphicon glyphicon-user"></span>
                                ${sessionScope.initials}</p>
                    </c:when>
                    <c:otherwise>
                        <div class="navbar-form navbar-right">
                            <button type="submit" class="btn btn-custom enter"><span class="glyphicon glyphicon-log-in"></span>
                                Войти</button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</header>