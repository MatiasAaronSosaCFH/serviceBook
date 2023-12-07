package com.servicebook.controller;

import com.servicebook.exception.MiException;
import com.servicebook.models.Cliente;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Usuario;
import com.servicebook.models.dtos.ClienteDtoEnviado;
import com.servicebook.models.enums.Role;
import com.servicebook.service.ClienteService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.constraints.NotBlank;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  ClienteService clienteService;

  @GetMapping("/busqueda")
  public String buscarCliente(@RequestParam @NotBlank Long id, ModelMap model) {

    Cliente cliente = clienteService.findById(id);
    //ClienteDtoRecibido clienteEnviar = new ClienteDtoRecibido(cliente);
    return "index.html";
  }

  @PostMapping("/crearCliente")
  public String crearUsuario(@RequestParam String email, @RequestParam String nombre, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {

    try {
      clienteService.crearCliente(email, nombre, password, password2);
      modelo.put("exito", "El Usuario fue registrado correctamente!");
    } catch (MiException ex) {
      //modelo.put("error", ex.getMessage());
      modelo.put("email", email);
      modelo.put("nombre", nombre);
      modelo.put("password", password);
      modelo.put("password2", password2);
      return "usuario_registro.html";
      /*-----Linkear a front----*/
    }
    return "inicio.html";
  }

//  @PostMapping("/modificar/{id}")
//  public String modificar(@PathVariable Long id, @RequestParam(required = false) String email, @RequestParam String nombre, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {
//
//    try {
//      clienteService.modificarCliente(id, email, nombre, password, password2);
//
//      Cliente cliente = clienteService.findById(id);
//
//      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//
//      HttpSession session = attr.getRequest().getSession();
//
//      session.setAttribute("usuariosession", cliente);
//
//      modelo.put("exito", "El cliente fue modificado correctamente!");
//    } catch (Exception e) {
//
//      modelo.put("error", e.getMessage());
//      modelo.put("email", email);
//      modelo.put("nombre", nombre);
//      modelo.put("password", password);
//      modelo.put("id", id);
//      return "perfil.html";
//    }
//    return "redirect:/inicio";
//  }
  
  @PostMapping("/modificar/{id}")
  public String modificar(@PathVariable Long id, @ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes, HttpSession session) {
    
    Usuario usuario = (Usuario) session.getAttribute("usuariosession");

    if (usuario != null) {
      if (usuario.getRole() == Role.USER) {
        Long clienteId = usuario.getId();
        ClienteDtoEnviado clienteDto = clienteService.obtenerClienteConDirecciones(clienteId);
        if (clienteDto != null) {
          redirectAttributes.addFlashAttribute("usuario", clienteDto);
        }
      } else if (usuario.getRole() == Role.PROVEEDOR) {
        Proveedor proveedor = (Proveedor) session.getAttribute("usuariosession");
        redirectAttributes.addFlashAttribute("usuario", proveedor);
      } else if (usuario.getRole() == Role.ADMIN) {
        Cliente admin = (Cliente) session.getAttribute("usuariosession");
        redirectAttributes.addFlashAttribute("usuario", admin);
      }
    }

    try {
      clienteService.modificar(id, cliente);
      redirectAttributes.addFlashAttribute("exito", "Cliente modificado correctamente");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
      return "redirect:/modificar";
    }
    return "redirect:/modificar";
  }

  @GetMapping("/modificar/{id}")
  public String modificar(@PathVariable Long id, ModelMap model) {

    Cliente cliente = clienteService.findById(id);
    model.addAttribute("cliente", cliente);
    return "perfil.html";

  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
  @GetMapping("/perfil")
  public String perfil(HttpSession session, ModelMap model) {

    Cliente cliente = (Cliente) session.getAttribute("usuariosession");

    model.addAttribute("cliente", cliente);

    return "perfil.html";
  }

  @PostMapping("/bajaCliente/{id}")
  public String bajaCliente(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpServletRequest request) {

    clienteService.bajaCliente(id);

    redirectAttributes.addFlashAttribute("estado", "El cliente fue dado de baja");

    request.getSession().invalidate();

    return "redirect:/logout";

  }
  

}
