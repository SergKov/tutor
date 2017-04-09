/**
 * Created by koval on 17.01.2017.
 */
$(document).ready(function() {

    $("#confirm_pwd").keyup(function () {
        var password = $("#pwd").val();
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

    $("#start_btn").click(function (e) {
        var confirmMsg = $('.js-start-btn').data('start-btn');
        if (!confirm(confirmMsg)) {
            e.preventDefault();
        }
    });

    $("#finish_btn").click(function (e) {
        var confirmMsg = $('.js-finish-btn').data('finish-btn');
        if (!confirm(confirmMsg)) {
            e.preventDefault();
        }
    });

    $(".remove_btn").click(function (e) {
        var confirmMsg = $('.js-remove-btn').data('remove-btn');
        if (!confirm(confirmMsg)) {
            e.preventDefault();
        }
    });

    $("#plusAnswer").click(function () {
        var $answer = $('.answer').first().clone();
        $answer.find(':input').each(function () {
            $(this).val('');
        });

        $answer.addClass('answer-padding');
        var $answers = $('.answers');
        if ($answers.find('.answer').length < 10) {  // TODO fix
            $answers.append($answer)
        }
    });

    $("#minusAnswer").click(function () {
        var $answers = $('.answer');
        if ($answers.length > 1) {
            $answers.last().remove();
        }
    });

    $("#test").bind("cut copy paste", function (e) {
        e.preventDefault();
    });

    $(".js-show-elements").change(function () {
        this.form.submit();
    })
});