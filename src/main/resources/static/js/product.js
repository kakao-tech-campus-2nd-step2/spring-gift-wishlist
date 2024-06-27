$(document).ready(function () {

  async function loadProducts() {
    try {
      const data = await $.get("/products");
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
    } catch (error) {
      console.error('Error loading products:', error);
    }
  }

  async function saveProduct() {
    const product = {
      name: $('#name').val(),
      price: $('#price').val(),
      imageUrl: $('#imageUrl').val()
    };
    const id = $('#productId').val();
    try {
      if (id) {
        await $.ajax({
          url: `/products/${id}`,
          type: 'PUT',
          contentType: 'application/json',
          data: JSON.stringify(product)
        });
      } else {
        await $.ajax({
          url: '/products',
          type: 'POST',
          contentType: 'application/json',
          data: JSON.stringify(product)
        });
      }
      $('#productModal').modal('hide');
      await loadProducts();
    } catch (error) {
      console.error('Error saving product:', error);
    }
  }

  async function editProduct(id) {
    try {
      const data = await $.get(`/products/${id}`);
      $('#productId').val(data.id);
      $('#name').val(data.name);
      $('#price').val(data.price);
      $('#imageUrl').val(data.imageUrl);
      $('#productModal').modal('show');
    } catch (error) {
      console.error('Error editing product:', error);
    }
  }

  async function deleteProduct(id) {
    try {
      await $.ajax({
        url: `/products/${id}`,
        type: 'DELETE'
      });
      await loadProducts();
    } catch (error) {
      console.error('Error deleting product:', error);
    }
  }

  $('#addNewProduct').click(function () {
    $('#productForm')[0].reset();
    $('#productId').val('');
    $('#productModal').modal('show');
  });

  $('#saveProduct').click(async function () {
    await saveProduct();
  });

  $(document).on('click', '.btn-edit', function () {
    const id = $(this).data('id');
    editProduct(id);
  });

  $(document).on('click', '.btn-delete', function () {
    const id = $(this).data('id');
    deleteProduct(id);
  });

  loadProducts();
});
