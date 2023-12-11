package com.servicebook.models.dtos;

import com.servicebook.models.Cliente;
import com.servicebook.models.FotoUsuario;

import java.util.Date;
import java.util.List;

public record ClienteDtoEnviado(Long id,
                                String nombre,
                                FotoUsuario foto,
                                String email,
                                List<DireccionDtoEnviado> direcciones,
                                Date fechaDeAlta,
                                String role,
                                List<TrabajoDtoEnviado> trabajos){

    public ClienteDtoEnviado(Cliente cliente){
        this(cliente.getId(), cliente.getNombre(), cliente.getFoto(), cliente.getEmail(),
                cliente.getDirecciones().stream().map(DireccionDtoEnviado::new).toList(),
                cliente.getFechaDeAlta(),cliente.getRole().toString(),
                cliente.getTrabajosPedidos().stream().map(TrabajoDtoEnviado::new).toList());
    }
}
