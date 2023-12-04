$(document).ready(function() {
  function setMainMargin() {
    var navbarHeight = $(".fixed-top").outerHeight();
    $("#main-content").css("margin-top", navbarHeight + "px");
  }

  setMainMargin();
  $(window).resize(setMainMargin);

  $('#navbarScroll').on('show.bs.collapse hide.bs.collapse', function () {
    $('#navbarScroll').one('shown.bs.collapse hidden.bs.collapse', function () {
      setMainMargin();
    });
  });
});

// document.addEventListener('DOMContentLoaded', function() {

// document.getElementById('enlace1').addEventListener('click', function() {
//   var nameField = document.getElementById('nombre');
  
//   nameField.readOnly = false;
// });

// document.getElementById('enlace2').addEventListener('click', function() {
//   var emailField = document.getElementById('email');

//   emailField.readOnly = false;
// });
// });

// function mostrarPasswords() {
//   let password = document.getElementById('newPassword');
//   let password2 = document.getElementById('confirmPassword');
//   password.classList.remove('hidden');
//   password2.classList.remove('hidden');
//   password.value = "";
//   password2.value = "";
//   document.getElementById('icon1').classList.remove('hidden');
//   document.getElementById('icon2').classList.remove('hidden');
//   document.getElementById('btn-cambiar').classList.add('hidden');
// }

// function validarFormulario() {
//   var password1 = document.getElementById('newPassword').value;
//   var password2 = document.getElementById('confirmPassword').value;

//   if (password1 !== password2) {
//       alert('Las contraseñas no coinciden.');
//       return false; 
//   }

//   if (password1.length < 6 || password2.length < 6) {
//       alert('Las contraseñas deben tener al menos 6 caracteres.');
//       return false; 
//   }
//   return true;
// }

