$(document).ready(function () {
    var $inputs = $('input[type="text"]');

    $inputs.on('focusout', function(){focusFunc($(this), 1);});
    $inputs.on('input', function(){keyFunc($(this), 1);});
});

function validateRegistrationForm(){
    return validation.apply(null, [$('input[name="mobilePhone"]'),
        $('input[name="lastName"]'),
        $('input[name="name"]'),
        $('input[name="surname"]')]);
}