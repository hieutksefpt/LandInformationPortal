
function showLogErrorEmpty(){
    showError("Vui lòng điền tất cả các trường trước khi gửi phản hồi!");
    //alert("Vui lòng điền tất cả các trường trước khi gửi phản hồi ! ");
}

function showLogError(){
    showError("Vui lòng điền nội dung phù hợp trước khi gửi phản hồi!");
    //alert("Vui lòng điền nội dung phù hợp trước khi gửi phản hồi ! ");
}

function showLogSuccessSendFeedback(){
    showSuccess("Phản hồi của bạn đã được gửi tới quản trị viên!");
    //alert("Phản hồi của bạn đã được gửi tới quản trị viên !");
}

(function ($) {
    "use strict";

    
    /*==================================================================
    [ Validate ]*/
    var name = $('.validate-input input[name="name"]');
    var email = $('.validate-input input[name="email"]');
    var subject = $('.validate-input input[name="subject"]');
    var message = $('.validate-input textarea[name="message"]');


    $('.validate-form').on('submit',function(){
        var check = true;

        if($(name).val().trim() == ''){
            showValidate(name);
            check=false;
        }

        if($(subject).val().trim() == ''){
            showValidate(subject);
            check=false;
        }


        if($(email).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
            showValidate(email);
            check=false;
        }

        if($(message).val().trim() == ''){
            showValidate(message);
            check=false;
        }

        return check;
    });


    $('.validate-form .input1').each(function(){
        $(this).focus(function(){
           hideValidate(this);
       });
    });

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    

})(jQuery);