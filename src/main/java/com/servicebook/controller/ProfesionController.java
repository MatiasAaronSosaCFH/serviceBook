package com.servicebook.controller;

import com.servicebook.service.ProfesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profesion")
public class ProfesionController {

    @Autowired
    private ProfesionService profesionService;
}
