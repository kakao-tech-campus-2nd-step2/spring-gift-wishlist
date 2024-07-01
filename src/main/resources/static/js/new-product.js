const addProduct = async () => {
  const name = document.getElementById('name').value;
  const price = document.getElementById('price').value;
  const imageUrl = document.getElementById('imageUrl').value;
  const response = await fetch('/api/products', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({name, price, imageUrl})
  });
  return await response.json();
}

document.getElementById('add').addEventListener('click', async (event) => {
  event.preventDefault();
  const product = await addProduct();
  console.log(`Product added: ${product}`);
  // 홈 화면으로 이동
  window.location.href = '/admin/products';
});