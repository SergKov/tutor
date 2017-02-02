/**
 * Created by koval on 17.01.2017.
 */
$(document).ready(function() {

    $("#confirm_psw").keyup(function () {
        var password = $("#psw").val();
        var $errorMsg = $("#errorMsg");
        if (password === $(this).val() || password === '') {
            $errorMsg.hide();
        } else {
            var errorMsg = $('.js-sign_up').data('msg-repeat');
            $errorMsg.html(errorMsg);
            $errorMsg.show();
        }
    });

    $("#back_btn").click(function (e) {
        e.preventDefault();
        parent.history.back();
    });

    $(".start_btn").click(function (e) {
        if (!confirm('Are you ready?')) {
            e.preventDefault();
        }
    });

    $(".remove_btn").click(function (e) {
        if (!confirm('Are you sure?')) {
            e.preventDefault();
        }
    });

    $("#plusAnswer").click(function () {
        var $answer = $('.answer').first().clone();
        $answer.find(':input').each(function () {
            $(this).val('');
        });

        $answer.addClass('answer-padding');
        $('.answers').append($answer);
    });

    $("#minusAnswer").click(function () {
        var $answers = $('.answer');
        if ($answers.length > 1) {
            $answers.last().remove();
        }
    });
});