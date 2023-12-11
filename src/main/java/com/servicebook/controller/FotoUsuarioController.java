/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.controller;

/**
 *
 * @author SantiagoPaguaga
 */
import com.servicebook.models.Admin;
import com.servicebook.models.Cliente;
import com.servicebook.models.FotoUsuario;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Usuario;
import com.servicebook.models.dtos.ClienteDtoEnviado;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.AdminRepository;
import com.servicebook.repository.ClienteRepository;
import com.servicebook.repository.ProveedorRepository;
import com.servicebook.service.ClienteService;
import com.servicebook.service.CloudinaryService;
import com.servicebook.service.FotoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/imagenes")
@CrossOrigin
@Controller
public class FotoUsuarioController {

  @Autowired
  private CloudinaryService cloudinaryService;
  @Autowired
  private FotoUsuarioService fotoUsuarioService;
  @Autowired
  private ClienteService clienteService;
  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private ProveedorRepository proveedorRepository;
  @Autowired
  private AdminRepository adminRepository;

  @PostMapping("/upload")
  public String upload(@RequestParam MultipartFile multipartFile, HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
 
    BufferedImage entry = ImageIO.read(multipartFile.getInputStream());

    if (entry == null) {
      redirectAttributes.addFlashAttribute("error", "Imagen Nula");
      return "redirect:/modificar";
    }

    Map resultado = cloudinaryService.subirFoto(multipartFile);
    FotoUsuario foto = new FotoUsuario();
    foto.setNombre((String) resultado.get("original_filename"));
    foto.setUrl((String) resultado.get("url"));
    foto.setFotoId((String) resultado.get("public_id"));

    foto = fotoUsuarioService.guardar(foto);
    
    Usuario usuario = (Usuario) session.getAttribute("usuariosession");
    
    if (usuario != null) {
      if (usuario.getRole() == Role.USER) {
        Cliente cliente = (Cliente) session.getAttribute("usuariosession");
        ClienteDtoEnviado clienteDto = clienteService.obtenerClienteConDirecciones(cliente.getId());
        if (clienteDto != null) {
          redirectAttributes.addFlashAttribute("usuario", clienteDto);
          cliente.setFoto(foto);
          clienteRepository.save(cliente);
        }
      } else if (usuario.getRole() == Role.PROVEEDOR) {
        Proveedor proveedor = (Proveedor) session.getAttribute("usuariosession");
        redirectAttributes.addFlashAttribute("usuario", proveedor);
        proveedor.setFoto(foto);
        proveedorRepository.save(proveedor);
      } else if (usuario.getRole() == Role.ADMIN) {
        Admin admin = (Admin) session.getAttribute("usuariosession");
        redirectAttributes.addFlashAttribute("usuario", admin);
        admin.setFoto(foto);
        adminRepository.save(admin);
      }
    }
    
    redirectAttributes.addFlashAttribute("exito", "imagen registrada con éxtio!");
    return "redirect:/modificar";
  }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException {
//        if(!imagenService.existePorId(id))
//            return ResponseEntity.notFound().build();
//        FotoUsuario foto = imagenService.encontrarImagen(id).get();
//        Map resultado = cloudinaryService.delete(imagen.getImagenId());
//        imagenService.borraraPorId(id);
//        return new ResponseEntity(new String ("imagen eliminada"),HttpStatus.OK);
//    }
}
