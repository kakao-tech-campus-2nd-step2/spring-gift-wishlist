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


$(document).ready(function() {
    var token = localStorage.getItem("token");
    if (token) {
        updateUI(true);
    } else {
        updateUI(false);
    }
});

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
        success: function (data, status, jqXHR) {
            const token = jqXHR.getResponseHeader('Token');
            localStorage.setItem("token", token);
            console.log(token);
            alert(data);
            location.href = '/';
            updateUI(true);
        },
        error: function (request, status, error) {
            alert(request.responseText);
        }
    });
}

function logout() {
    localStorage.removeItem('token');
    updateUI(false);
}

function updateUI(isLoggedIn) {
    if (isLoggedIn) {
        $('#login-button').hide(); 
        $('#logout-button').show();
    } else {
        $('#login-button').show();
        $('#logout-button').hide(); 
    }
}