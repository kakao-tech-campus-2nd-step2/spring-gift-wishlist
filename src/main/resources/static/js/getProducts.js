function addProductRow(element) {
  const table = document.getElementById('productTable').getElementsByTagName('tbody')[0];
  const newRow = table.insertRow();

  const newIdCell = newRow.insertCell(0);
  const newNameCell = newRow.insertCell(1);
  const newPriceCell = newRow.insertCell(2);
  const newImageCell = newRow.insertCell(3);
  const saveCell = newRow.insertCell(4);
  const cancelCell = newRow.insertCell(5);

  newNameCell.innerHTML = '<input type="text" id="newName" oninput="validateName(this)"> <span class="nameMessage"></span>';
  newPriceCell.innerHTML = '<input type="text" id="newPrice" oninput="validatePrice(this)"> <span class="priceMessage"></span>';
  newImageCell.innerHTML = '<input type="text" id="newImage">';
  saveCell.innerHTML = '<img src="/image/save.png" alt="save" id="saveButton" style="width:100px;height: auto" onclick="saveAddProduct(this)">';
  cancelCell.innerHTML = '<img src="/image/cancel.png" alt="cancel" style="width:100px;height: auto" onclick="cancelProductEditing(this)">';

  element.style.pointerEvents = 'none';
  element.style.opacity = '0.3';
}

function validateName(element) {
  const inputName = element.value;
  const inputMessage = element.nextElementSibling;
  const pattern1 = /^[a-zA-Z0-9ㄱ-ㅎ가-힣()\[\]+\-&/_ ]+$/;
  const pattern2 = /^((?!카카오).)*$/;

  if(inputName.length < 1 || inputName.length > 15) {
    inputMessage.textContent = "제품명 길이는 1~15자만 가능합니다.";
    inputMessage.style.color = "red";
    element.style.pointerEvents = 'none';
    element.style.opacity = '0.3';
  }
  else if (pattern1.test(inputName) && pattern2.test(inputName)) {
    inputMessage.textContent = "올바른 이름입니다.";
    inputMessage.style.color = "green";
    element.style.pointerEvents = 'auto';
    element.style.opacity = '1';
  } else if (pattern1.test(inputName) && !pattern2.test(inputName)) {
    inputMessage.textContent = "카카오가 포함된 문구는 담당 MD와 협의한 후에 사용해주시기 바랍니다.";
    inputMessage.style.color = "red";
    element.style.pointerEvents = 'none';
    element.style.opacity = '0.3';
  } else {
    inputMessage.textContent = "( ), [ ], +, -, &, /, _을 제외한 특수문자는 입력할 수 없습니다.";
    inputMessage.style.color = "red";
    element.style.pointerEvents = 'none';
    element.style.opacity = '0.3';
  }
}

function validatePrice(element) {
  const inputPrice = element.value;
  const inputMessage = element.nextElementSibling;

  if(isNaN(inputPrice)){
    inputMessage.textContent = "가격을 숫자로 입력해주세요";
    inputMessage.style.color = "red";
    element.style.pointerEvents = 'none';
    element.style.opacity = '0.3';
  }
  else if(inputPrice.length < 1 || inputPrice > 2147483647) {
    inputMessage.textContent = "가격은 1~2147483647원 까지만 가능합니다.";
    inputMessage.style.color = "red";
    element.style.pointerEvents = 'none';
    element.style.opacity = '0.3';
  }
  else {
    inputMessage.textContent = "올바른 가격입니다.";
    inputMessage.style.color = "green";
    element.style.pointerEvents = 'auto';
    element.style.opacity = '1';
  }
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
  const table = document.getElementById('productTable').getElementsByTagName('tbody')[0];
  table.deleteRow(table.rows.length - 1);

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

  nameCell.innerHTML = `<input type="text" class="newName" value="${currentName}" oninput="validateName(this)"> <span class="nameMessage"></span>`;
  priceCell.innerHTML = `<input type="text" class="newPrice" value="${currentPrice}" oninput="validatePrice(this)"> <span class="priceMessage"></span>`;
  imageCell.innerHTML = `<input type="text" class="newImage" value="${currentImage}">`;

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