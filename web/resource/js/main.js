/**
 * Created by koval on 17.01.2017.
 */
$(document).ready(function() {

    $("#confirm_psw").keyup(function () {
        var password = $("#psw").val();
        var $errorMsg = $("#errorMsg");
        if (password == $(this).val()) {
            $errorMsg.hide();
        } else {
            $errorMsg.html("Passwords do not match!");
            $errorMsg.show();
        }
    });

    $("#back_btn").click(function (e) {
        e.preventDefault();
        parent.history.back();
    });
    
});