/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servicebook.service;

import com.servicebook.exception.MiException;
import com.servicebook.models.Direccion;
import com.servicebook.models.Usuario;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void crearUsuario(String email, String nombre, Date fechaDeAlta, Role role, Boolean alta, String password, String password2, List<Direccion> direccion) throws MiException {

        validar(email, nombre, role, password, password2, direccion);

        Usuario usuario = new Usuario() {};
        
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setFechaDeAlta(fechaDeAlta);
        usuario.setRole(role);
        usuario.setAlta(alta);
        usuario.setPassword(password);
        usuario.setDireccion(direccion);
        usuarioRepository.save(usuario);

    }

    public void validar(String email, String nombre, Role role, String password, String password2, List<Direccion> direccion) throws MiException {

        if (email.isEmpty()) {
            throw new MiException("el email no puede ser nulo o estar vacio");
        }
        if (nombre.isEmpty()) {
            throw new MiException("el nombre no puede ser nulo o estar vacio");
        }
        if (!role.equals(role.USER)) {
            if (!role.equals(role.ADMIN)) {
                throw new MiException("el rol no puede ser nulo o estar vacio");
            }
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("la contraseña no puede estar vacia, y debe tener mas de 5 digitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("las contraseñas ingresadas deben ser iguales");
        }
        if (direccion.isEmpty()) {
            throw new MiException("la direccion no puede ser nulo o estar vacio");
        }
    }

    @Transactional
    public void modificarUsuario(Long id, String email, String nombre, Date fechaDeAlta, Role role, Boolean alta, String password, String password2, List<Direccion> direccion) throws MiException {

        Usuario usuario = new Usuario() {};
        
        Optional<Usuario> respuesta = usuarioRepository.findById(id);

        if (respuesta.isPresent()) {
            usuario = respuesta.get();
        }

        validar(email, nombre, role, password, password2, direccion);
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setFechaDeAlta(fechaDeAlta);
        usuario.setRole(role);
        usuario.setAlta(alta);
        usuario.setPassword(password);
        usuario.setDireccion(direccion);
        usuarioRepository.save(usuario);

    }

    @Transactional
    public void eliminarUsuario(Long id) {

        Optional<Usuario> respuesta = usuarioRepository.findById(id);
        if (respuesta.isPresent()) {
            usuarioRepository.deleteById(id);
        }
    }

    public void altaUsuario(Long id) {
        Usuario usuario = new Usuario() {};

        Optional<Usuario> respuesta = usuarioRepository.findById(id);

        if (respuesta.isPresent()) {
            usuario = respuesta.get();
            usuario.setAlta(Boolean.TRUE);
        }
    }

    @Transactional
    public void bajaUsuario(Long id) {
        Usuario usuario = new Usuario() {};

        Optional<Usuario> respuesta = usuarioRepository.findById(id);

        if (respuesta.isPresent()) {
            usuario = respuesta.get();
            usuario.setAlta(Boolean.FALSE);
        }
    }
    
    public List<Usuario> listarUsuarios() {
    List<Usuario> usuarios = new ArrayList();
    usuarios = usuarioRepository.findAll();
    return usuarios;
}
   
    public Usuario getOne(Long id) throws MiException {
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }else {
            throw new MiException("no existe");
        }

    }

    }
