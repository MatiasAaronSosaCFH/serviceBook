package com.servicebook.config;

import com.servicebook.models.Cliente;
import com.servicebook.models.Proveedor;
import com.servicebook.service.ClienteService;
import com.servicebook.service.ProveedorService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceDetails implements UserDetailsService {

    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Proveedor proveedor= proveedorService.findByEmail(email);
        if (proveedor != null) {
        
          List<GrantedAuthority> permisos = new ArrayList<>();

          GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + proveedor.getRole().toString());

          permisos.add(p);
          
          return new User(proveedor.getEmail(), proveedor.getPassword(), permisos);
          
        }

        Cliente cliente = clienteService.findByEmail(email);
        
        if (cliente != null) {
        
          List<GrantedAuthority> permisos = new ArrayList<>();

          GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + cliente.getRole().toString());

          permisos.add(p);
          
          return new User(cliente.getEmail(), cliente.getPassword(), permisos);
        
        }
        
        throw new UsernameNotFoundException("Usuario no encontrado");

    }
   
}

//Usuario usuario = usuarioRepository.buscarPorEmail(email);
//    
//    if(usuario != null){
//    
//      List<GrantedAuthority> permisos = new ArrayList<>();
//      
//      GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
//      
//      permisos.add(p);
//      
//      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//      
//      HttpSession session = attr.getRequest().getSession();
//      
//      session.setAttribute("usuariosession", usuario);
//      
//      return new User(usuario.getEmail(), usuario.getPassword(), permisos);
//    
//    } else {
//      
//      return null;
//      
//    }
