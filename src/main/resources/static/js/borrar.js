function openDeleteModal() {
  let modal = document.getElementById("deleteModal");
  modal.style.display = "block";
  modal.classList.add("d-flex");
  modal.classList.add("justify-content-center");
  modal.classList.add("align-items-center");
}

function closeDeleteModal() {
  let modal = document.getElementById("deleteModal");
  modal.style.display = "none";
  modal.classList.remove("d-flex");
  modal.classList.remove("justify-content-center");
  modal.classList.remove("align-items-center");
}

document.getElementById("btnCancelar").addEventListener("click", function () {
  closeDeleteModal();
});

document
  .getElementById("enlace-borrar")
  .addEventListener("click", openDeleteModal);

document.getElementById("habilitar").addEventListener("change", function () {
  var eliminarBtn = document.getElementById("btnEliminar");
  eliminarBtn.disabled = !this.checked;
});

document.addEventListener("DOMContentLoaded", function () {
  const cabecera = document.querySelector("#cabecera");
  const detalles = document.querySelector("#detalles");
  const flecha = document.querySelector("#flecha");

  cabecera.addEventListener("click", function () {
    if (detalles.style.display === "none" || detalles.style.display === "") {
      detalles.style.display = "block";
      flecha.style.transform = "rotate(180deg)";
    } else {
      detalles.style.display = "none";
      flecha.style.transform = "rotate(0deg)";
    }
  });
});

const inputImagen = document.getElementById("imagen");
const camara = document.getElementById("camara");
const fotoPerfil = document.getElementById("perfil-foto");

camara.addEventListener("click", () => {
  inputImagen.click();
});

fotoPerfil.addEventListener("click", () => {
  inputImagen.click();
});

inputImagen.addEventListener("change", () => {
  const file = inputImagen.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      fotoPerfil.innerHTML = `<img src="${e.target.result}" alt="Nueva Foto">`;
    };
    reader.readAsDataURL(file);
  }
});

