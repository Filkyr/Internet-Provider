$(document).ready(function () {
    var $nav = $('ul.nav-pills'),
        $window = $(window),
        $top = $('#fix-element').offset().top,
        $url = $('.navbar-brand').attr('href'),
        $main = $('.main');

    $window.on('scroll', function(){
        var top = $window.scrollTop();
        if ($top >= top) {
            var difference = $top - top + 8;
            $nav.css({top: difference});
        } else {
            $nav.css({top: '8px'});
        }
    });

    $('input[type="checkbox"]').on('click', function(){
        var $current = $(this);
        $.ajax({
            type: 'POST',
            url: $url,
            data: {cmd:'change-subscription', category: $current.attr('data-id'), name: $current.attr('data-name')},
            success: function (data) {
                if(data.error === 'false'){
                    if($current.prop('checked')){
                        $current.prop('checked', true);
                    } else {
                        $current.prop('checked', false);
                    }
                    $('.alert').remove();
                    $main.prepend(data.content);
                }
            }
        });
    });
});