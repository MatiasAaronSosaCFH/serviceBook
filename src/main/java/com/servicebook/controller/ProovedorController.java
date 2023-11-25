package com.servicebook.controller;

import com.servicebook.service.FotoService;
import com.servicebook.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proveedor")
public class ProovedorController {

    @Autowired
    private ProveedorService proveedorService;
}
