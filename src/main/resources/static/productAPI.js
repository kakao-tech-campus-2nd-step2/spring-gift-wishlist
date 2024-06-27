let apiUrl = window.location.origin + '/api/products';

export function addProduct(page) {
    const name = document.getElementById('productName').value;
    const price = document.getElementById('productPrice').value;
    const imageUrl = document.getElementById('productImageUrl').value;

    fetch(`${apiUrl}?name=${name}&price=${price}&imageurl=${imageUrl}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    }).then((response) => {
        if (response.ok) {
            pagination(page);
        }
    });
}

export function deleteProduct(id, page) {
    fetch(`${apiUrl}?id=${id}`, {
        method: 'DELETE',
    }).then((response) => {
        if (response.ok) {
            pagination(page);
        }
    });
}

window.deleteProduct = deleteProduct;

export function editProduct(id, page) {
    const name = document.getElementById('productName').value;
    const price = document.getElementById('productPrice').value;
    const imageUrl = document.getElementById('productImageUrl').value;

    fetch(
        `${apiUrl}?id=${id}&name=${name}&price=${price}&imageurl=${imageUrl}`,
        {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
        }
    ).then((response) => {
        if (response.ok) {
            pagination(page);
        }
    });
}

export function pagination(page) {
    const originalUrl = `${window.location.origin}/admin?page=${page}`;
    window.location.href = originalUrl;
}

window.pagination = pagination;
