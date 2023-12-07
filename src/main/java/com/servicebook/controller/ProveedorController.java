package com.servicebook.controller;

import com.servicebook.models.Cliente;
import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.ProfesionRepository;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

@Autowired
private ProveedorService proveedorService;
@Autowired
private ClienteService clienteService;
@Autowired
private ProfesionRepository profesionRepository;

    @GetMapping
    public String inicioIndex(ModelMap map){

        map.addAttribute("proveedores" , proveedorService.findByAlta());

        return "index.html";
    }

    @PostMapping
    public String guardarProveedor(@RequestParam String nombre,
                                   @RequestParam String email,
                                   @RequestParam String emailDeContacto,
                                   @RequestParam String numeroDeContacto,
                                   @RequestParam String password,
                                   ModelMap map){

        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nombre);
        proveedor.setEmail(email);
        proveedor.setEmailDeContacto(emailDeContacto);
        proveedor.setNumeroDeContacto(numeroDeContacto);
        proveedor.setPassword(password);

        proveedorService.save(proveedor);

        return "index.html";
    }
	 
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
@GetMapping("/registrar/{id}")
public String registrarProveedor(HttpSession session, ModelMap map) {

	Cliente usuario = (Cliente) session.getAttribute("usuariosession");
	map.addAttribute("usuario", usuario);
	map.addAttribute("roles", Role.values());
	List<Profesion> profesiones = profesionRepository.listarProfesiones();
	map.addAttribute("profesiones", profesiones);
	return "modificar_proveedor.html";
}	 

@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
  @PostMapping("/registrar/{id}")  //MultipartFile archivo, 
  public String registrarProveedor(@PathVariable Long id, @RequestParam String emailDeContacto, @RequestParam String numeroDeContacto, @RequestParam(required=false) List<Profesion> profesiones, @RequestParam String presentacion, @RequestParam Integer precioPorHora, @RequestParam Boolean disponible, ModelMap map, MultipartFile archivo) {

//	  Cliente usuario = (Cliente) session.getAttribute("usuariosession");
//	map.addAttribute("usuario", usuario);
	map.addAttribute("roles", Role.values());
	
	      Cliente cliente = clienteService.findById(id);

      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = attr.getRequest().getSession();
      session.setAttribute("usuariosession", cliente);

	try {
     proveedorService.crearProveedor(id, emailDeContacto, numeroDeContacto, profesiones, presentacion, precioPorHora, disponible, archivo);
      map.put("exito", "El Usuario fue registrado correctamente!");
		return "redirect: /inicio";
    } catch (Exception ex) {   //, archivo
      //modelo.put("error", ex.getMessage());
      map.put("emailDeContacto", emailDeContacto);
      map.put("numeroDeContacto", numeroDeContacto);
      map.put("presentacion", presentacion);
      map.put("precioPorHora", precioPorHora);
		map.put("disponible", disponible);
      return "modificar_proveedor.html";
      /*-----Linkear a front----*/
    }

  }


	 
}
