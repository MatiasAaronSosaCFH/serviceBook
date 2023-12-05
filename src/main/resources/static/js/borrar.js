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

document.addEventListener('DOMContentLoaded', function () {
  const cabecera = document.querySelector('#cabecera');
  const detalles = document.querySelector('#detalles');
  const flecha = document.querySelector('#flecha');

  cabecera.addEventListener('click', function () {
      if (detalles.style.display === 'none' || detalles.style.display === '') {
          detalles.style.display = 'block';
          flecha.style.transform = 'rotate(180deg)';
      } else {
          detalles.style.display = 'none';
          flecha.style.transform = 'rotate(0deg)';
      }
  });
});

const inputImagen = document.getElementById('imagen');
const camara = document.getElementById('camara');
const fotoPerfil = document.getElementById('perfil-foto');

camara.addEventListener('click', () => {
    inputImagen.click(); 
});

fotoPerfil.addEventListener('click', () => {
    inputImagen.click(); 
});

inputImagen.addEventListener('change', () => {
    const file = inputImagen.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            fotoPerfil.innerHTML = `<img src="${e.target.result}" alt="Nueva Foto">`;
        };
        reader.readAsDataURL(file);
    }
});

function mostrarFormulario() {
  document.getElementById("detalles-direccion").innerHTML =
      '<form method="POST" th:action="@{/direccion/registrar/__${usuario.id}__}" id="formulario-direccion" class="formulario" th:each="direccion : ${usuario.direcciones}">' +
      '<div th:if="${error}" class="alert alert-danger" role="alert">' +
      '<p th:text="${error}"></p>' +
      '</div>' +
      '<input type="hidden" name="idUsuario" th:value="${usuario.id}">' +
      '   <label for="provincia">Provincia:</label>' +
      '   <input type="text" id="provincia" name="provincia" th:value="">' +
      '   <label for="localidad">Localidad:</label>' +
      '   <input type="text" id="localidad" name="localidad" th:value="">' +
      '   <label for="calle">Calle:</label>' +
      '   <input type="text" id="calle" name="calle" th:value="">' +
      '   <label for="numero">Número:</label>' +
      '   <input type="text" id="numero" name="numero" th:value="">' +
      '   <button type="submit" id="boton-guardar3">Guardar Cambios</button>' +
      '</form>';
}

document.addEventListener('DOMContentLoaded', function () {
  const cabecera = document.querySelector('#cabecera-direccion');
  const detalles = document.querySelector('#detalles-direccion');
  const flecha = document.querySelector('#flecha-direccion');

  cabecera.addEventListener('click', function () {
      if (detalles.style.display === 'none' || detalles.style.display === '') {
          detalles.style.display = 'block';
          flecha.style.transform = 'rotate(180deg)';
      } else {
          detalles.style.display = 'none';
          flecha.style.transform = 'rotate(0deg)';
      }
  });
});

document.addEventListener('DOMContentLoaded', function () {
  const cabecera = document.querySelector('#cabecera-contrasena');
  const detalles = document.querySelector('#detalles-contrasena');
  const flecha = document.querySelector('#flecha-contrasena');

  cabecera.addEventListener('click', function () {
      if (detalles.style.display === 'none' || detalles.style.display === '') {
          detalles.style.display = 'block';
          flecha.style.transform = 'rotate(180deg)';
      } else {
          detalles.style.display = 'none';
          flecha.style.transform = 'rotate(0deg)';
      }
  });
});