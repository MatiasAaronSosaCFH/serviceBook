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
import com.servicebook.models.FotoProveedor;
import com.servicebook.models.Proveedor;
import com.servicebook.models.Usuario;
import com.servicebook.repository.ProveedorRepository;
import com.servicebook.service.CloudinaryService;
import com.servicebook.service.FotoProveedorService;
import com.servicebook.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin
public class FotoProveedorController {

  @Autowired
  private CloudinaryService cloudinaryService;
  @Autowired
  private FotoProveedorService fotoProveedorService;
  @Autowired
  private ProveedorService proveedorService;
  @Autowired
  private ProveedorRepository proveedorRepository;

  @GetMapping("/uploadProveedor")
  public ResponseEntity<String> upload(@RequestParam("imagenes") MultipartFile[] imagenes, HttpSession session) {
    try {
      
      Usuario usuario = (Usuario) session.getAttribute("usuariosession");

      Proveedor proveedor = proveedorService.findById(usuario.getId());
      
      for (MultipartFile imagen : imagenes) {
        BufferedImage entry = ImageIO.read(imagen.getInputStream());

        if (entry == null) {
          return new ResponseEntity<>("Imagen Nula", HttpStatus.BAD_REQUEST);
        }

        Map resultado = cloudinaryService.subirFoto(imagen);
        FotoProveedor foto = new FotoProveedor();
        foto.setNombre((String) resultado.get("original_filename"));
        foto.setUrl((String) resultado.get("url"));
        foto.setFotoId((String) resultado.get("public_id"));

        foto = fotoProveedorService.guardar(foto);

        
        proveedor.getFotos().add(foto);
        
      }
      proveedorRepository.save(proveedor);
      return new ResponseEntity<>("Imágenes cargadas exitosamente", HttpStatus.OK);
    } catch (IOException e) {
      e.printStackTrace();
      return new ResponseEntity<>("Error al procesar las imágenes", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
