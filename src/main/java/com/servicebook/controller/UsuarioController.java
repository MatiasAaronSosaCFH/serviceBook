/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servicebook.controller;

import com.servicebook.exception.MiException;
import com.servicebook.models.Direccion;
import com.servicebook.models.Usuario;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.UsuarioRepositorio;
import com.servicebook.service.UsuarioService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author SAMIR
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/crearUsuario")
    public String crearUsuario() {
        return "usuario_registro.html";
        /*-----Linkear a front----*/
    }

    @PostMapping("/crearUsuario")
    public String crearUsuario(@RequestParam String email, @RequestParam String nombre, @RequestParam Date fechaDeAlta, @RequestParam Role role, @RequestParam Boolean alta, @RequestParam String password, @RequestParam String password2, @RequestParam List<Direccion> direccion, ModelMap modelo) {

        try {
            usuarioService.crearUsuario(email, nombre, fechaDeAlta, role, alta, password, password2, direccion);
            modelo.put("exito", "El Usuario fue registrado correctamente!");
        } catch (MiException e) {

            modelo.put("error", e.getMessage());
            modelo.put("email", email);
            modelo.put("nombre", nombre);
            modelo.put("password", password);
            modelo.put("password2", password2);
            modelo.put("direccion", direccion);

            return "usuario_registro.html";
            /*-----Linkear a front----*/
        }
        return "index.html";
    }

    @PostMapping("/modificarUsuario/{id}")
    public String modificarUsuario(@PathVariable Long id, @RequestParam String email, @RequestParam String nombre, @RequestParam Date fechaDeAlta, @RequestParam Role role, @RequestParam Boolean alta, @RequestParam String password, @RequestParam String password2, @RequestParam List<Direccion> direccion, ModelMap modelo) {

        try {
            usuarioService.modificarUsuario(id, email, nombre, fechaDeAlta, role, alta, password, password2, direccion);
            modelo.put("exito", "El Usuario fue modificado correctamente!");
        } catch (MiException e) {

            modelo.put("error", e.getMessage());
            modelo.put("email", email);
            modelo.put("nombre", nombre);
            modelo.put("password", password);
            modelo.put("password2", password2);
            modelo.put("direccion", direccion);

            return "usuario_modificar.html";
            /*-----Linkear a front----*/
        }
        return "index.html";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Long id, ModelMap modelo) {

        try {

            usuarioService.eliminarUsuario(id);
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            modelo.addAttribute("usuarios", usuarios);
            modelo.put("exito", "El Usuario fue eliminado correctamente!");
            return "redirect:../index.html";

        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return "index.html";
            /*-----Linkear a front----*/
        }
    }

    @GetMapping("/listarUsuarios")
    public String listarUsuarios(ModelMap modelo) {

        try {
            usuarioService.listarUsuarios();
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            modelo.addAttribute("usuarios", usuarios);
            return "usuario_lista.html";
            /*-----Linkear a front----*/

        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return "usuario_lista.html";
            /*-----Linkear a front----*/
        }
    }

    @GetMapping("/altaBajaUsuario/{id}")
    public void altaBajaUsuario(@PathVariable Long id, ModelMap modelo) {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();

            if (usuario.getAlta() == true) {
                usuarioService.bajaUsuario(id);
                modelo.put("estado", "El usuario fue dado de baja");
            }
            if (usuario.getAlta() == false) {
                usuarioService.altaUsuario(id);
                modelo.put("estado", "El usuario fue dado de alta");
            }
        }
        
    }

}
