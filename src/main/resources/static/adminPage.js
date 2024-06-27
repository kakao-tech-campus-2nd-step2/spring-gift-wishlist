let modal = document.getElementById('productModal');
let addProductbtn = document.getElementsByClassName('header-add')[0];
let span = document.getElementsByClassName('close')[0];

addProductbtn.onclick = function () {
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

function addProduct() {
    const name = document.getElementById('productName').value;
    const price = document.getElementById('productPrice').value;
    const imageUrl = document.getElementById('productImage').value;

    fetch(`/api/products?name=${name}&price=${price}&imageurl=${imageUrl}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    }).then((response) => {
        if (response.ok) {
            resetUrlAndReload();
        }
    });
}

document.addEventListener('DOMContentLoaded', function () {
    const mainCheckbox = document.querySelector(
        'table th input[type="checkbox"]'
    );
    const checkboxes = document.querySelectorAll(
        'table td input[type="checkbox"]'
    );

    const deleteButton = document.querySelector('.header-delete');

    mainCheckbox.addEventListener('click', function () {
        checkboxes.forEach((checkbox) => {
            checkbox.checked = mainCheckbox.checked;
        });
    });

    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener('click', function () {
            if (
                document.querySelectorAll(
                    'table td input[type="checkbox"]:checked'
                ).length === checkboxes.length
            ) {
                mainCheckbox.checked = true;
            } else {
                mainCheckbox.checked = false;
            }
        });
    });

    deleteButton.addEventListener('click', function () {
        const selectedCheckboxes = document.querySelectorAll(
            'table td input[type="checkbox"]:checked'
        );

        selectedCheckboxes.forEach((checkbox) => {
            const id = checkbox
                .closest('tr')
                .querySelector('td:nth-child(2)').innerText; // ID is in the second column
            deleteProduct(id);
        });

        mainCheckbox.checked = false;
    });
});

function deleteProduct(id) {
    fetch(`/api/products?id=${id}`, {
        method: 'DELETE',
    }).then((response) => {
        if (response.ok) {
            resetUrlAndReload();
        }
    });
}

function resetUrlAndReload() {
    const originalUrl = window.location.origin + window.location.pathname;
    history.pushState(null, '', originalUrl);
    location.reload();
}
