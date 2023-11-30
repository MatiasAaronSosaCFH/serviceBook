package com.servicebook.controller;

import com.servicebook.exception.MiException;
import com.servicebook.models.Cliente;
import com.servicebook.models.dtos.ClienteDtoRecibido;
import com.servicebook.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/busqueda")
    public String buscarCliente(@RequestParam @NotBlank Long id, ModelMap model){

        Cliente cliente = clienteService.findById(id);
        ClienteDtoRecibido clienteEnviar = new ClienteDtoRecibido(cliente);
        return "index.html";
    }
    
    @GetMapping("/crearCliente")
    public String crearCliente() {
        return "usuario_registro.html";
        /*-----Linkear a front----*/
    }

    @PostMapping("/crearCliente")
    public String crearUsuario(@RequestParam String email, @RequestParam String nombre, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {

        try {
            clienteService.crearCliente(email, nombre, password, password2);
            modelo.put("exito", "El Usuario fue registrado correctamente!");
        } catch (MiException ex) {
           //modelo.put("error", ex.getMessage());
            modelo.put("email", email);
            modelo.put("nombre", nombre);
            modelo.put("password", password);
            modelo.put("password2", password2);
            return "usuario_registro.html";
            /*-----Linkear a front----*/
        }
        return "inicio.html";
    }
    
}
