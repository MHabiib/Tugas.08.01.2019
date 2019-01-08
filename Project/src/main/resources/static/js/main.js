function getAllUsers(){
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/users",
        headers: {
            "Content-Type": "application/json", "Accept": "application/json"
        },
        dataType:"json",
        success: function (data) {
            console.log("yes. data: " + data);
            if (data) {
                var len = data.length;
                if (len > 0) {
                    $("#userTable").empty();
                    for (var i = 0; i < len; i++) {
                        if (data[i]) {
                            var user = data[i];
                            $("#userTable").append(
                                $("<tr>").attr("id", user.id).append(
                                    $("<td>").text(user.username),
                                    $("<td>").text(data[i].role),
                                    $("<td>").append($("<button>").text("Edit").addClass("btn btn-warning").click(editUser.bind(null,user.id)),$("<button>").text("Delete").addClass("btn btn-danger").click(deleteUser.bind(null,user.id)))
                                )
                            );
                        }
                    }
                }
            }
        },
        error: function (error) {
            console.log('errorCode: ' + error.status + ' . Message: ' + error.responseText);
        }
    });
}
function editUser(userId){
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/users/"+userId,
        headers: {
            "Content-Type": "application/json", "Accept": "application/json"
        },
        dataType:"json",
        success: function (data) {
            console.log("yes. data: " + data);
            if (data) {
                $("#username").val(data.username);
                $("#roleUser").val(data.role);
               $("#password").val(data.password);
            }
        },
        error: function (error) {
            console.log('errorCode: ' + error.status + ' . Message: ' + error.responseText);
        }
    });

    $("#updateUser").unbind().click(function(e) {
        e.preventDefault();
        var user = {
            username: $("#username").val(),
            password: $("#password").val(),
            role: $("#roleUser").val()
        };
        var data = JSON.stringify(user);

        $.ajax({
                type: 'PUT',
                url: "http://localhost:8080/users/" + userId,
                headers: {
                    "Content-Type": "application/json", "Accept": "application/json"
                },
                data: data,
                dataType: "json",
                success: function (data) {
                    if (data != null) alert("Successed:\nuser Updated!");
                    else alert("Error:\nfailed to update User!");
                },
                error: function (error) {
                    console.log('errorCode: ' + error.status + ' . Message: ' + error.responseText);
                    alert(error.status + "\nSome error occurred, failed to update user");
                }
            }
        )
    });

}


function deleteUser(userId){
    $.ajax({
        type: 'DELETE',
        url: "http://localhost:8080/users/"+userId,
        headers: {
            "Content-Type": "application/json", "Accept": "application/json"
        },
        dataType:"json",
        success: function (data) {
            if(data==false) alert("Error:\nFailed to delete user!");
            else alert("Successed:\nUser deleted!");
        },
        error: function (error) {
            console.log('errorCode: ' + error.status + ' . Message: ' + error.responseText);
            alert(error.status+"\nSome error occurred, failed to delete user");
        }
    });
}
getAllUsers();

$("#saveUser").unbind().click( function(e) {
        e.preventDefault();
        var user = {
        username: $("#username").val(),
        password: $("#password").val(),
        role: $("#roleUser").val()
    };
    var data = JSON.stringify(user);
    $.ajax({
        type: 'POST',
        url: "http://localhost:8080/users",
        headers: {
            "Content-Type": "application/json", "Accept": "application/json"
        },
        data: data,
        dataType: "json",
        success: function (data) {
            if(data){ alert("Successed\nUser created!");}
            else {alert("Failed to create user!")}
        },
        error: function (error) {
            console.log('errorCode: ' + error.status + ' . Message: ' + error.responseText);
        }
    });
});
