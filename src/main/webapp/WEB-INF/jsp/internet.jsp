<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Интернет-услуги</title>
    <meta charset="utf-8"/>
    <meta name="description" content="This application about internet-provider. You can register in this system and start
            using our features."/>
    <meta name="keywords" content="internet-provider tariff traffic connect"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/media/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/media/css/style-header.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/media/css/style-internet.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/media/css/style-form.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/media/css/style-animate.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/media/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/media/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/media/js/viewportchecker.min.js"></script>
    <c:if test="${sessionScope.role != 'admin' && sessionScope.role != 'user'}">
        <script src="${pageContext.request.contextPath}/media/js/internet.form.js"></script>
        <script src="${pageContext.request.contextPath}/media/js/checking.js"></script>
    </c:if>
</head>
<body>
<c:import url="template/header.jsp"/>

<section id="carousel-example-generic" class="carousel slide carousel-fade" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="${pageContext.request.contextPath}/media/images/slide02.jpg" alt="Первая картинка"/>
            <div class="carousel-caption">
                <h2>Lorem ipsum dolor sit amet.</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed mi libero. Aenean id pellentesque ante, non volutpat dolor. Phasellus ornare elementum nisi, vel dictum.</p>
            </div>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/media/images/slide03.jpg" alt="Вторая картинка">
            <div class="carousel-caption">
                <h2>Consectetur adipiscing elit.</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dignissim sem eu varius sollicitudin. Aenean in laoreet neque, a auctor risus. Praesent faucibus mi sem, vel dictum ligula ornare in. Nam tortor sapien, molestie sed.</p>
            </div>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/media/images/slide01.jpg" alt="Третья картинка">
            <div class="carousel-caption">
                <h2>Pellentesque cursus felis ornare.</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque cursus felis ornare magna tincidunt sodales. Nulla a elementum metus, sed varius massa. Praesent maximus interdum erat tincidunt convallis. Aenean luctus arcu eget.</p>
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

<div class="container">
    <section class="features">
        <div class="spacing-border clearfix">
            <div class="col-md-8 content">
                <h2>Proin ex risus, tristique ac pharetra vel, tristique at eros.</h2>
                <div>
                    <p>Proin libero nisl, tempus sed sodales sit amet, consectetur id orci. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam eleifend elementum efficitur. Phasellus molestie, turpis ac placerat placerat, sem dolor feugiat nibh, non aliquet nisl ex sed arcu. Nam malesuada placerat faucibus. Vestibulum ornare neque consequat placerat faucibus. Cras ultrices, purus in eleifend luctus, ex orci rhoncus ante, eget ultricies ipsum ante nec nisi. Morbi vehicula eget ante in mattis. Fusce neque dolor, lacinia at congue suscipit, tincidunt vitae tortor. Nam efficitur a risus et sagittis. Nam sodales turpis eu dolor congue venenatis in id arcu. Nam tempor mattis nisl, quis imperdiet purus dignissim in. Cras placerat interdum velit, in vestibulum metus porttitor dictum. Etiam posuere facilisis massa, eu elementum est auctor nec. Sed elit ex, placerat eu odio sit amet, molestie fringilla nunc.</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="image-container">
                    <img src="${pageContext.request.contextPath}/media/images/Rigorous-standards.png"/>
                </div>
            </div>
        </div>

        <div class="spacing-border clearfix">
            <div class="col-md-4">
                <div class="image-container">
                    <img src="${pageContext.request.contextPath}/media/images/Accuracy.png"/>
                </div>
            </div>
            <div class="col-md-8 content">
                <h2>Quisque tincidunt mi sed justo ultricies egestas.</h2>
                <div>
                    <p>Curabitur lacus velit, eleifend ultrices urna non, semper dapibus elit. Etiam erat augue, convallis at sapien sit amet, laoreet commodo odio. Vestibulum eu leo ut lacus malesuada ultricies ac vel ex. Integer pulvinar aliquam arcu, vel consequat lectus pharetra eu. Phasellus massa dui, vestibulum ut eros laoreet, tincidunt finibus turpis. Integer ut augue lacinia, mattis velit eget, cursus diam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et posuere leo. Proin vel massa nec felis feugiat finibus. Phasellus venenatis arcu purus, ut maximus ipsum sagittis nec. Vestibulum efficitur augue a lacus sagittis, ut pulvinar sem vehicula. Nullam consectetur libero ac ipsum ultricies, vel semper velit fringilla. Sed a tempus eros.</p>
                </div>
            </div>
        </div>

        <div class="spacing-border clearfix">
            <div class="col-md-8 content">
                <h2>Aliquam ac posuere enim. Maecenas sed nibh euismod, tincidunt neque nec, egestas lorem.</h2>
                <div>
                    <p>Sed placerat enim vel risus finibus, non ultrices eros finibus. Ut blandit efficitur lectus ac tempus. In ut lacus vitae risus dictum vehicula a id sem. Quisque commodo tortor at scelerisque luctus. Curabitur quis malesuada mi, a gravida leo. Nunc semper in orci eget vestibulum. Sed lobortis auctor metus, et maximus nunc imperdiet eget. In dictum sapien et eleifend consequat. Aliquam venenatis vestibulum iaculis. Donec sit amet vestibulum tortor, nec hendrerit nisi. Aliquam eget maximus enim. Vivamus vel feugiat nibh, eget volutpat nunc. Aenean at augue sed lectus tincidunt volutpat nec sit amet nisi. Suspendisse eget volutpat nunc.</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="image-container">
                    <img src="${pageContext.request.contextPath}/media/images/Mobile-testing.png"/>
                </div>
            </div>
        </div>

        <div class="spacing-border clearfix">
            <div class="col-md-4">
                <div class="image-container">
                    <img src="${pageContext.request.contextPath}/media/images/Optimized-test.png"/>
                </div>
            </div>
            <div class="col-md-8 content">
                <h2>Sed consectetur lobortis sapien eget blandit.</h2>
                <div>
                    <p>Sed ut ullamcorper purus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed finibus ante sed tellus laoreet lacinia. Suspendisse tincidunt dui sit amet facilisis volutpat. Praesent pharetra magna est, non pharetra nunc malesuada at. Proin vehicula ligula et purus ullamcorper, sed suscipit eros ultricies. Phasellus rhoncus nisl leo, eu rhoncus erat ultricies non. Aenean quis lobortis ipsum. Pellentesque mollis dui fringilla odio egestas, et dapibus nisl blandit. Quisque gravida nunc et accumsan egestas. Mauris ornare elementum libero quis dictum. Aenean at libero commodo, facilisis erat et, egestas odio. Sed fermentum consequat massa. Ut laoreet blandit rutrum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis sed facilisis mauris.</p>
                </div>
            </div>
        </div>
    </section>
</div>

<c:import url="template/footer.jsp"/>

<c:if test="${sessionScope.role != 'admin' && sessionScope.role != 'user'}">
    <c:import url="template/form.jsp"/>
</c:if>

<script>
    $(document).ready(function () {
        $('.spacing-border').addClass("hide_me").viewportChecker({classToAdd: 'show_me animated fadeInDown', offset: 100});
    });
</script>

</body>
</html>