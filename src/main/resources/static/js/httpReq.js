function addOne() {
     event.preventDefault();

    var formData = {
        'id' : $('#id').val(),
        'name' : $('#name').val(),
        'price' : $('#price').val(),
        'imageUrl' : $('#imageUrl').val()
    };

    $.ajax({
        url: '/api/products/add',
        method: 'POST',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        processData: false,

        success: function (response) {
            alert('성공적으로 추가되었습니다!');
            location.href = response;
        },
        error: function (request, status, error) {
            alert(request.responseText);            
        }
    });
}

function deleteOne(id) {
   $.ajax({
       method: 'DELETE',
       url: `/api/products/${id}`,
       success: function(response) {
            alert('삭제되었습니다!'); 
            location.href = response;
        },
        error: function (request, status, error) {
            alert(request.responseText);            
        }
   });
}

function editOne() {
    event.preventDefault();

    var formData = {
        'id' : $('#id').val(),
        'name' : $('#name').val(),
        'price' : $('#price').val(),
        'imageUrl' : $('#imageUrl').val()
    };

    $.ajax({
        url: '/api/products/edit',
        method: 'PUT',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        processData: false,
        success: function (response) {
            alert('수정되었습니다!');
            location.href = response;
        },
        error: function (request, status, error) {
            alert(request.responseText);            
        }
    });
}