
package com.servicebook.controller;

import com.servicebook.exception.MiException;
import com.servicebook.models.Direccion;
import com.servicebook.service.DireccionService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
  public String listado(@RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int size,
                        @RequestParam(required = false, defaultValue = "id") String sortBy,
                        @RequestParam(required = false, defaultValue = "ASC") String sortOrder,
                        ModelMap model){

    Pageable paginacion = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

    Page<Direccion> direcciones = direccionService.listado(paginacion);
    
    model.addAttribute("direcciones", direcciones);
  
    return "direccion_registro.html";
    
  }
  
  @GetMapping("/registrar")
  public String registrar(){
  
    return "nombre_archivo.html";
    
  }
  
  @PostMapping("/registrar")
  public String registrar(@RequestParam String calle, @RequestParam String numero, @RequestParam String localidad, 
                          @RequestParam String provincia){
  
    try {
      direccionService.registrar(calle, numero, localidad, provincia);
    } catch (MiException ex) {
      Logger.getLogger(DireccionController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return "nombre_archivo.html";
  
  }
  
  
  
  
  
  
}
