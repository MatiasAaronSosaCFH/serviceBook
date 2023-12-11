package com.servicebook.controller;

import com.servicebook.exception.MiException;
import com.servicebook.models.Admin;
import com.servicebook.models.Cliente;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Usuario;
import com.servicebook.models.dtos.AdminDtoEnviado;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.ProveedorRepository;
import com.servicebook.service.AdminService;
import com.servicebook.service.ClienteService;
import com.servicebook.service.ProveedorService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  ClienteService clienteService;
  @Autowired
  ProveedorService proveedorService;
  @Autowired
  ProveedorRepository proveedorRepository;
  @Autowired
  private AdminService adminService;

  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN', 'ROLE_PERIODISTA')")
  @GetMapping("/proveedor/lista")
  public String panelAdminListaNoticias(ModelMap modelo, HttpSession session) {
    modelo.put("usuario", session.getAttribute("usuariosession"));
    modelo.addAttribute("proveedores", proveedorRepository.listarProveedoresTodo());

    return "proveedor_list.html";
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/cliente/lista")
  public String listaPeriodistas(ModelMap modelo, HttpSession session) {
    modelo.put("usuario", session.getAttribute("usuariosession"));
    List<Cliente> clientes = clienteService.findAll();
    modelo.addAttribute("clientes", clientes);
    return "cliente_list.html";
  }

  @GetMapping("/cambiarEstado/{id}")
  public String cambiarEstado(@PathVariable Long id, ModelMap modelo, HttpSession session) {
    modelo.put("usuario", session.getAttribute("usuariosession"));

    modelo.put("cliente", clienteService.findById(id));
    clienteService.cambiarEstado(id);
    List<Cliente> clientes = clienteService.findAll();
    modelo.addAttribute("clientes", clientes);
    return "cliente_list.html";
  }

  @GetMapping("/rotarRol/{id}")
  public String rotarRol(@PathVariable Long id, ModelMap modelo, HttpSession session) {
    modelo.put("usuario", session.getAttribute("usuariosession"));
    modelo.put("cliente", clienteService.findById(id));
    clienteService.rotarRol(id);
    Role role = clienteService.findById(id).getRole();
    if (role.equals(role.USER)) {
      List<Cliente> clientes = clienteService.findAll();
      modelo.addAttribute("clientes", clientes);
    }
    if (role.equals(role.PROVEEDOR)) {
      List<Cliente> clientes = clienteService.findAll();
      modelo.addAttribute("clientes", clientes);
    }
    if (role.equals(role.ADMIN)) {
      List<Cliente> clientes = clienteService.findAll();
      modelo.addAttribute("clientes", clientes);
    }
    return "cliente_list.html";
  }

  @GetMapping("/proveedorEstado/{id}")
  public String proveedorCambiarEstado(@PathVariable Long id, ModelMap modelo, HttpSession session) {
    modelo.put("usuario", session.getAttribute("usuariosession"));

    modelo.put("proveedor", proveedorRepository.findById(id));
    proveedorService.cambiarEstado(id);
    List<Proveedor> proveedores = proveedorRepository.listarProveedoresTodo();
    modelo.addAttribute("proveedores", proveedores);
    //modelo.addAttribute("proveedores", proveedorRepository.listarProveedoresTodo());
    return "proveedor_list.html";
  }

  @GetMapping("/proveedorDisponible/{id}")
  public String proveedorDisponible(@PathVariable Long id, ModelMap modelo, HttpSession session) {
    modelo.put("usuario", session.getAttribute("usuariosession"));

    modelo.put("proveedor", proveedorService.findById(id));
    proveedorService.cambiarDisponible(id);
    modelo.addAttribute("proveedores", proveedorRepository.listarProveedoresTodo());
    return "proveedor_list.html";
  }

  @GetMapping("/proveedorRotarRol/{id}")
  public String proveedorRotarRol(@PathVariable Long id, ModelMap modelo, HttpSession session) {
    modelo.put("usuario", session.getAttribute("usuariosession"));
    modelo.put("proveedor", proveedorRepository.findById(id));
    proveedorService.rotarRol(id);
    modelo.addAttribute("proveedores", proveedorRepository.listarProveedoresTodo());
    return "proveedor_list.html";
  }

  @GetMapping("/proveedorAprobar/{id}")
  public String proveedorAprobar(@PathVariable Long id, ModelMap modelo, HttpSession session) {
    modelo.put("usuario", session.getAttribute("usuariosession"));
    modelo.put("proveedor", proveedorRepository.findById(id));
    proveedorService.aprobar(id);
    modelo.addAttribute("proveedores", proveedorRepository.listarProveedoresTodo());
    return "proveedor_list.html";
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/perfil/{id}")
  public String modificarPerfil(@PathVariable Long id, ModelMap modelo, HttpSession session) {
    modelo.put("usuario", session.getAttribute("usuariosession"));
    Cliente cliente = clienteService.findById(id);
    modelo.put("cliente", cliente);
    modelo.addAttribute("roles", Role.values());
//		return "cliente_modificar.html";
    return "perfil.html";
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/perfil/{id}")
  public String modificarPerfil(@PathVariable Long id, @RequestParam String email, @RequestParam String nombre, @RequestParam String password, @RequestParam String password2, ModelMap modelo, HttpSession session) {

    try {
      Cliente cliente = clienteService.findById(id);
      modelo.put("cliente", cliente);
      clienteService.modificarCliente(id, email, nombre, password, password2);
      modelo.put("exito", "Usuario actualizado correctamente");
      return "redirect:/admin/cliente/lista";

    } catch (MiException ex) {
      modelo.put("usuario", session.getAttribute("usuariosession"));
      Cliente cliente = clienteService.findById(id);
      modelo.put("cliente", cliente);
      modelo.addAttribute("roles", Role.values());
//			modelo.put("error", ex.getMessage());
//			return "cliente_modificar.html";		
      return "perfil.html";
    }
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @PostMapping("/modificar/{id}")
  public String modificar(@PathVariable Long id, @ModelAttribute Admin admin, RedirectAttributes redirectAttributes, HttpSession session, ModelMap model) {

    try {
      adminService.modificar(id, admin);
      Usuario usuario = (Usuario) session.getAttribute("usuariosession");

      if (usuario != null) {
        Long adminId = usuario.getId();
        AdminDtoEnviado adminDto = adminService.obtenerDtoAdmin(adminId);
          System.out.println(adminDto.nombre() + adminDto.email());
          model.addAttribute("usuario", adminDto);
      }
      model.addAttribute("exito", "Admin modificado correctamente");
    } catch (MiException ex) {
      model.addAttribute("errorBasica", ex.getMessage());
      model.addAttribute("nombre", admin.getNombre());
      model.addAttribute("email", admin.getEmail());
      return "modificar.html";
    }
    return "modificar.html";
  }

}
