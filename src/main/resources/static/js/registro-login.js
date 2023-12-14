function openLoginModal() {
  let modal = document.getElementById("loginModal");
  modal.style.display = "block";
  modal.classList.add("d-flex");
  modal.classList.add("justify-content-center");
  modal.classList.add("align-items-center");
}

function openRegisterModal() {
  let modal = document.getElementById("registerModal");
  modal.style.display = "block";
  modal.classList.add("d-flex");
  modal.classList.add("justify-content-center");
  modal.classList.add("align-items-center");
  closeLoginModal();
}

function closeLoginModal() {
  let modal = document.getElementById("loginModal");
  modal.style.display = "none";
  modal.classList.remove("d-flex");
  modal.classList.remove("justify-content-center");
  modal.classList.remove("align-items-center");
}

function closeRegisterModal() {
  let modal = document.getElementById("registerModal");
  modal.style.display = "none";
  modal.classList.remove("d-flex");
  modal.classList.remove("justify-content-center");
  modal.classList.remove("align-items-center");
}

function volverLoginModal() {
  let modal = document.getElementById("loginModal");
  modal.style.display = "block";
  modal.classList.add("d-flex");
  modal.classList.add("justify-content-center");
  modal.classList.add("align-items-center");
  closeRegisterModal();
}

document
  .getElementById("btnOpenLoginModal")
  .addEventListener("click", openLoginModal);

document
  .getElementById("enlace-registrar")
  .addEventListener("click", openRegisterModal);

document
  .getElementById("btnVolver")
  .addEventListener("click", volverLoginModal);


