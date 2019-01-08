function validateInput(){
    var check = true;
    var input = $('.input100');
    console.log(input);
    for(var i=0; i<input.length; i++) {
        if(validate(input[i]) == false){
            showValidate(input[i]);
            check=false;
        }
    }
    return check;
}
$('.input100').each(function(){
    $(this).focus(function(){
        hideValidate(this);
    });
});

function validate (input) {
    if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
        if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
            return false;
        }
    }
    else {
        if($(input).val().trim() == ''){
            return false;
        }
    }
}

function showValidate(input) {
    var thisAlert = $(input).parent();

    $(thisAlert).addClass('alert-validate');
}

function hideValidate(input) {
    var thisAlert = $(input).parent();

    $(thisAlert).removeClass('alert-validate');
}



$("#login").click(function () {
    if(validateInput()){
        var login = {
            email: $("#email").val(),
            password : $("#password").val()
        };
        var loginJSON=JSON.stringify(login);
        console.log(loginJSON);
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/login',
            data: loginJSON,
            headers: {
                "Content-Type": "application/json", "Accept": "application/json"
            },
            dataType: "json", //to parse string into JSON object,
            success: function (data) {
                console.log("YES!")
            },
            error: function (error) {
                console.log("cookie not saved");
                console.log('errorCode: ' + error.status + ' . Message: ' + error.responseText);
                alert("email/password does not exist");
            }
        });
    }
});

