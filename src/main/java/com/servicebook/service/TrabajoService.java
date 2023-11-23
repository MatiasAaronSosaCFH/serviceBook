/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servicebook.service;

import com.servicebook.repository.TrabajoRepository;
import com.servicebook.repository.UsuarioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author julip
 */
public class TrabajoService {
    
    @Autowired
    private TrabajoRepository trabajo_repository;
    
    @Autowired
    private UsuarioRepositorio usuarioRepository;
      
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    public void crear_carta(MultipartFile archivo, String emailUsuario, String emailProvedor){
        
        
        Optional<Cliente> respuestaCliente = usuarioRepository.findByEmail(emailUsuario);
        Optional<Proveedor> respuestaProveedor = ProveedorRepository.findByEmail(emailProvedor);
        
    }
    
    
    
}
