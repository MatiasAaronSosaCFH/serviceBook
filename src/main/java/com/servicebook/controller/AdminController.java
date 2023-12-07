
package com.servicebook.controller;

import com.servicebook.exception.MiException;
import com.servicebook.models.Cliente;
import com.servicebook.models.Proveedor;
import com.servicebook.models.enums.Role;
import com.servicebook.service.ClienteService;
import com.servicebook.service.ProveedorService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
@Autowired
ClienteService clienteService;
@Autowired
ProveedorService proveedorService;

@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN', 'ROLE_PERIODISTA')")
@GetMapping("/proveedor/lista")
public String panelAdminListaNoticias(ModelMap modelo, HttpSession session){
	List<Cliente> clientes = clienteService.findAll();
	modelo.addAttribute("clientes", clientes);
	List<Proveedor> proveedores = proveedorService.findByAlta();
	modelo.addAttribute("proveedores", proveedores);
	return "proveedor_list.html";
}

@PreAuthorize("hasRole('ROLE_ADMIN')")
@GetMapping("/cliente/lista")
public String listaPeriodistas(ModelMap modelo, HttpSession session) {
	Cliente usuario = (Cliente) session.getAttribute("usuariosession");
	modelo.put("usuario", usuario);
	List<Cliente> clientes = clienteService.findAll();
	modelo.addAttribute("clientes", clientes);
	return "cliente_list.html";
}

	@GetMapping("/cambiarEstado/{id}")
	public String cambiarEstado(@PathVariable Long id, ModelMap modelo, HttpSession session){
		Cliente usuario = (Cliente) session.getAttribute("usuariosession");
		modelo.put("usuario", usuario);	
		
		modelo.put("cliente", clienteService.findById(id));
		clienteService.cambiarEstado(id);
			List<Cliente> clientes = clienteService.findAll();
			modelo.addAttribute("clientes", clientes);		
		return "cliente_list.html";
	}
        
	@GetMapping("/rotarRol/{id}")
	public String rotarRol(@PathVariable Long id, ModelMap modelo, HttpSession session){
		Cliente usuario = (Cliente) session.getAttribute("usuariosession");
		modelo.put("usuario", usuario);
		
		
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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/perfil/{id}")
	public String modificarPerfil(@PathVariable Long id, ModelMap modelo, HttpSession session){
		Cliente usuario = (Cliente) session.getAttribute("usuariosession");
		modelo.put("usuario", usuario);
		Cliente cliente = clienteService.findById(id);
		modelo.put("cliente", cliente);
		modelo.addAttribute("roles", Role.values() );
//		return "cliente_modificar.html";
		return "perfil.html";
	}	 
	 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/perfil/{id}")
	public String modificarPerfil(@PathVariable Long id, @RequestParam String email,@RequestParam String nombre,@RequestParam String password, @RequestParam String password2, ModelMap modelo){
		
		try {
			Cliente cliente = clienteService.findById(id);
			modelo.put("cliente", cliente);
			clienteService.modificarCliente(id, email, nombre, password, password2);
			modelo.put("exito", "Usuario actualizado correctamente");
			return "redirect:/admin/cliente/lista";
			
		} catch (MiException ex) {
			Cliente cliente = clienteService.findById(id);
			modelo.put("cliente", cliente);
			modelo.addAttribute("roles", Role.values() );
//			modelo.put("error", ex.getMessage());
//			return "cliente_modificar.html";		
			return "perfil.html";
		}
	}	
	
}
