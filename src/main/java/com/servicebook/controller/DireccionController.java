
package com.servicebook.controller;

import com.servicebook.models.Direccion;
import com.servicebook.service.DireccionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/direccion")
public class DireccionController {
  
  @Autowired
  private DireccionService direccionService;
  
  @GetMapping("/listado")
  public String listado(ModelMap model){
  
    List<Direccion> direcciones = direccionService.listado();
    
    model.addAttribute("direcciones", direcciones);
  
    return "nombre_archivo.html";
    
  }
  
  @GetMapping("/registrar")
  public String registrar(){
  
    return "nombre_archivo.html";
    
  }
  
  @PostMapping("/registrar")
  public String registrar(@RequestParam String calle, @RequestParam String numero, @RequestParam String localidad, 
                          @RequestParam String provincia){
  
    direccionService.registrar(calle, numero, localidad, provincia);
    
    return "nombre_archivo.html";
  
  }
  
  
  
  
  
  
}
