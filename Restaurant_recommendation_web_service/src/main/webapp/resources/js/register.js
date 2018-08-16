$(function(){
    $('#register_submit').click(function(){
        var userId = $('#userId').val();
        var password = $('#password').val();
        var password1 = $('#password1').val();
        var verifyCodeActual = $('#j_captcha').val();
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        if(!verifyCodeActual){
            alert('please enter the verification code');
            return;
        }
        $.ajax({
            url:'/restaurant_recommendation/handleRegister?userId=' + userId + '&password='+password + '&verifyCodeActual=' + verifyCodeActual
            + '&firstName=' + firstName + '&lastName=' + lastName + '&password1=' + password1,
            type:'POST',
            contentType:false,
            processData:false,
            cache:false,
            success:function(data){
                if(data.success){
                    alert("Congratulations, successful registeration");
                    window.location.href = '/restaurant_recommendation/login';
                } else{
                    alert(data.errMsg);
                }
                $('#captcha_img').click();
            }
        })
    })
})