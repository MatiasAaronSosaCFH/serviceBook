package com.servicebook.controller;

import com.servicebook.models.Admin;
import com.servicebook.service.ProfesionService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profesion")
public class ProfesionController {

  @Autowired
  private ProfesionService profesionService;

  @GetMapping("/registrar")
  public String crearProfesion(HttpSession session, ModelMap map) {

    Admin admin = (Admin) session.getAttribute("usuariosession");
    map.addAttribute("usuario", admin);
    return "profesion_registro.html";
  }

  @PostMapping("/registrar")
  public String crearProfesion(@RequestParam String nombre, RedirectAttributes redirectAttributes) {
    try {
      profesionService.crearProfesion(nombre);
      redirectAttributes.addFlashAttribute("exito", "Se registro la profesión correctamente!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Error: no se pudo registrar la profesión");
    }
    return "redirect:/profesion/registrar";

  }

}
