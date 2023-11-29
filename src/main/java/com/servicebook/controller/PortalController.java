package com.servicebook.controller;

import com.servicebook.models.Direccion;
import com.servicebook.models.dtos.ClienteDtoEnviado;
import com.servicebook.service.ClienteService;
import com.servicebook.service.DireccionService;
import com.servicebook.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import java.util.List;

@Controller
@RequestMapping("/")
public class PortalController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String dashboard(ModelMap map){

        map.addAttribute("proveedores" , proveedorService.findByAlta());
        return "index.html";
    }

    @GetMapping("/perfil")
    public String retornarPerfil(ModelMap model){

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //model.put(new ClienteDtoEnviado(clienteService.findById(authentication.getId())))
        return "perfil.html";
    }
}
