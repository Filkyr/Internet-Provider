var focusFunc = function (input, place) {
    if (input.attr('aria-checked') !== 'true' && input.val() !== '') {
        checkInput(input, place);
    }
};

var keyFunc = function (input, place) {
    if (input.attr('aria-checked') === 'true') {
        checkInput(input, place);
    }
};

function checkInput(input, place) {
    input.attr('aria-checked', 'true');
    var $val = input.val();
    /*if($val === undefined){
        return true;
    }*/

    if($val === '' && input.attr('aria-required') === 'true'){
        resetError(input, place);
        showError(input, 'data-val-required', place);
        input.focus();
        return false;
    } else if($val.length === 1) {
        resetError(input, place);
    }

    var regEx = new RegExp(input.attr('data-val-pattern'));
    if (!$val.match(regEx)) {
        showError(input, 'data-val-error', place);
        return false;
    } else {
        resetError(input, place);
        return true;
    }
}

function showError(input, attribute, place) {
    if (input.attr('aria-invalid') === 'false') {
        input.css('border', '1px solid #e63740');
        if(place === 1){
            input.after('<span class="error-message">' + input.attr(attribute) + '</span>');
        } else {
            input.parent().last().after('<span class="error-message">' + input.attr(attribute) + '</span>');
        }
        input.attr('aria-invalid', 'true');
    }
}

function resetError(input, place) {
    input.css('border', '');
    if(place === 1) {
        input.siblings('.error-message').remove();
    } else {
        input.parent().siblings('.error-message').remove();
    }
    input.attr('aria-invalid', 'false');
}

var validation = function(){
    var answer = true;
    for(var i = 0; i < arguments.length; i++){
        if(!checkInput(arguments[i], 1)){
            answer = false;
        }
    }
    return answer;
};