function addOne() {
     event.preventDefault();

    var formData = new FormData();
    formData.append('id', $('#id').val());
    formData.append('name', $('#name').val());
    formData.append('price', $('#price').val());
    formData.append('imageUrl', $('#imageUrl').val());

    $.ajax({
        url: '/api/products/add',
        method: 'POST',
        data: formData,
        contentType: false,
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

    var formData = new FormData();
    formData.append('id', $('#id').val());
    formData.append('name', $('#name').val());
    formData.append('price', $('#price').val());
    formData.append('imageUrl', $('#imageUrl').val());

    $.ajax({
        url: '/api/products/edit',
        method: 'PUT',
        data: formData,
        contentType: false,
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