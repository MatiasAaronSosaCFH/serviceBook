package com.servicebook.controller;

import com.servicebook.models.Cliente;
import com.servicebook.models.dtos.ClienteDto;
import com.servicebook.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/busqueda")
    public String buscarCliente(@RequestParam @Valid Long id, ModelMap model){

        Cliente cliente = clienteService.findById(id);
        ClienteDto clienteEnviar = new ClienteDto(cliente);
        return "index.html";
    }
}
