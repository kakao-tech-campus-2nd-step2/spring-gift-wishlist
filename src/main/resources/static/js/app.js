function deleteProduct(element) {
  if (confirm('이 제품을 삭제하시겠습니까?')) {
    const productId = element.getAttribute('data-id');
    fetch(`/products/${productId}`, {
      method: 'DELETE'
    })
    .then(response => response.text())
    .then(result => {
      if (result === 'success') {
        window.location.reload();
      } else {
        alert('Error deleting product');
      }
    });
  }
}
