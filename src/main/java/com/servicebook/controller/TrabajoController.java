/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servicebook.controller;

import com.servicebook.service.ProfesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mathi
 */
@Controller
@RequestMapping("/trabajo")
public class TrabajoController {
    
    @Autowired
    private ProfesionService profesionService;
    
    
    
}
