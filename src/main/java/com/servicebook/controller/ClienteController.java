package com.servicebook.controller;

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
}
