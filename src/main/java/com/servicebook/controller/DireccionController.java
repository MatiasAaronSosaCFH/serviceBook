
package com.servicebook.controller;

import com.servicebook.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/direccion")
public class DireccionController {
  
  @Autowired
  private DireccionService direccionService;
  
}
