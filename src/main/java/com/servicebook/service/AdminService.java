/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.service;

import com.servicebook.exception.MiException;
import com.servicebook.models.Admin;
import com.servicebook.models.dtos.AdminDtoEnviado;
import com.servicebook.repository.AdminRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SantiagoPaguaga
 */
@Service
public class AdminService {

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  public Admin findById(Long id) {
    return adminRepository.findById(id).orElse(null);
  }
  
  @Transactional
  public void bajaAdmin(Long id) {
    Admin admin = new Admin() {
    };

    Optional<Admin> respuesta = adminRepository.findById(id);

    if (respuesta.isPresent()) {
      admin = respuesta.get();
      admin.setAlta(false);

      adminRepository.save(admin);

    }
  }

  public void modificarPassword(Long id, String passwordActual, String nuevaPassword, String repetirPassword) throws MiException {
    Optional<Admin> adminResp = adminRepository.buscarPorId(id);

    if (adminResp.isPresent()) {

      Admin admin = adminResp.get();

      if (!passwordEncoder.matches(passwordActual, admin.getPassword())) {
        throw new MiException("Ingresó una contraseña incorrecta");
      }

      if (nuevaPassword.length() < 6 || repetirPassword.length() < 6) {

        throw new MiException("La contraseña no puede tener menos de 6 caracteres");

      }

      if (!nuevaPassword.equals(repetirPassword)) {
        throw new MiException("Las contraseñas nuevas no coinciden");
      }

      // Actualizar la contraseña
      admin.setPassword(passwordEncoder.encode(nuevaPassword));
      adminRepository.save(admin);

    }

  }

  @Transactional
  public void modificar(Long id, Admin adminActualizado) throws MiException {

    Optional<Admin> adminResp = adminRepository.buscarPorId(id);

    if (adminResp.isPresent()) {

      Admin admin = adminResp.get();

      if (adminActualizado.getNombre().trim().isEmpty()) {

        throw new MiException("El nombre no puede estar vacío");

      }

      if (adminActualizado.getEmail().trim().isEmpty()) {

        throw new MiException("El email no puede estar vacío");

      }

      if (adminActualizado.getNombre() != null) {
        admin.setNombre(adminActualizado.getNombre());
      }
      if (adminActualizado.getEmail() != null) {
        admin.setEmail(adminActualizado.getEmail());
      }
      if (adminActualizado.getAlta() != null) {
        admin.setAlta(adminActualizado.getAlta());
      }
      if (adminActualizado.getRole() != null) {
        admin.setRole(adminActualizado.getRole());
      }
      if (adminActualizado.getFoto() != null) {
        admin.setFoto(adminActualizado.getFoto());
      }
      if (adminActualizado.getPassword() != null) {
        admin.setPassword(adminActualizado.getPassword());
      }

      adminRepository.save(admin);

    }

  }
  
  @Transactional
  public AdminDtoEnviado obtenerDtoAdmin(Long AdminId) {
    Admin admin = adminRepository.findById(AdminId).orElse(null);
    if (admin != null) {
      return new AdminDtoEnviado(admin);
    }
    return null;
  }

}
