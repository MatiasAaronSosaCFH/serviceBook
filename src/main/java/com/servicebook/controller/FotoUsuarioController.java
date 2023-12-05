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

import com.servicebook.models.FotoUsuario;
import com.servicebook.service.CloudinaryService;
import com.servicebook.service.FotoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin
public class FotoUsuarioController {

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private FotoUsuarioService fotoUsuarioService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage entry = ImageIO.read(multipartFile.getInputStream());

        if (entry == null) return new ResponseEntity(new String("imagen no valida"), HttpStatus.BAD_REQUEST);

        Map resultado = cloudinaryService.subirFoto(multipartFile);
        FotoUsuario foto = new FotoUsuario();
        foto.setNombre((String) resultado.get("original_filename"));
        foto.setUrl((String) resultado.get("url"));
        foto.setFotoId((String)resultado.get("public_id"));
        
        fotoUsuarioService.guardar(foto);
        return new ResponseEntity(new String ("imagen subida"), HttpStatus.OK);
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