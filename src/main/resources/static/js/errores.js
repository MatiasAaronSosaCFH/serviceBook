const openToastrExito = () => {
  const toastrExito = document.querySelector('#toastrExito');
  toastrExito.style.display = 'flex';
  closeToastrExito();  
}

const openToastrError = () => {
  const toastrError = document.querySelector('#toastrError');
  toastrError.style.display = 'flex';
  closeToastrError();
}

const closeToastrExito = () => {
  setTimeout(() => {
    const toastrExito = document.querySelector('#toastrExito');
    toastrExito.style.display = 'none';
  }, 6000);
}

const closeToastrError = () => {
  setTimeout(() => {
    const toastrError = document.querySelector('#toastrError');
    toastrError.style.display = 'none';
  }, 6000);
}