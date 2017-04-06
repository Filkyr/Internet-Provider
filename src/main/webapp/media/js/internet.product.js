$(document).ready(function () {
    var $url = $('.navbar-brand').attr('href');

    $('[data-toggle="tooltip"]').tooltip();

    $('.changeable').on('click', function() {
        var $current = $(this).parent();
        $current.parent().find('input').val($current.find('.content').text());
        $current.siblings('.hide').removeClass("hide");
        $current.addClass('hide');
    });
    $('.cancel').on('click', function() {
        var $li = $(this).parent().parent().parent();
        resetError($li.find('input'), 2);
        $li.find('.hide').removeClass('hide');
        $li.find('.input-group').addClass('hide');
    });

    $('input[type="text"]').on('input', function(){keyFunc($(this), 2)});

    $('#cost-btn').on('click', function(){
        var $input = $(this).parent().siblings('input');
        if(checkInput($input)){
            var $li = $(this).parent().parent().parent();
            var text = $input.val();
            $.ajax({
                type: 'POST',
                url: $url,
                data: {cmd:'update-sum', sum: text, tariffId: $input.attr('data-id')},
                success: function (data) {
                    if(data.error === 'false'){
                        $li.find('.content').text(text);
                        resetError($li.find('input'), 2);
                        $li.find('.hide').removeClass('hide');
                        $li.find('.input-group').addClass('hide');
                    } else {
                        showError($input, 'data-server-error', 2);
                    }
                }
            });
        }
    });

    $('#description-btn').on('click', function(){
        var $input = $(this).parent().siblings('input');
        if(checkInput($input)){
            var $li = $(this).parent().parent().parent();
            var text = $input.val();
            $.ajax({
                type: 'POST',
                url: $url,
                data: {cmd:'update-description', description: text, tariffId: $input.attr('data-id')},
                success: function (data) {
                    if(data.error === 'false'){
                        $li.find('.content').text(text);
                        resetError($li.find('input'), 2);
                        $li.find('.hide').removeClass('hide');
                        $li.find('.input-group').addClass('hide');
                    } else {
                        showError($input, 'data-server-error', 2);
                    }
                }
            });
        }
    });

    $('.feature-btn').on('click', function(){
        var $input = $(this).parent().siblings('input');
        if(checkInput($input)){
            var $li = $(this).parent().parent().parent();
            var text = $input.val();
            $.ajax({
                type: 'POST',
                url: $url,
                data: {cmd:'update-feature', feature: text, featureId: $input.attr('data-id')},
                success: function (data) {
                    if(data.error === 'false'){
                        $li.find('.content').text(text);
                        resetError($li.find('input'), 2);
                        $li.find('.hide').removeClass('hide');
                        $li.find('.input-group').addClass('hide');
                    } else {
                        showError($input, 'data-server-error', 2);
                    }
                }
            });
        }
    });
});