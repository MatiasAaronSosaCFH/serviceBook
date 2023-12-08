package com.servicebook.controller;

import com.servicebook.exception.MiException;
import com.servicebook.models.Cliente;
import com.servicebook.models.Direccion;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Usuario;
import com.servicebook.models.dtos.ClienteDtoEnviado;
import com.servicebook.models.enums.Role;
import com.servicebook.service.ClienteService;
import com.servicebook.service.DireccionService;
import com.servicebook.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class PortalController {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ProveedorService proveedorService;
  
  @Autowired
  private DireccionService direccionService;

  @GetMapping
  public String dashboard(ModelMap map) {

    map.addAttribute("proveedores", proveedorService.findByAlta());
    return "index.html";
  }

  @GetMapping("/perfil")
  public String retornarPerfil(ModelMap model) {

    //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //model.put(new ClienteDtoEnviado(clienteService.findById(authentication.getId())))
    return "perfil.html";
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR','ROLE_ADMIN')")
  @GetMapping("/inicio")
  public String inicio(HttpSession session, ModelMap model) {
    Usuario usuario = (Usuario) session.getAttribute("usuariosession");
    if (usuario != null) {
      if (usuario.getRole() == Role.USER) {
        Long clienteId = usuario.getId(); 
        ClienteDtoEnviado clienteDto = clienteService.obtenerClienteConDirecciones(clienteId);
        if (clienteDto != null) {
          model.addAttribute("usuario", clienteDto);
        }
      } else if (usuario.getRole() == Role.PROVEEDOR) {
        Proveedor proveedor = (Proveedor) session.getAttribute("usuariosession");
        model.addAttribute("usuario", proveedor);
      } else if (usuario.getRole() == Role.ADMIN) {
        Cliente admin = (Cliente) session.getAttribute("usuariosession");
        model.addAttribute("usuario", admin);
      }
    }

    model.addAttribute("proveedores", proveedorService.findByAlta());
    return "inicio.html";
  }

  @GetMapping("/template")
  public String template(ModelMap map) {

    map.addAttribute("proveedores", proveedorService.findByAlta());
    return "template.html";
  }

  @GetMapping("/login")
  public String login(@RequestParam(required = false) String error, ModelMap model) {

//        if(error != null){
//        
//          model.put("error", "Usuario o Contraseña invalidos!");
//          
//        }
    return "login.html";

  }

  @GetMapping("/registrar")
  public String registrar() {
    return "registro.html";
  }

//  @PostMapping("/registrar")
//  public String registrar(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
//          @RequestParam String password2, ModelMap model) {
//
//    try {
//
//      clienteService.crearCliente(email, nombre, password, password2);
//
//      //model.put("exito", "Usuario registrado exitosamente!");
//      return "redirect:/login";
//
//    } catch (MiException ex) {
//
//      //model.put("error", ex.getMessage());
//      return "registro.html";
//
//    }
//
//  }
  @PostMapping("/registrar")
  public String registrar(@RequestParam String email, @RequestParam String nombre,
          @RequestParam String password, @RequestParam String password2, RedirectAttributes redirectAttributes) {

    try {
      clienteService.crearCliente(email, nombre, password, password2);
      redirectAttributes.addFlashAttribute("exito", "El usuario fue registrado exitosamente!");
    } catch (MiException ex) {
      // Si hay un error, redirige con los atributos flash
      redirectAttributes.addFlashAttribute("error", ex.getMessage());
      redirectAttributes.addFlashAttribute("email", email);
      redirectAttributes.addFlashAttribute("nombre", nombre);
      redirectAttributes.addFlashAttribute("password", password);
      redirectAttributes.addFlashAttribute("password2", password2);
      return "redirect:/";
    }

    return "redirect:/";
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR','ROLE_ADMIN')")
  @GetMapping("/modificar")
  public String modificar(HttpSession session, ModelMap model) {
    Usuario usuario = (Usuario) session.getAttribute("usuariosession");

    if (usuario != null) {
      if (usuario.getRole() == Role.USER) {
        Long clienteId = usuario.getId(); 
        ClienteDtoEnviado clienteDto = clienteService.obtenerClienteConDirecciones(clienteId);
        if (clienteDto != null) {
          model.addAttribute("usuario", clienteDto);
        }
      } else if (usuario.getRole() == Role.PROVEEDOR) {
        Proveedor proveedor = (Proveedor) session.getAttribute("usuariosession");
        model.addAttribute("usuario", proveedor);
      } else if (usuario.getRole() == Role.ADMIN) {
        Cliente admin = (Cliente) session.getAttribute("usuariosession");
        model.addAttribute("usuario", admin);
      }
    }

    return "modificar.html";

  }
 
}
