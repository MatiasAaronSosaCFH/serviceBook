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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yaml.snakeyaml.events.Event;

@Controller
@RequestMapping("/direccion")
public class DireccionController {

  @Autowired
  private DireccionService direccionService;

  @Autowired
  private ClienteService clienteService;

  @GetMapping("/listado")
  public String listado(@RequestParam(required = false, defaultValue = "1") int page,
          @RequestParam(required = false, defaultValue = "10") int size,
          @RequestParam(required = false, defaultValue = "id") String sortBy,
          @RequestParam(required = false, defaultValue = "ASC") String sortOrder,
          ModelMap model) {

    Pageable paginacion = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

    Page<Direccion> direcciones = direccionService.listado(paginacion);

    model.addAttribute("direcciones", direcciones);

    return "nombre_archivo.html";

  }

  @GetMapping("/registrar")
  public String registrar() {

    return "direccion_registro.html";

  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR','ROLE_ADMIN')")
  @PostMapping("/registrar/{id}")
  public String registrar(@PathVariable Long id, @RequestParam String calle, @RequestParam String numero, @RequestParam String localidad,
          @RequestParam String provincia, HttpSession session, RedirectAttributes redirectAttributes) {

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
      direccionService.registrar(id, calle, numero, localidad, provincia);
      redirectAttributes.addFlashAttribute("exito", "La dirección fue guardada correctamente");
      return "redirect:/modificar";
    } catch (MiException ex) {
      redirectAttributes.addFlashAttribute("errorDireccionRegistrar", ex.getMessage());
      redirectAttributes.addFlashAttribute("provincia", provincia);
      redirectAttributes.addFlashAttribute("localidad", localidad);
      redirectAttributes.addFlashAttribute("calle", calle);
      redirectAttributes.addFlashAttribute("id", id);
      redirectAttributes.addFlashAttribute("numero", numero);
      return "redirect:/modificar";
    }

  }

  @PostMapping("/eliminar/{idCliente}/{idDireccion}")
  public String bajaDireccion(@PathVariable Long idCliente, @PathVariable Long idDireccion ,RedirectAttributes redirectAttributes, HttpSession session) {

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
      direccionService.eliminarPorCliente(idCliente, idDireccion);
      redirectAttributes.addFlashAttribute("exito", "La dirección fue eliminada correctamente");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorDireccion", e.getMessage());
    }

    return "redirect:/modificar";

  }

  @PostMapping("/modificar/{id}")
  public String modificar(@PathVariable Long id, @RequestParam String localidad, @RequestParam String provincia, @RequestParam String calle , @RequestParam String numero, RedirectAttributes redirectAttributes, HttpSession session) {

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
      direccionService.modificar(id, calle, numero, localidad, provincia);
      redirectAttributes.addFlashAttribute("exito", "La dirección fue modificada correctamente");
    } catch (MiException ex) {
      redirectAttributes.addFlashAttribute("errorDireccionModificar", ex.getMessage());
      redirectAttributes.addFlashAttribute("provincia", provincia);
      redirectAttributes.addFlashAttribute("localidad", localidad);
      redirectAttributes.addFlashAttribute("calle", calle);
      redirectAttributes.addFlashAttribute("id", id);
      redirectAttributes.addFlashAttribute("numero", numero);
      return "redirect:/modificar";
    }

    return "redirect:/modificar";

  }
  
}
