function openDeleteModal() {
  let modal = document.getElementById("deleteModal");
  modal.style.display = 'block';
  modal.classList.add('d-flex');
  modal.classList.add('justify-content-center');
  modal.classList.add('align-items-center');
}

function closeDeleteModal() {
  let modal = document.getElementById("deleteModal");
  modal.style.display = 'none';
  modal.classList.remove('d-flex');
  modal.classList.remove('justify-content-center');
  modal.classList.remove('align-items-center');
}

document.getElementById("enlace-borrar").addEventListener("click", openDeleteModal);

document.getElementById("habilitar").addEventListener("change", function () {
  var eliminarBtn = document.getElementById("btnEliminar");
  eliminarBtn.disabled = !this.checked;
});