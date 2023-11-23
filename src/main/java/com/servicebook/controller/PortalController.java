package com.servicebook.controller;

import com.servicebook.models.Direccion;
import com.servicebook.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import java.util.List;

@Controller
@RequestMapping("/")
public class PortalController {

    public String dashboard(){
        return "index.html";
    }


}
