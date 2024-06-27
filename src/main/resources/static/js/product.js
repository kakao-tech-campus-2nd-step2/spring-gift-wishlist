$(document).ready(function () {
  function loadProducts() {
    $.get("/products", function (data) {
      $('#productTableBody').empty();
      data.forEach(function (product) {
        $('#productTableBody').append(`
          <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.imageUrl}</td>
            <td>
              <button class="btn btn-warning btn-edit" data-id="${product.id}">Edit</button>
              <button class="btn btn-danger btn-delete" data-id="${product.id}">Delete</button>
            </td>
          </tr>
        `);
      });
    });
  }

  $('#addNewProduct').click(function () {
    $('#productForm')[0].reset();
    $('#productId').val('');
    $('#productModal').modal('show');
  });

  $('#saveProduct').click(function () {
    const product = {
      name: $('#name').val(),
      price: $('#price').val(),
      imageUrl: $('#imageUrl').val()
    };
    const id = $('#productId').val();
    if (id) {
      $.ajax({
        url: `/products/${id}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(product),
        success: function () {
          $('#productModal').modal('hide');
          loadProducts();
        }
      });
    } else {
      $.ajax({
        url: '/products',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(product),
        success: function () {
          $('#productModal').modal('hide');
          loadProducts();
        }
      });
    }
  });

  $(document).on('click', '.btn-edit', function () {
    const id = $(this).data('id');
    $.get(`/products/${id}`, function (data) {
      $('#productId').val(data.id);
      $('#name').val(data.name);
      $('#price').val(data.price);
      $('#imageUrl').val(data.imageUrl);
      $('#productModal').modal('show');
    });
  });

  $(document).on('click', '.btn-delete', function () {
    const id = $(this).data('id');
    $.ajax({
      url: `/products/${id}`,
      type: 'DELETE',
      success: function () {
        loadProducts();
      }
    });
  });

  loadProducts();
});
