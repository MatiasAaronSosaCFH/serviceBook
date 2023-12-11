/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servicebook.controller;

import com.servicebook.exception.MiException;
import com.servicebook.models.Usuario;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.UsuarioRepository;
import com.servicebook.service.AdminService;
import com.servicebook.service.ClienteService;
import com.servicebook.service.ProveedorService;
import com.servicebook.service.UsuarioService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author SAMIR
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private UsuarioService usuarioService;
  @Autowired
  private ProveedorService proveedorService;
  @Autowired
  private AdminService adminService;
  @Autowired
  private ClienteService clienteService;

  @GetMapping("/crearUsuario")
  public String crearUsuario() {
    return "usuario_registro.html";
    /*-----Linkear a front----*/
  }

  @PostMapping("/crearUsuario")
  public String crearUsuario(@RequestParam String email, @RequestParam String nombre, @RequestParam("fechaDeAlta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDeAlta, @RequestParam Role role, @RequestParam Boolean alta, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {

    try {
      usuarioService.crearUsuario(email, nombre, fechaDeAlta, role, alta, password, password2);
      modelo.put("exito", "El Usuario fue registrado correctamente!");
    } catch (MiException ex) {
      modelo.addAttribute("roles", Role.values());
      modelo.put("error", ex.getMessage());
      modelo.put("email", email);
      modelo.put("nombre", nombre);
      modelo.put("password", password);
      modelo.put("password2", password2);
      return "usuario_registro.html";
      /*-----Linkear a front----*/
    }
    return "inicio.html";
  }

  @PostMapping("/modificarUsuario/{id}")
  public String modificarUsuario(@PathVariable Long id, @RequestParam String email, @RequestParam String nombre, @RequestParam("fechaDeAlta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDeAlta, @RequestParam Role role, @RequestParam Boolean alta, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {

    try {
      usuarioService.modificarUsuario(id, email, nombre, fechaDeAlta, role, alta, password, password2);
      modelo.put("exito", "El Usuario fue modificado correctamente!");
    } catch (MiException e) {

      modelo.put("error", e.getMessage());
      modelo.put("email", email);
      modelo.put("nombre", nombre);
      modelo.put("password", password);
      modelo.put("password2", password2);
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

  @PostMapping("/baja/{id}")
  public String baja(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {

    Usuario usuario = (Usuario) session.getAttribute("usuariosession");

    if (usuario != null) {
      if (usuario.getRole() == Role.USER) {
        clienteService.bajaCliente(id);
        redirectAttributes.addFlashAttribute("estado", "El cliente fue dado de baja");
      } else if (usuario.getRole() == Role.PROVEEDOR) {
        proveedorService.bajaProveedor(id);
        redirectAttributes.addFlashAttribute("estado", "El proveedor fue dado de baja");
      } else if (usuario.getRole() == Role.ADMIN) {
        adminService.bajaAdmin(id);
        redirectAttributes.addFlashAttribute("estado", "El administrador fue dado de baja");
      }
    }
    
    request.getSession().invalidate();

    return "redirect:/logout";

  }

  @GetMapping("/altaBajaUsuario/{id}")
  public void altaBajaUsuario(@PathVariable Long id, ModelMap modelo) {
    Optional<Usuario> respuesta = usuarioRepository.findById(id);
    if (respuesta.isPresent()) {

      Usuario usuario = respuesta.get();

      if (usuario.getAlta() == true) {
        usuarioService.baja(id);
        modelo.put("estado", "El usuario fue dado de baja");
      }
      if (usuario.getAlta() == false) {
        usuarioService.altaUsuario(id);
        modelo.put("estado", "El usuario fue dado de alta");
      }
    }

  }

}
