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
    if($(input).val().trim() == ''){
        return false;
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
    validateInput();
}


