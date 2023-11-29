package com.servicebook.controller;

import com.servicebook.models.Trabajo;
import com.servicebook.service.TrabajoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/trabajo")
public class TrabajoController {

    @Autowired
    private TrabajoService trabajoService;

    @GetMapping("/lista")
    public String listar(ModelMap model) {
        //List<Trabajo> trabajos = trabajoService;
        //model.addAttribute("trabajos", trabajos);
        return "/lista";
    }

}
