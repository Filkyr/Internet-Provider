$(document).ready(function () {
    var $block = $('#form-block'),
        $button = $('#add-button'),
        $inputs = $('#new-tariff input[type="text"]');

    $inputs.on('focusout', function(){focusFunc($(this), 1)});
    $inputs.on('input', function(){keyFunc($(this), 1)});

    $button.on('click', function() {
        if($block.attr('aria-expanded') === 'false'){
            $inputs.each(function() {
                $(this).val('');
                resetError($(this), 1);
            });
            setTimeout(changeButtonText.bind(null, 'Отменить добавление'), 370);
        } else {
            setTimeout(changeButtonText.bind(null, 'Добавить тариф'), 370);
        }
    });

    function changeButtonText(text){
        $button.text(text);
    }
});

function checkNewTariff(){
    return validation.apply(null, [$('#new-tariff input[name="feature3"]'),
        $('#new-tariff input[name="feature2"]'),
        $('#new-tariff input[name="feature1"]'),
        $('#new-tariff input[name="description"]'),
        $('#new-tariff input[name="monthlyCost"]'),
        $('#new-tariff input[name="name"]')]);
}