document.addEventListener("DOMContentLoaded", function () {
  const cabecera = document.querySelector("#cabecera-direccion");
  const detalles = document.querySelector("#detalles-direccion");
  const flecha = document.querySelector("#flecha-direccion");

  cabecera.addEventListener("click", function () {
    if (detalles.style.display === "none" || detalles.style.display === "") {
      detalles.style.display = "block";
      flecha.style.transform = "rotate(180deg)";
    } else {
      detalles.style.display = "none";
      flecha.style.transform = "rotate(0deg)";
    }
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const cabecera = document.querySelector("#cabecera-contrasena");
  const detalles = document.querySelector("#detalles-contrasena");
  const flecha = document.querySelector("#flecha-contrasena");

  cabecera.addEventListener("click", function () {
    if (detalles.style.display === "none" || detalles.style.display === "") {
      detalles.style.display = "block";
      flecha.style.transform = "rotate(180deg)";
    } else {
      detalles.style.display = "none";
      flecha.style.transform = "rotate(0deg)";
    }
  });
});

function abrirDireccion() {
  const detalles = document.querySelector("#detalles-direccion");
  const flecha = document.querySelector("#flecha-direccion");

  if (detalles.style.display === "none" || detalles.style.display === "") {
    detalles.style.display = "block";
    flecha.style.transform = "rotate(180deg)";
  }
  const formDireccion = document.getElementById("formDireccion");

  if (formDireccion) {
    formDireccion.scrollIntoView({ behavior: "smooth" });
  }
}

function abrirPassword() {
  const detalles = document.querySelector("#detalles-contrasena");
  const flecha = document.querySelector("#flecha-contrasena");

  if (detalles.style.display === "none" || detalles.style.display === "") {
    detalles.style.display = "block";
    flecha.style.transform = "rotate(180deg)";
  }

  const formContrasena = document.getElementById("formContrasena");

  if (formContrasena) {
    formContrasena.scrollIntoView({ behavior: "smooth" });
  }
}

function abrirBasica() {
  const detalles = document.querySelector("#detalles");
  const flecha = document.querySelector("#flecha");

  if (detalles.style.display === "none" || detalles.style.display === "") {
    detalles.style.display = "block";
    flecha.style.transform = "rotate(180deg)";
  }

  const formBasica = document.getElementById("formBasica");

  if (formBasica) {
    formBasica.scrollIntoView({ behavior: "smooth" });
  }
}

function mostrarFormularioModificar(localidad, provincia, calle, numero, id) {
  
  var formExistente = document.getElementById('formDireccion');
  
  if (formExistente) {
    formExistente.remove();
  }

  var form = document.createElement("form");
  form.method = "POST";
  form.action = "/direccion/modificar/" + id;
  form.id = "formDireccion";
  form.className = "formulario";

  // form.insertAdjacentHTML(
  //   "afterbegin",
  //   '<div th:if="${errorDireccionRegistrar}" th:class="${\'alert alert-danger\'}"' +
  //     'role="alert">' +
  //     '<p th:text="${errorDireccionRegistrar}"></p>' +
  //     '</div>'
  // );

  var localidadLabel = document.createElement("label");
  localidadLabel.htmlFor = "localidad";
  var textLocalidad = document.createTextNode("Localidad:");
  localidadLabel.appendChild(textLocalidad);

  var localidadInput = document.createElement("input");
  localidadInput.type = "text";
  localidadInput.name = "localidad";
  localidadInput.id = "localidad";
  localidadInput.value = localidad;

  var provinciaLabel = document.createElement("label");
  provinciaLabel.htmlFor = "provincia";
  var textProvincia = document.createTextNode("Provincia:");
  provinciaLabel.appendChild(textProvincia);

  var provinciaInput = document.createElement("input");
  provinciaInput.type = "text";
  provinciaInput.name = "provincia";
  provinciaInput.id = "provincia";
  provinciaInput.value = provincia;

  var calleLabel = document.createElement("label");
  calleLabel.htmlFor = "calle";
  var textCalle = document.createTextNode("Calle:");
  calleLabel.appendChild(textCalle);

  var calleInput = document.createElement("input");
  calleInput.type = "text";
  calleInput.name = "calle";
  calleInput.id = "calle";
  calleInput.value = calle;

  var numeroLabel = document.createElement("label");
  numeroLabel.htmlFor = "numero";
  var textNumero = document.createTextNode("Número:");
  numeroLabel.appendChild(textNumero);

  var numeroInput = document.createElement("input");
  numeroInput.type = "text";
  numeroInput.name = "numero";
  numeroInput.id = "numero";
  numeroInput.value = numero;

  var miBoton = document.createElement("button");
  miBoton.type = "submit";
  miBoton.innerHTML = "Modificar Dirección";
  miBoton.id = "boton-guardar3";

  form.appendChild(localidadLabel);
  form.appendChild(localidadInput);
  form.appendChild(provinciaLabel);
  form.appendChild(provinciaInput);
  form.appendChild(calleLabel);
  form.appendChild(calleInput);
  form.appendChild(numeroLabel);
  form.appendChild(numeroInput);
  form.appendChild(miBoton);

  var detalleDireccionDiv = document.getElementById("detalles-direccion");

  var tabla = document.getElementById("tabla");

  detalleDireccionDiv.insertBefore(form, tabla.nextSibling);

}

function mostrarFormularioRegistro(id) {
  var formExistente = document.getElementById('formDireccion');
  
  if (formExistente) {
    formExistente.remove();
  }

  var form = document.createElement("form");
  form.method = "POST";
  form.action = "/direccion/registrar/" + id;
  form.id = "formDireccion";
  form.className = "formulario";

  // form.insertAdjacentHTML(
  //   "afterbegin",
  //   '<div th:if="${errorDireccion}" th:class="${\'alert alert-danger\'}"' +
  //     'role="alert">' +
  //     '<p th:text="${errorDireccion}"></p>' +
  //     '</div>'
  // );

  var localidadLabel = document.createElement("label");
  localidadLabel.htmlFor = "localidad";
  var textLocalidad = document.createTextNode("Localidad:");
  localidadLabel.appendChild(textLocalidad);

  var localidadInput = document.createElement("input");
  localidadInput.type = "text";
  localidadInput.name = "localidad";
  localidadInput.id = "localidad";

  var provinciaLabel = document.createElement("label");
  provinciaLabel.htmlFor = "provincia";
  var textProvincia = document.createTextNode("Provincia:");
  provinciaLabel.appendChild(textProvincia);

  var provinciaInput = document.createElement("input");
  provinciaInput.type = "text";
  provinciaInput.name = "provincia";
  provinciaInput.id = "provincia";

  var calleLabel = document.createElement("label");
  calleLabel.htmlFor = "calle";
  var textCalle = document.createTextNode("Calle:");
  calleLabel.appendChild(textCalle);

  var calleInput = document.createElement("input");
  calleInput.type = "text";
  calleInput.name = "calle";
  calleInput.id = "calle";

  var numeroLabel = document.createElement("label");
  numeroLabel.htmlFor = "numero";
  var textNumero = document.createTextNode("Número:");
  numeroLabel.appendChild(textNumero);

  var numeroInput = document.createElement("input");
  numeroInput.type = "text";
  numeroInput.name = "numero";
  numeroInput.id = "numero";

  var miBoton = document.createElement("button");
  miBoton.type = "submit";
  miBoton.innerHTML = "Registrar Dirección";
  miBoton.id = "boton-guardar3";

  form.appendChild(localidadLabel);
  form.appendChild(localidadInput);
  form.appendChild(provinciaLabel);
  form.appendChild(provinciaInput);
  form.appendChild(calleLabel);
  form.appendChild(calleInput);
  form.appendChild(numeroLabel);
  form.appendChild(numeroInput);
  form.appendChild(miBoton);

  var detalleDireccionDiv = document.getElementById("detalles-direccion");

  var tabla = document.getElementById("tabla");

  detalleDireccionDiv.insertBefore(form, tabla.nextSibling);
}

function insertarError(error){

  var form = document.getElementById("formDireccion");

  form.insertAdjacentHTML(
    "afterbegin",
    '<div class="alert alert-danger"' +
      'role="alert">' +
      `<p>${error}</p>` +
      '</div>'
  );

}

$(document).ready(function() {
  $('#selectedOptions').select2({
    placeholder: 'Selecciona profesiones',
    allowClear: true,
    dropdownParent: $('#customDropdownParent')
  });
});