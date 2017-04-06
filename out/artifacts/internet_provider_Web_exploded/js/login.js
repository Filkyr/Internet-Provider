$(document).ready(function () {
    $(".message a").click(function () {
        $(".form-to-change").animate({height: "toggle", opacity: "toggle"}, "slow");
    });

    if($("#login-form h1").text() == "Добро пожаловать!"){
        $("#login-form h1").addClass("rus-width");
    }

    // $("html")[0].lang = "ru";

    var $overlay = $("#overlay");
    var $content = $("#content");
    $("#enter-button").click( function(){
        $overlay.fadeIn(400, function(){
            $content.css('display', 'block').animate({opacity: 1, top: '50%'}, 200);
        });
    });
    $overlay.click( function(){
        $content.animate({opacity: 0, top: '45%'}, 200, function(){
                    $(this).css('display', 'none');
                    $overlay.fadeOut(400);
        });
    });

    //$("#register-form input[type='text']").tooltip({title: "text"});
    /*$("#register-form input[type='text']").focusout(function () {
        $("#register-form input[type='submit']").prop("disabled", true);
    });
    $("#register-form input[type='text']").focusin(function () {
        $("#register-form input[type='submit']").prop("disabled", false);
    });*/

});