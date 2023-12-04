package com.servicebook.controller;

import com.servicebook.models.dtos.CalificacionDtoRecibido;
import com.servicebook.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calificacion")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;
}
