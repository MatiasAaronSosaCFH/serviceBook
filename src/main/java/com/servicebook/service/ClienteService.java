package com.servicebook.service;

import com.servicebook.exception.MiException;
import com.servicebook.models.Cliente;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.ClienteRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  public Cliente findById(Long id) {
    return clienteRepository.findById(id).orElse(null);
  }

  public Cliente findByEmail(String email) {
    return clienteRepository.findByEmail(email).orElse(null);
  }

  public void crearCliente(String email, String nombre, String password, String password2) throws MiException {
    validar(email, nombre, password, password2);

    Cliente cliente = new Cliente();

    cliente.setEmail(email);
    cliente.setNombre(nombre);
    cliente.setPassword(password);
    cliente.setFechaDeAlta(new Date());
    cliente.setRole(Role.USER);

    clienteRepository.save(cliente);
  }

  public void validar(String email, String nombre, String password, String password2) throws MiException {

    if (email.isEmpty()) {
      throw new MiException("el email no puede ser nulo o estar vacio");
    }
    if (nombre.isEmpty()) {
      throw new MiException("el nombre no puede ser nulo o estar vacio");
    }

    if (password.isEmpty() || password == null || password.length() <= 5) {
      throw new MiException("la contraseña no puede estar vacia, y debe tener mas de 5 digitos");
    }
    if (!password.equals(password2)) {
      throw new MiException("las contraseñas ingresadas deben ser iguales");
    }

  }

}
