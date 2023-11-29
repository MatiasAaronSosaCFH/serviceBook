package com.servicebook.config;

import com.servicebook.models.Cliente;
import com.servicebook.models.Proveedor;
import com.servicebook.service.ClienteService;
import com.servicebook.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (proveedor != null) return proveedor;

        Cliente cliente = clienteService.findByEmail(email);
        if (cliente != null) return cliente;
        throw new UsernameNotFoundException("Usuario no encontrado");

    }
}
