const modal = document.getElementById('productModal');
const closeButton = document.getElementsByClassName('close')[0];
const mainCheckbox = document.querySelector('table th input[type="checkbox"]');
const checkboxes = document.querySelectorAll('table td input[type="checkbox"]');

let apiUrl = window.location.origin + '/api/products';

export function addProductbtnOnClick(page) {
    modal.getElementsByTagName('h1')[0].innerText = 'Add a new product';
    modal.getElementsByTagName('button')[0].onclick = addProduct.bind(
        null,
        page
    );
    modal.style.display = 'flex';
}

window.addProductbtnOnClick = addProductbtnOnClick;

export function editProductbtnOnClick(id, page) {
    modal.getElementsByTagName('h1')[0].innerText = 'Edit product';
    modal.getElementsByTagName('button')[0].onclick = editProduct.bind(
        null,
        id,
        page
    );
    modal.style.display = 'flex';
}

window.editProductbtnOnClick = editProductbtnOnClick;

closeButton.onclick = function () {
    modal.style.display = 'none';
};

window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = 'none';
    }
};

export function addProduct(page) {
    const name = document.getElementById('productName').value;
    const price = document.getElementById('productPrice').value;
    const imageUrl = document.getElementById('productImage').value;

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

export function mainCheckboxOnClick() {
    checkboxes.forEach((checkbox) => {
        checkbox.checked = mainCheckbox.checked;
    });
}

window.mainCheckboxOnClick = mainCheckboxOnClick;

export function checkboxOnClick() {
    if (
        document.querySelectorAll('table td input[type="checkbox"]:checked')
            .length === checkboxes.length
    ) {
        mainCheckbox.checked = true;
    } else {
        mainCheckbox.checked = false;
    }
}

window.checkboxOnClick = checkboxOnClick;

export function deleteCheckedProductsOnClick(page) {
    const selectedCheckboxes = document.querySelectorAll(
        'table td input[type="checkbox"]:checked'
    );

    selectedCheckboxes.forEach((checkbox) => {
        const id = checkbox
            .closest('tr')
            .querySelector('td:nth-child(2)').innerText; // ID is in the second column
        deleteProduct(id, page);
    });
    mainCheckbox.checked = false;
}

window.deleteCheckedProductsOnClick = deleteCheckedProductsOnClick;

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
    const imageUrl = document.getElementById('productImage').value;

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
