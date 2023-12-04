package com.servicebook.controller;

import org.springframework.web.bind.annotation.PathVariable;
import com.servicebook.exception.MiException;
import com.servicebook.models.enums.Estrellas;
import com.servicebook.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calificacion")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;
    
    @GetMapping("/crearCalifcacion/{id}")
    public String crearCalifcacion(){
        return "calificacion.html";
    }
    
    @PostMapping("/crearCalifcacion/{id}")
    public String crearCalifcacion(@PathVariable Estrellas estrellas ,
              @RequestParam String descripcion , ModelMap modelo){
        try{
            calificacionService.calificar(estrellas , descripcion);
            modelo.put("exito", "Su calificacion fue enviada");
        } catch (MiException ex){
            modelo.addAttribute("estrellas", Estrellas.values());
             modelo.put("error", ex.getMessage());
            modelo.put("descripcion", descripcion);
            return "calificacion.html";
            
        }
                  
            return "inicio.html";
   
    }
    
    @GetMapping("/modificarCalifcacion/{id}")
    public String modificarCalifcacion(){
        return "modificarCalifcacion.html";
    }
    
    /*@PostMapping("/modificarCalifcacion/{id}")
    public String modificarCalifcacion(@PathVariable Long id, @RequestParam String descripcion ,
            @PathVariable Estrellas estrella ){
       
        //calificacionService.modificarCalificacion(descripcion,estrella,id);
        
       
        
        return "modificarCalifcacion.html";
    }*/
    
}
