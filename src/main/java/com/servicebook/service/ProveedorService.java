package com.servicebook.service;

import com.servicebook.models.Cliente;
import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;
import com.servicebook.repository.ClienteRepository;
import com.servicebook.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProveedorService {

@Autowired
private ProveedorRepository proveedorRepository;
@Autowired
private ClienteRepository clienteRepository;
@Autowired
private FotoUsuarioService fotoUsuarioService;
    
    public List<Proveedor> findByAlta(){
      
        return proveedorRepository.listarProveedores();
    }

    public void save(Proveedor proveedor){
        proveedorRepository.save(proveedor);
    }
	 
@Transactional
public void bajaProveedor(Long id) {
	Proveedor proveedor;
	Optional<Proveedor> respuesta = proveedorRepository.findById(id);

	if (respuesta.isPresent()) {
		proveedor = respuesta.get();
		proveedor.setAlta(false);
		proveedorRepository.save(proveedor);
	}
}
	 
  public Proveedor findById(Long id) {
    return proveedorRepository.findById(id).orElse(null);
    
  }
  
 public Proveedor crearProveedor(Long idCliente, String emailDeContacto, String numeroDeContacto, List<Profesion> profesiones, String presentacion, Integer precioPorHora, Boolean disponible){
	Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
	Proveedor proveedor = new Proveedor();
	//proveedor.setId(idCliente);
	proveedor.setAlta(false);
	proveedor.setEmail(cliente.getEmail());
	proveedor.setFechaDeAlta(cliente.getFechaDeAlta());
	proveedor.setNombre(cliente.getNombre());
		String pass = cliente.getPassword();
		proveedor.setPassword(pass);
	proveedor.setRole(cliente.getRole());
	
	proveedor.setEmailDeContacto(emailDeContacto);
	proveedor.setNumeroDeContacto(numeroDeContacto);
	proveedor.setProfesiones(profesiones);
	proveedor.setPresentacion(presentacion);
	proveedor.setPrecioPorHora(precioPorHora);
	
//	proveedor.setFoto((FotoUsuario) archivo);
	proveedor.setAprobacion(false);
	proveedor.setDisponible(disponible);

	proveedorRepository.save(proveedor);
	
        return proveedor;
} 
  
  
  
  
}
