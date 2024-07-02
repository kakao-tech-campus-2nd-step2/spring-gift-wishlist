function addProductRow(element) {
  const table = document.getElementById('productTable').getElementsByTagName(
      'tbody')[0];
  const newRow = table.insertRow();

  const newIdCell = newRow.insertCell(0);
  const newNameCell = newRow.insertCell(1);
  const newPriceCell = newRow.insertCell(2);
  const newImageCell = newRow.insertCell(3);
  const saveCell = newRow.insertCell(4);
  const cancelCell = newRow.insertCell(5);

  newNameCell.innerHTML = '<input type="text" id="newName">';
  newPriceCell.innerHTML = '<input type="text" id="newPrice">';
  newImageCell.innerHTML = '<input type="text" id="newImage">';
  saveCell.innerHTML = '<img src="/image/save.png" alt="save" style="width:100px;height: auto" onclick="saveAddProduct()">';
  cancelCell.innerHTML = '<img src="/image/cancel.png" alt="cancel" style="width:100px;height: auto" onclick="cancelProductEditing()">';

  // 추가 버튼 비활성화
  element.style.pointerEvents = 'none';
  element.style.opacity = '0.3';
}

function saveAddProduct() {
  const newName = document.getElementById('newName').value;
  const newPrice = document.getElementById('newPrice').value;
  const newImage = document.getElementById('newImage').value;

  let requestJson = {
    "name": newName,
    "price": newPrice,
    "imageUrl": newImage
  };

  $.ajax({
    type: 'POST',
    url: '/api/products',
    dataType: 'json',
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify(requestJson),
    success: function () {
      alert('상품 추가를 성공하였습니다.');
      window.location.href = '/api/products';
    },
    error: function (xhr) {
      if (xhr.responseJSON && xhr.responseJSON.isError
          && xhr.responseJSON.message) {
        alert('오류: ' + xhr.responseJSON.message);
      } else {
        alert('상품 추가를 실패하였습니다. 값을 제대로 입력했는지 확인해주세요');
      }
    }
  });
}

function cancelProductEditing() {
  const table = document.getElementById('productTable').getElementsByTagName(
      'tbody')[0];
  table.deleteRow(table.rows.length - 1);

  // 추가 버튼 다시 활성화
  const addButton = document.getElementById('addButton');
  addButton.style.pointerEvents = 'auto';
  addButton.style.opacity = '1';
}

function removeProductRow(button) {
  const row = button.closest('tr');
  const productId = row.getAttribute('data-id');

  $.ajax({
    type: 'DELETE',
    url: `/api/products/${productId}`,
    contentType: 'application/json; charset=utf-8',
    success: function () {
        alert('상품 삭제를 성공하였습니다.');
      window.location.href = '/api/products';
    },
    error: function (xhr) {
      if (xhr.responseJSON && xhr.responseJSON.isError
          && xhr.responseJSON.message) {
        alert('오류: ' + xhr.responseJSON.message);
      } else {
        alert('상품 삭제를 실패하였습니다.');
      }
      window.location.href = '/api/products';
    }
  });
}

function editProductRow(button) {
  const row = button.closest('tr');

  const nameCell = row.querySelector('.productName');
  const priceCell = row.querySelector('.productPrice');
  const imageCell = row.querySelector('.productImage');

  const currentName = nameCell.innerText;
  const currentPrice = priceCell.innerText;
  const currentImage = imageCell.querySelector('img').src;

  nameCell.innerHTML = `<input type="text" class="newName" value="${currentName}">`;
  priceCell.innerHTML = `<input type="text" class="newPrice" value="${currentPrice}">`;
  imageCell.innerHTML = `<input type="text" class="newImage" value="${currentImage}">`;

  // 편집 버튼을 저장 버튼으로 변경
  button.setAttribute('src', '/image/save.png');
  button.setAttribute('alt', 'save');
  button.setAttribute('onclick', 'savePutProductRow(this)');
}

function savePutProductRow(button) {
  const row = button.closest('tr');
  const productId = row.getAttribute('data-id');
  const newName = row.querySelector('.newName').value;
  const newPrice = row.querySelector('.newPrice').value;
  const newImage = row.querySelector('.newImage').value;

  let requestJson = {
    "id": productId,
    "name": newName,
    "price": newPrice,
    "imageUrl": newImage
  };

  $.ajax({
    type: 'PUT',
    url: `/api/products/${productId}`,
    dataType: 'json',
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify(requestJson),
    success: function () {
      alert('상품 수정을 성공하였습니다.');
      window.location.href = '/api/products';
    },
    error: function (xhr) {
      if (xhr.responseJSON && xhr.responseJSON.isError
          && xhr.responseJSON.message) {
        alert('오류: ' + xhr.responseJSON.message);
      } else {
        alert('상품 수정을 실패하였습니다. 값을 제대로 입력했는지 확인해주세요');
      }
    }
  });
}