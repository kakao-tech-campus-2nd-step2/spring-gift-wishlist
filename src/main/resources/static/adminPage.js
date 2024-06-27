let modal = document.getElementById("productModal");
let addProductbtn = document.getElementsByClassName("header-add")[0];
let span = document.getElementsByClassName("close")[0];

addProductbtn.onclick = function () {
  modal.style.display = "flex";
};

span.onclick = function () {
  modal.style.display = "none";
};

window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
};
