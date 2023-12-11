package com.servicebook.service;

import com.servicebook.exception.MiException;
import com.servicebook.models.Cliente;
import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;
import com.servicebook.models.dtos.ProveedorDtoEnviado;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.ClienteRepository;
import com.servicebook.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<Proveedor> findByAlta() {

    return proveedorRepository.listarProveedoresAlta();
  }

  public void save(Proveedor proveedor) {
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

  public void cambiarEstado(Long id) {
    Optional<Proveedor> respuesta = proveedorRepository.findById(id);
    if (respuesta.isPresent()) {
      Boolean estado = respuesta.get().getAlta();
      Proveedor proveedor = respuesta.get();

      proveedor.setAlta(!estado);

      proveedorRepository.save(proveedor);
    }
  }

  public void cambiarDisponible(Long id) {
    Optional<Proveedor> respuesta = proveedorRepository.findById(id);
    if (respuesta.isPresent()) {
      Boolean disponible = respuesta.get().getDisponible();
      Proveedor proveedor = respuesta.get();

      proveedor.setDisponible(!disponible);

      proveedorRepository.save(proveedor);
    }
  }

  public void rotarRol(Long id) {

    Optional<Proveedor> respuesta = proveedorRepository.findById(id);
    if (respuesta.isPresent()) {
      Role role = respuesta.get().getRole();
      Proveedor proveedor = respuesta.get();

      if (role.equals(Role.USER)) {
        proveedor.setRole(Role.PROVEEDOR);
      }
      if (role.equals(Role.PROVEEDOR)) {
        proveedor.setRole(Role.USER);
      }

      proveedorRepository.save(proveedor);
    }
  }

  public void aprobar(Long id) {
    Optional<Proveedor> respuesta = proveedorRepository.findById(id);
    if (respuesta.isPresent()) {
      Boolean aprobacion = respuesta.get().getAprobacion();
      Proveedor proveedor = respuesta.get();

      proveedor.setAlta(true);
      proveedor.setAprobacion(true);
      proveedor.setRole(Role.PROVEEDOR);

      proveedorRepository.save(proveedor);
    }
  }

  public Proveedor crearProveedor(Long idCliente, String emailDeContacto, String numeroDeContacto, List<Profesion> profesiones, String presentacion, Integer precioPorHora, Boolean disponible) {
    Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
    Proveedor proveedor = new Proveedor();
    //proveedor.setId(idCliente);
    proveedor.setFoto(cliente.getFoto());
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

    proveedor.setAprobacion(false);
    if(disponible != null){
      proveedor.setDisponible(true);
    } else {
      proveedor.setDisponible(false);
    }
    
    proveedorRepository.save(proveedor);

    return proveedor;
  }

  public void modificarPassword(Long id, String passwordActual, String nuevaPassword, String repetirPassword) throws MiException {
    Optional<Proveedor> proveedorResp = proveedorRepository.buscarPorId(id);

    if (proveedorResp.isPresent()) {

      Proveedor proveedor = proveedorResp.get();

      if (!passwordEncoder.matches(passwordActual, proveedor.getPassword())) {
        throw new MiException("Ingresó una contraseña incorrecta");
      }

      if (nuevaPassword.length() < 6 || repetirPassword.length() < 6) {

        throw new MiException("La contraseña no puede tener menos de 6 caracteres");

      }

      if (!nuevaPassword.equals(repetirPassword)) {
        throw new MiException("Las contraseñas nuevas no coinciden");
      }

      // Actualizar la contraseña
      proveedor.setPassword(passwordEncoder.encode(nuevaPassword));
      proveedorRepository.save(proveedor);

    }

  }

  @Transactional
  public ProveedorDtoEnviado obtenerProveedorConDirecciones(Long proveedorId) {
    Proveedor proveedor = proveedorRepository.findById(proveedorId).orElse(null);
    if (proveedor != null) {
      return new ProveedorDtoEnviado(proveedor);
    }
    return null;
  }

  @Transactional
  public void modificar(Long id, Proveedor proveedorActualizado) throws MiException {

    Optional<Proveedor> proveedorResp = proveedorRepository.buscarPorId(id);

    if (proveedorResp.isPresent()) {

      Proveedor proveedor = proveedorResp.get();

      if (proveedorActualizado.getNombre().trim().isEmpty()) {

        throw new MiException("El nombre no puede estar vacío");

      }

      if (proveedorActualizado.getEmail().trim().isEmpty()) {

        throw new MiException("El email no puede estar vacío");

      }

      if (proveedorActualizado.getNombre() != null) {
        proveedor.setNombre(proveedorActualizado.getNombre());
      }
      if (proveedorActualizado.getEmail() != null) {
        proveedor.setEmail(proveedorActualizado.getEmail());
      }
      if (proveedorActualizado.getAlta() != null) {
        proveedor.setAlta(proveedorActualizado.getAlta());
      }
      if (proveedorActualizado.getRole() != null) {
        proveedor.setRole(proveedorActualizado.getRole());
      }
      if (proveedorActualizado.getFoto() != null) {
        proveedor.setFoto(proveedorActualizado.getFoto());
      }
      if (proveedorActualizado.getPassword() != null) {
        proveedor.setPassword(proveedorActualizado.getPassword());
      }

      if(proveedorActualizado.getEmailDeContacto() != null){
        proveedor.setEmailDeContacto(proveedorActualizado.getEmailDeContacto());
      }
      
      if(proveedorActualizado.getNumeroDeContacto() != null){
        proveedor.setNumeroDeContacto(proveedorActualizado.getNumeroDeContacto());
      }
      
      if(proveedorActualizado.getPresentacion() != null){
        proveedor.setPresentacion(proveedorActualizado.getPresentacion());
      }
      
      if(proveedorActualizado.getPrecioPorHora() != null){
        proveedor.setPrecioPorHora(proveedorActualizado.getPrecioPorHora());
      }
      
      if(proveedorActualizado.getDisponible() != null){
        proveedor.setDisponible(proveedorActualizado.getDisponible());
      }
      
      proveedorRepository.save(proveedor);

    }

  }
  
  public Boolean verificarSiExisteEmail(String email){
  
    Integer cantidad = proveedorRepository.existeEmail(email);
    
    if(cantidad == 0){
    
      return false;
    
    } else return true;
    
  }

}
