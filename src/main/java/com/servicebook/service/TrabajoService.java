
package com.servicebook.service;

import com.servicebook.repository.TrabajoRepository;
import com.servicebook.repository.UsuarioRepositorio;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author julip
 */

@Service
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
