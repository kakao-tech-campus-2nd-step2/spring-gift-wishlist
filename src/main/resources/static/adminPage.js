let modal = document.getElementById('productModal');
let addProductbtn = document.getElementsByClassName('header-add')[0];
let editProductbtn = document.getElementsByClassName('content-edit')[0];
let span = document.getElementsByClassName('close')[0];

let apiUrl = window.location.origin + '/api/products';

addProductbtnOnClick = function (page) {
    modal.getElementsByTagName('h1')[0].innerText = 'Add a new product';
    modal.getElementsByTagName('button')[0].onclick = addProduct.bind(
        null,
        page
    );
    modal.style.display = 'flex';
};

editProductbtnOnClick = function (id, page) {
    console.log(id, page);
    modal.getElementsByTagName('h1')[0].innerText = 'Edit product';
    modal.getElementsByTagName('button')[0].onclick = editProduct.bind(
        null,
        id,
        page
    );
    modal.style.display = 'flex';
};

span.onclick = function () {
    modal.style.display = 'none';
};

window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = 'none';
    }
};

function addProduct(page) {
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

const mainCheckbox = document.querySelector('table th input[type="checkbox"]');
const checkboxes = document.querySelectorAll('table td input[type="checkbox"]');

function mainCheckboxOnClick() {
    checkboxes.forEach((checkbox) => {
        checkbox.checked = mainCheckbox.checked;
    });
}

function checkboxOnClick() {
    if (
        document.querySelectorAll('table td input[type="checkbox"]:checked')
            .length === checkboxes.length
    ) {
        mainCheckbox.checked = true;
    } else {
        mainCheckbox.checked = false;
    }
}

function deleteCheckedProductsOnClick(page) {
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

function deleteProduct(id, page) {
    fetch(`${apiUrl}?id=${id}`, {
        method: 'DELETE',
    }).then((response) => {
        if (response.ok) {
            pagination(page);
        }
    });
}

function editProduct(id, page) {
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

function pagination(page) {
    const originalUrl = `${window.location.origin}/admin?page=${page}`;
    window.location.href = originalUrl;
}
