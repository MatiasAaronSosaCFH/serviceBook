package com.servicebook.controller;

import com.servicebook.models.Cliente;
import com.servicebook.service.ProfesionService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profesion")
public class ProfesionController {

    @Autowired
    private ProfesionService profesionService;

	 @GetMapping("/registrar")
	public String crearProfesion(HttpSession session, ModelMap map){
		
		Cliente usuario = (Cliente) session.getAttribute("usuariosession");
		map.addAttribute("usuario", usuario);
		return "profesion_registro.html";
	}
	
	 @PostMapping("/registrar")
	public String crearProfesion(@RequestParam String nombre){
		profesionService.crearProfesion(nombre);
		return "redirect:registrar";
	}	
		 
	 
}
