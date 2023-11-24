
package com.servicebook.service;

import com.servicebook.repository.TrabajoRepository;
import com.servicebook.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class TrabajoService {
    
    @Autowired
    private TrabajoRepository trabajo_repository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
      
    //@Autowired
    //private ProveedorRepository proveedorRepository;
    
   /* @Transactional
    public void crear_carta(MultipartFile archivo, String emailUsuario, String emailProvedor){
        
        
       // Optional<Cliente> respuestaCliente = usuarioRepository.findByEmail(emailUsuario);
       // Optional<Proveedor> respuestaProveedor = ProveedorRepository.findByEmail(emailProvedor);
        
    }*/
    
}
