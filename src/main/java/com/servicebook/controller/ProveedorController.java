package com.servicebook.controller;

import com.servicebook.models.Proveedor;
import com.servicebook.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

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
}
