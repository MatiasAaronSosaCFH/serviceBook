/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servicebook.service;

import com.servicebook.exception.MiException;
import com.servicebook.models.Direccion;
import com.servicebook.models.Usuario;
import com.servicebook.models.enums.Role;
import com.servicebook.repository.ClienteRepository;
import com.servicebook.repository.ProveedorRepository;
import com.servicebook.repository.UsuarioRepository;

import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void crearUsuario(String email, String nombre, Date fechaDeAlta, Role role, Boolean alta, String password, String password2, List<Direccion> direccion) throws MiException {

        validar(email, nombre, role, password, password2, direccion);

        Usuario usuario = new Usuario() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority((role.name())));
            }

            @Override
            public String getUsername() {
                return this.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setFechaDeAlta(fechaDeAlta);
        usuario.setRole(role);
        usuario.setAlta(alta);
        usuario.setPassword(password);
        usuario.setDireccion(direccion);
        usuarioRepository.save(usuario);

    }

    public void validar(String email, String nombre, Role role, String password, String password2, List<Direccion> direccion) throws MiException {

        if (email.isEmpty()) {
            throw new MiException("el email no puede ser nulo o estar vacio");
        }
        if (nombre.isEmpty()) {
            throw new MiException("el nombre no puede ser nulo o estar vacio");
        }
        if (!role.equals(role.USER)) {
            if (!role.equals(role.ADMIN)) {
                throw new MiException("el rol no puede ser nulo o estar vacio");
            }
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("la contraseña no puede estar vacia, y debe tener mas de 5 digitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("las contraseñas ingresadas deben ser iguales");
        }
        if (direccion.isEmpty()) {
            throw new MiException("la direccion no puede ser nulo o estar vacio");
        }
    }

    @Transactional
    public void modificarUsuario(Long id, String email, String nombre, Date fechaDeAlta, Role role, Boolean alta, String password, String password2, List<Direccion> direccion) throws MiException {

        Usuario usuario = new Usuario() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority((role.name())));
            }

            @Override
            public String getUsername() {
                return this.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        
        Optional<Usuario> respuesta = usuarioRepository.findById(id);

        if (respuesta.isPresent()) {
            usuario = respuesta.get();
        }

        validar(email, nombre, role, password, password2, direccion);
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setFechaDeAlta(fechaDeAlta);
        usuario.setRole(role);
        usuario.setAlta(alta);
        usuario.setPassword(password);
        usuario.setDireccion(direccion);
        usuarioRepository.save(usuario);

    }

    @Transactional
    public void eliminarUsuario(Long id) {

        Optional<Usuario> respuesta = usuarioRepository.findById(id);
        if (respuesta.isPresent()) {
            usuarioRepository.deleteById(id);
        }
    }

    public void altaUsuario(Long id) {
        Usuario usuario = new Usuario() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority((role.name())));
            }

            @Override
            public String getUsername() {
                return this.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        Optional<Usuario> respuesta = usuarioRepository.findById(id);

        if (respuesta.isPresent()) {
            usuario = respuesta.get();
            usuario.setAlta(Boolean.TRUE);
        }
    }

    @Transactional
    public void bajaUsuario(Long id) {
        Usuario usuario = new Usuario() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority((role.name())));
            }

            @Override
            public String getUsername() {
                return this.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        Optional<Usuario> respuesta = usuarioRepository.findById(id);

        if (respuesta.isPresent()) {
            usuario = respuesta.get();
            usuario.setAlta(Boolean.FALSE);

            usuarioRepository.save(usuario);

            
        }
    }
    
    public List<Usuario> listarUsuarios() {
    List<Usuario> usuarios = new ArrayList();
    usuarios = usuarioRepository.findAll();
    return usuarios;
}
   
    public Usuario getOne(Long id) throws MiException {
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }else {
            throw new MiException("no existe");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario;

        try{
            usuario = proveedorRepository.findByEmail(email).orElse(null);
        }catch (Exception ex ){
            System.out.println(ex.toString());
        }
        try {
            usuario = clienteRepository.findByEmail(email).orElse(null);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }finally {
            usuario = new Usuario() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public String getUsername() {
                    return null;
                }

                @Override
                public boolean isAccountNonExpired() {
                    return false;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return false;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return false;
                }

                @Override
                public boolean isEnabled() {
                    return false;
                }
            };
        }
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRole().toString()); //ROLE_USER
            permisos.add(p);

            //seguridad para ROLE_ADMIN
            ServletRequestAttributes attr =  (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            User user = new User(usuario.getEmail(), usuario.getPassword(), permisos);
            return user;
        } else {
            return null;
        }
    }
    
    
    }
