package com.servicebook.controller;

import com.servicebook.models.Cliente;
import com.servicebook.models.Trabajo;
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
	 
    @GetMapping("/lista")
    public String listar(ModelMap model) {
        //List<Trabajo> trabajos = trabajoService;
        //model.addAttribute("trabajos", trabajos);
        return "/lista";
    }

@GetMapping("Cliente/{id}")
public String trabajoCliente(@PathVariable Long id, HttpSession session, ModelMap modelo){
	 modelo.put("usuario", session.getAttribute("usuariosession"));
	  List<Trabajo> trabajos = trabajoRepository.buscarTrabajoPorCliente(id);
	modelo.addAttribute("trabajos", trabajos);
	return "trabajo_list.html";
	
}

@GetMapping("Proveedor/{id}")
public String trabajoProveedor(@PathVariable Long id, HttpSession session, ModelMap modelo){
	 modelo.put("usuario", session.getAttribute("usuariosession"));
	  List<Trabajo> trabajos = trabajoRepository.buscarTrabajoPorProveedor(id);
	modelo.addAttribute("trabajos", trabajos);
	return "trabajo_list.html";
	
}

//revisar para crear trabajo.
@GetMapping("crearTrabajo")
public String crearTrabajo(HttpSession session, ModelMap modelo){
	 modelo.put("usuario", session.getAttribute("usuariosession"));
	 Cliente usuario = (Cliente) session.getAttribute("usuariosession");
	  List<Trabajo> trabajos = trabajoRepository.buscarTrabajoPorCliente(usuario.getId());   //rervisar si funciona el id
	modelo.addAttribute("trabajos", trabajos);
	return "cliente_vista.html";

}

@PostMapping("crearTrabajo/{idProveedor}")
public String crearTrabajo(@RequestParam Long idProveedor, @RequestParam Long idCliente, @RequestParam String titullTrabajo, @RequestParam String descripcionTrabajo, HttpSession session, ModelMap modelo){
	 modelo.put("usuario", session.getAttribute("usuariosession"));
	 Cliente usuario = (Cliente) session.getAttribute("usuariosession");
	  List<Trabajo> trabajos = trabajoRepository.buscarTrabajoPorCliente(usuario.getId());
	modelo.addAttribute("trabajos", trabajos);
	return "cliente_vista.html";

}


}
