package com.servicebook.controller;

import com.servicebook.models.Cliente;
import com.servicebook.models.Mensaje;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Trabajo;
import com.servicebook.models.Usuario;
import com.servicebook.models.enums.Role;
import com.servicebook.models.enums.TipoMensaje;
import com.servicebook.repository.MensajeRepository;
import com.servicebook.repository.ProveedorRepository;
import com.servicebook.repository.TrabajoRepository;
import com.servicebook.service.TrabajoService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/trabajo")
public class TrabajoController {

  @Autowired
  private TrabajoService trabajoService;
  @Autowired
  private TrabajoRepository trabajoRepository;
  @Autowired
  private ProveedorRepository proveedorRepository;
  @Autowired
  private MensajeRepository mensajeRepository;

  @GetMapping("/lista")
  public String listar(ModelMap model) {
    //List<Trabajo> trabajos = trabajoService;
    //model.addAttribute("trabajos", trabajos);
    return "/lista";
  }

  @GetMapping("/Cliente/{id}")
  public String trabajoCliente(@PathVariable Long id, HttpSession session, ModelMap modelo) {
    modelo.put("usuario", session.getAttribute("usuariosession"));
    List<Trabajo> trabajos = trabajoRepository.buscarTrabajoPorCliente(id);
    modelo.addAttribute("trabajos", trabajos);
    return "trabajo_list.html";

  }

  @GetMapping("/Proveedor/{id}")
  public String trabajoProveedor(@PathVariable Long id, HttpSession session, ModelMap modelo) {
    modelo.put("usuario", session.getAttribute("usuariosession"));
    List<Trabajo> trabajos = trabajoRepository.buscarTrabajoPorProveedor(id);
    modelo.addAttribute("trabajos", trabajos);
    return "trabajo_list.html";

  }

//revisar para crear trabajo.
  @GetMapping("/listaCliente")
  public String crearTrabajo(HttpSession session, ModelMap modelo) {
    Usuario usuario = (Usuario) session.getAttribute("usuariosession");
    modelo.put("usuario", usuario);
//    Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);
//    modelo.put("proveedor", proveedor);
    List<Trabajo> trabajosCliente = trabajoRepository.buscarTrabajoPorCliente(usuario.getId());
    modelo.addAttribute("trabajos", trabajosCliente);
    return "cliente_vista.html";

  }

  @PostMapping("/listaCliente")
  public String ver(@RequestParam Long idProveedor, @RequestParam Long idTrabajo, HttpSession session, ModelMap modelo) {
    Usuario usuario = (Usuario) session.getAttribute("usuariosession");
    modelo.put("usuario", usuario);

    Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);
    modelo.put("proveedor", proveedor);
    List<Trabajo> trabajosCliente = trabajoRepository.buscarTrabajoPorCliente(usuario.getId());
    modelo.addAttribute("trabajos", trabajosCliente);
    
    Trabajo trabajo = trabajoRepository.findById(idTrabajo).orElse(null);
    modelo.addAttribute("trabajo", trabajo);
    
   List<Mensaje> mensajes = mensajeRepository.buscarMensajesPorTrabajo(idTrabajo);
   modelo.addAttribute("mensajes", mensajes);
    
    modelo.addAttribute("verificacion", "verificado");
    
    return "cliente_vista.html";
  }

  @PostMapping("/crearTrabajo")
  public String crearTrabajo(@RequestParam Long idProveedor, @RequestParam Long idCliente, @RequestParam String tituloTrabajo, @RequestParam(required = false) String descripcionTrabajo, HttpSession session, ModelMap modelo) {
    trabajoService.crearTrabajo(idCliente, idProveedor, tituloTrabajo, descripcionTrabajo);
    modelo.put("usuario", session.getAttribute("usuariosession"));
    Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);
    modelo.put("proveedor", proveedor);
    List<Trabajo> trabajosCliente = trabajoRepository.buscarTrabajoPorCliente(idCliente);
    modelo.addAttribute("trabajos", trabajosCliente);
//    List<Trabajo> trabajosProveedor = trabajoRepository.buscarTrabajoPorProveedor(idProveedor);
//    modelo.addAttribute("trabajosProveedor", trabajosProveedor);
    return "cliente_vista.html";
  }
  
  @PostMapping("/enviarMensaje")
  public String enviarMensaje(@RequestParam Long idProveedor, @RequestParam Long idTrabajo, @RequestParam String mensaje, HttpSession session, ModelMap modelo) {
    Usuario usuario = (Usuario) session.getAttribute("usuariosession");
    modelo.put("usuario", usuario);

    Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);
    modelo.put("proveedor", proveedor);
    List<Trabajo> trabajosCliente = trabajoRepository.buscarTrabajoPorCliente(usuario.getId());
    modelo.addAttribute("trabajos", trabajosCliente);
    
    Trabajo trabajo = trabajoRepository.findById(idTrabajo).orElse(null);
    modelo.addAttribute("trabajo", trabajo);
    
   List<Mensaje> mensajes = mensajeRepository.buscarMensajesPorTrabajo(idTrabajo);
   modelo.addAttribute("mensajes", mensajes);
    
    modelo.addAttribute("verificacion", "verificado");
    
    if(usuario.getRole().equals(Role.USER)){
      trabajoService.enviarMensaje(idTrabajo, mensaje, TipoMensaje.CLIENTE);
    } else {
      trabajoService.enviarMensaje(idTrabajo, mensaje, TipoMensaje.PROVEEDOR);
    }
    
    return "cliente_vista.html";
  }

//  @PostMapping("/refresh")
//  public String refrescar(ModelMap modelo, HttpSession session, @RequestParam Long idProveedor, @RequestParam Long idTrabajo, @RequestParam String mensaje){
//  
//    Usuario usuario = (Usuario) session.getAttribute("usuariosession");
//    modelo.put("usuario", usuario);
//
//    Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);
//    modelo.put("proveedor", proveedor);
//    List<Trabajo> trabajosCliente = trabajoRepository.buscarTrabajoPorCliente(usuario.getId());
//    modelo.addAttribute("trabajos", trabajosCliente);
//    
//    Trabajo trabajo = trabajoRepository.findById(idTrabajo).orElse(null);
//    modelo.addAttribute("trabajo", trabajo);
//    
//   List<Mensaje> mensajes = mensajeRepository.buscarMensajesPorTrabajo(idTrabajo);
//   modelo.addAttribute("mensajes", mensajes);
//    
//    modelo.addAttribute("verificacion", "verificado");
//    
//    
//    return "cliente_vista.html";
//  
//  }
  
}
