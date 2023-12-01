/*!
 * Color mode toggler for Bootstrap's docs (https://getbootstrap.com/)
 * Copyright 2011-2023 The Bootstrap Authors
 * Licensed under the Creative Commons Attribution 3.0 Unported License.
 */

(() => {
  'use strict'

  const getStoredTheme = () => localStorage.getItem('theme')
  const setStoredTheme = theme => localStorage.setItem('theme', theme)

  const getPreferredTheme = () => {
    const storedTheme = getStoredTheme()
    if (storedTheme) {
      return storedTheme
    }

    return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'
  }

  const setTheme = theme => {
    if (theme === 'auto' && window.matchMedia('(prefers-color-scheme: dark)').matches) {
      document.documentElement.setAttribute('data-bs-theme', 'dark')
    } else {
      document.documentElement.setAttribute('data-bs-theme', theme)
    }
  }

  const updateNavbarTogglerColor = theme => {
    const navbarToggler = document.querySelector('.navbar-toggler');
    if (navbarToggler) {
      navbarToggler.classList.remove('bg-dark', 'bg-light');
      navbarToggler.classList.add(theme === 'dark' ? 'bg-dark' : 'bg-light');
    }
  }


  setTheme(getPreferredTheme())

  const showActiveTheme = (theme, focus = false) => {
    const themeSwitcher = document.querySelector('#bd-theme')

    if (!themeSwitcher) {
      return
    }

    const themeSwitcherText = document.querySelector('#bd-theme-text')
    const activeThemeIcon = document.querySelector('.theme-icon-active use')
    const btnToActive = document.querySelector(`[data-bs-theme-value="${theme}"]`)
    const svgOfActiveBtn = btnToActive.querySelector('svg use').getAttribute('href')

    document.querySelectorAll('[data-bs-theme-value]').forEach(element => {
      element.classList.remove('active')
      element.setAttribute('aria-pressed', 'false')
    })

    btnToActive.classList.add('active')
    btnToActive.setAttribute('aria-pressed', 'true')
    activeThemeIcon.setAttribute('href', svgOfActiveBtn)
    const themeSwitcherLabel = `${themeSwitcherText.textContent} (${btnToActive.dataset.bsThemeValue})`
    themeSwitcher.setAttribute('aria-label', themeSwitcherLabel)

    if (focus) {
      themeSwitcher.focus()
    }

    updateNavbarTogglerColor(theme);
  }

  window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
    const storedTheme = getStoredTheme()
    if (storedTheme !== 'light' && storedTheme !== 'dark') {
      setTheme(getPreferredTheme())
      updateNavbarTogglerColor(newTheme);
    }
  })

  window.addEventListener('DOMContentLoaded', () => {
    showActiveTheme(getPreferredTheme())

    document.querySelectorAll('[data-bs-theme-value]')
      .forEach(toggle => {
        toggle.addEventListener('click', () => {
          const theme = toggle.getAttribute('data-bs-theme-value')
          setStoredTheme(theme)
          setTheme(theme)
          showActiveTheme(theme, true)
          updateNavbarTogglerColor(theme);
        })
      })
  })
})()

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

document.addEventListener('DOMContentLoaded', function() {

document.getElementById('enlace1').addEventListener('click', function() {
  var nameField = document.getElementById('nombre');
  
  nameField.readOnly = false;
});

document.getElementById('enlace2').addEventListener('click', function() {
  var emailField = document.getElementById('email');

  emailField.readOnly = false;
});
});

function mostrarPasswords() {
  let password = document.getElementById('newPassword');
  let password2 = document.getElementById('confirmPassword');
  password.classList.remove('hidden');
  password2.classList.remove('hidden');
  password.value = "";
  password2.value = "";
  document.getElementById('icon1').classList.remove('hidden');
  document.getElementById('icon2').classList.remove('hidden');
  document.getElementById('btn-cambiar').classList.add('hidden');
}

function validarFormulario() {
  var password1 = document.getElementById('newPassword').value;
  var password2 = document.getElementById('confirmPassword').value;

  if (password1 !== password2) {
      alert('Las contraseñas no coinciden.');
      return false; 
  }

  if (password1.length < 6 || password2.length < 6) {
      alert('Las contraseñas deben tener al menos 6 caracteres.');
      return false; 
  }
  return true;
}