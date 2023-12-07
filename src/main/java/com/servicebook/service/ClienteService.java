package com.servicebook.service;

import com.servicebook.exception.MiException;
import com.servicebook.models.Cliente;
import com.servicebook.models.Direccion;
import com.servicebook.models.Proveedor;
import com.servicebook.models.dtos.ClienteDtoEnviado;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.ClienteRepository;
import com.servicebook.repository.ProveedorRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ClienteService implements UserDetailsService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ProveedorRepository proveedorRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
   
  public Cliente findById(Long id) {
    return clienteRepository.findById(id).orElse(null);
  }

  public Cliente findByEmail(String email) {

    return clienteRepository.findByEmail(email).orElse(null);
    //return clienteRepository.findByEmail(email);
  }

  public List<Cliente> findAll() {
    return clienteRepository.findAll();
  }

  public void cambiarEstado(Long id) {
    Optional<Cliente> respuesta = clienteRepository.findById(id);
    if (respuesta.isPresent()) {
      Boolean estado = respuesta.get().getAlta();
      Cliente cliente = respuesta.get();

      cliente.setAlta(!estado);

      clienteRepository.save(cliente);
    }
  }

  public void rotarRol(Long id) {

    Optional<Cliente> respuesta = clienteRepository.findById(id);
    if (respuesta.isPresent()) {
      Role role = respuesta.get().getRole();
      Cliente cliente = respuesta.get();

      if (role.equals(Role.USER)) {
        cliente.setRole(Role.PROVEEDOR);
      }
      if (role.equals(Role.PROVEEDOR)) {
        cliente.setRole(Role.USER);
      }

      clienteRepository.save(cliente);
    }
  }

  public void crearCliente(String email, String nombre, String password, String password2) throws MiException {
    validar(email, nombre, password, password2);

    Cliente cliente = new Cliente();

    cliente.setEmail(email);
    cliente.setNombre(nombre);
    cliente.setPassword(new BCryptPasswordEncoder().encode(password));
    cliente.setFechaDeAlta(new Date());
    cliente.setRole(Role.USER);

    clienteRepository.save(cliente);
  }

  @Transactional
  public void modificarCliente(Long id, String email, String nombre, String password, String password2) throws MiException {

    Cliente cliente = new Cliente() {
    };

    Optional<Cliente> respuesta = clienteRepository.buscarPorId(id);

    if (respuesta.isPresent()) {
      cliente = respuesta.get();
    }

    validar(email, nombre, password, password2);
    cliente.setEmail(email);
    cliente.setNombre(nombre);
    cliente.setPassword(new BCryptPasswordEncoder().encode(password));
//evaluar si conviene utilizar "traer el password encriptado cuando apretan el boton cambiar contraseña, habria que llenar los 2 campos en la vista con thymeleaf  	 
//		String pass = cliente.getPassword();
//		if (password.equals(pass)) {
//			System.out.println("sin cambio de password");
//		} else {
//			cliente.setPassword(new BCryptPasswordEncoder().encode(password));
//		}

    clienteRepository.save(cliente);
  }

  @Transactional
  public void bajaCliente(Long id) {
    Cliente cliente = new Cliente() {
    };

    Optional<Cliente> respuesta = clienteRepository.findById(id);

    if (respuesta.isPresent()) {
      cliente = respuesta.get();
      cliente.setAlta(false);

      clienteRepository.save(cliente);

    }
  }

  public void validar(String email, String nombre, String password, String password2) throws MiException {

    if (nombre.isEmpty()) {
      throw new MiException("el nombre no puede ser nulo o estar vacio");
    }

    if (email.isEmpty()) {
      throw new MiException("el email no puede ser nulo o estar vacio");
    }

    if (password.isEmpty() || password == null || password.length() <= 5) {
      throw new MiException("la contraseña no puede estar vacia, y debe tener mas de 5 digitos");
    }
    if (!password.equals(password2)) {
      throw new MiException("las contraseñas ingresadas deben ser iguales");
    }

  }

  @Transactional
  public ClienteDtoEnviado obtenerClienteConDirecciones(Long clienteId) {
    Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
    if (cliente != null) {
      return new ClienteDtoEnviado(cliente);
    }
    return null;
  }

  @Transactional
  public void modificar(Long id, Cliente clienteActualizado) throws MiException {

    Optional<Cliente> clienteResp = clienteRepository.buscarPorId(id);

    if (clienteResp.isPresent()) {

      Cliente cliente = clienteResp.get();
      
      if(clienteActualizado.getNombre().trim().isEmpty()){
        
          throw new MiException("El nombre no puede estar vacío");
        
      }

      if(clienteActualizado.getEmail().trim().isEmpty()){
        
          throw new MiException("El email no puede estar vacío");
        
        }
      
      if (clienteActualizado.getNombre() != null) {
        cliente.setNombre(clienteActualizado.getNombre());
      }
      if (clienteActualizado.getEmail() != null) {
        cliente.setEmail(clienteActualizado.getEmail());
      }
      if (clienteActualizado.getAlta() != null) {
        cliente.setAlta(clienteActualizado.getAlta());
      }
      if (clienteActualizado.getRole() != null) {
        cliente.setRole(clienteActualizado.getRole());
      }
      if (clienteActualizado.getFoto() != null) {
        cliente.setFoto(clienteActualizado.getFoto());
      }
      if (clienteActualizado.getPassword() != null) {
        cliente.setPassword(clienteActualizado.getPassword());
      }

      clienteRepository.save(cliente);

    }

  }

  public void modificarPassword(Long id, String passwordActual, String nuevaPassword, String repetirPassword) throws MiException {
    Optional<Cliente> clienteResp = clienteRepository.buscarPorId(id);

    if (clienteResp.isPresent()) {

      Cliente cliente = clienteResp.get();

      if (!passwordEncoder.matches(passwordActual, cliente.getPassword())) {
        throw new MiException("Ingresó una contraseña incorrecta");
      }

      if(nuevaPassword.length() < 6 || repetirPassword.length() < 6 ){
      
        throw new MiException("La contraseña no puede tener menos de 6 caracteres");
      
      }
      
      if (!nuevaPassword.equals(repetirPassword)) {
        throw new MiException("Las contraseñas nuevas no coinciden");
      }

      // Actualizar la contraseña
      cliente.setPassword(passwordEncoder.encode(nuevaPassword));
      clienteRepository.save(cliente);

    }

  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Cliente cliente = clienteRepository.findByEmail(email).orElse(null);

    if (cliente != null) {

      List<GrantedAuthority> permisos = new ArrayList<>();

      GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + cliente.getRole().toString());

      permisos.add(p);

      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

      HttpSession session = attr.getRequest().getSession();

      session.setAttribute("usuariosession", cliente);

      return new User(cliente.getEmail(), cliente.getPassword(), permisos);

    }

    Proveedor proveedor = proveedorRepository.findByEmail(email).orElse(null);

    if (proveedor != null) {

      List<GrantedAuthority> permisos = new ArrayList<>();

      GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + proveedor.getRole().toString());

      permisos.add(p);

      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

      HttpSession session = attr.getRequest().getSession();

      session.setAttribute("usuariosession", proveedor);

      return new User(proveedor.getEmail(), proveedor.getPassword(), permisos);

    }

    return null;

  }

}
