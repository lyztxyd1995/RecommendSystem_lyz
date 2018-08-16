$(function(){
    function getUser(){
        var url = '/restaurant_recommendation/getuser';
        $.ajax({
            url:url,
            type:"get",
            success:function(data){
                if(data.success){
                    currentUser = data.currentUser;
                    $('#userId').val(currentUser.userId);
                    $('#firstName').val(currentUser.firstName);
                    $('#lastName').val(currentUser.lastName);
                } else {
                    alert('Fail to load your accoumt information,please retry to login');
                    window.location.href = '/restaurant_recommendation/login';
                }
            }
        })
    }
    getUser();
    $('#update_submit').click(function(){
        var userId = $('#userId').val();
        var password = $('#password').val();
        var newpassword = $('#newpassword').val();
        var newpassword1 = $('#newpassword1').val();
        var verifyCodeActual = $('#j_captcha').val();
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var url = '/restaurant_recommendation/update?userId=' + userId + '&password=' + password + '&verifyCodeActual=' + verifyCodeActual
        + '&newpassword=' + newpassword + '&newpassword1=' + newpassword1 + '&firstName=' + firstName + '&lastName=' + lastName;
        if(!verifyCodeActual){
            alert('please enter the verification code');
            return;
        }
        if(newpassword !== newpassword1){
            alert('The two passwords you typed do not match');
            return;
        }
        $.ajax({
            url: url,
            type:'GET',
            contentType:false,
            processData:false,
            cache:false,
            success:function(data){
                if(data.success){
                    alert("update successfully");
                    window.location.href = '/restaurant_recommendation/index';
                } else{
                    alert(data.errMsg);
                    $('#captcha_img').click();
                }
            }
        })
    })
})