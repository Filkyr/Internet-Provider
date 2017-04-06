$(document).ready(function () {
    var $overlay = $('#overlay'),
        $content = $('#form-container'),
        $button = $('#register-form').find('input[type="submit"]'),
        $timer = null,
        $inputs = $('input[type="text"]'),
        $formToChange = $('.form-to-change'),
        $loginForm = $('#login-form'),
        $passwords = $('input[name="password"]'),
        $loginEmail = $loginForm.find('input[name="email"]'),
        $url = $loginForm.attr('action'),
        $registerEmail = $('#register-form input[name="email"]');

    $('.message a').on('click', function () {
        $formToChange.animate({height: 'toggle', opacity: 'toggle'}, 'slow');
        $inputs.each(function() {
            $(this).val('');
            resetError($(this), 1);
        });
        $passwords.each(function() {
            $(this).val('');
            resetError($(this), 1);
        });
        $button.prop('disabled', false);
    });

    $('.enter').on('click', function(){
        $inputs.each(function() {
            $(this).val('');
            resetError($(this), 1);
        });
        $passwords.each(function() {
            $(this).val('');
            resetError($(this), 1);
        });
        $button.prop('disabled', false);
        if($loginForm.css('display') === 'none'){
            $formToChange.animate({height: 'toggle', opacity: 'toggle'}, 10);
        }
        $overlay.fadeIn(400, function(){
            $content.css('display', 'block').animate({opacity: 1, top: '50%'}, 200);
        });
        $loginEmail.focus();
    });

    $overlay.on('click', function(){
        $content.animate({opacity: 0, top: '45%'}, 200, function(){
            $(this).css('display', 'none');
            $overlay.fadeOut(400);
        });
    });

    $passwords.on('focusout', function(){focusFunc($(this), 1);});
    $passwords.on('input', function(){keyFunc($(this), 1);});
    $loginEmail.on('focusout', function(){focusFunc($(this), 1);});
    $loginEmail.on('input', function(){keyFunc($(this), 1);});

    $registerEmail.on('focusout', function() {
        var $content = $(this);
        if($content.attr('aria-checked') !== 'true' && $content.val() !== ''){
            $content.attr('aria-checked', 'true');
            if(checkInput($content, 1)){
                checkEmailUsage($content);
            }
        }
    });

    $registerEmail.on('input',function() {
        var $content = $(this);
        if($content.attr('aria-checked') === 'true') {
            clearTimeout($timer);
            resetError($content, 1);
            if(checkInput($content, 1)){
                $timer = setTimeout(checkEmailUsage.bind(null, $content), 200);
            }
        }
    });

    function checkEmailUsage(input){
        $.ajax({
            type: 'POST',
            url: $url,
            data: {cmd:'check-email', email: input.val()},
            success: function (data) {
                if(data === 'false'){
                    showError(input, 'data-val-used-error', 1);
                    $button.prop('disabled', true);
                } else {
                    resetError(input, 1);
                    $button.prop('disabled', false);
                }
            }
        });
    }
});

function validateLoginForm(){
    return validation.apply(null,
                            [$('#login-form input[name="password"]'), $('#login-form input[name="email"]')]);
}

function validateRegisterForm(){
    return validation.apply(null,
                            [$('#register-form input[name="password"]'), $('#register-form input[name="email"]')]);
}
