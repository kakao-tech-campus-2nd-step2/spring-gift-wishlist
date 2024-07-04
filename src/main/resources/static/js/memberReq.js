function register() {

    event.preventDefault();

    var formData = {
        'email' : $('#email').val(),
        'password' : $('#password').val()
    };

    $.ajax({
        url: '/api/members/register',
        method: 'POST',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        processData: false,
        success: function (response) {
            alert(response);
            location.href = '/';
        },
        error: function (request, status, error) {
            alert(request.responseText);            
        }
    });
}

function login() {

    event.preventDefault();

    var formData = {
        'email' : $('#email').val(),
        'password' : $('#password').val()
    };

    $.ajax({
        url: '/api/members/login',
        method: 'POST',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        processData: false,
        success: function (response) {
            alert(response);
            location.href = '/';
        },
        error: function (request, status, error) {
            alert(request.responseText);
        }
    });
}