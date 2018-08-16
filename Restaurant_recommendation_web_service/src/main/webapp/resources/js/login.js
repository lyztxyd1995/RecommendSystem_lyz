$(function(){
    $('#login_submit').click(function(){
        var userId = $('#userId').val();
        var password = $('#password').val();
        var verifyCodeActual = $('#j_captcha').val();
        if(!verifyCodeActual){
            alert('please enter the verification code');
            return;
        }
        $.ajax({
            url: '/restaurant_recommendation/handleLogin?userId=' + userId + '&password='+password + '&verifyCodeActual=' + verifyCodeActual,
            type:'GET',
            contentType:false,
            processData:false,
            cache:false,
            success:function(data){
                if(data.success){
                    window.location.href = '/restaurant_recommendation/index';
                } else{
                    alert(data.errMsg);
                }
                $('#captcha_img').click();
            }
        })
    })
})