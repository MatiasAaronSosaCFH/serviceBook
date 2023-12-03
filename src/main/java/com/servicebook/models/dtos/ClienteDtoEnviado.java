package com.servicebook.models.dtos;

import com.servicebook.models.Cliente;

import java.util.Date;
import java.util.List;

public record ClienteDtoEnviado(Long id,
                                String nombre,
                                String email,
                                List<DireccionDtoEnviado> direcciones,
                                Date fechaAlta,
                                String role,
                                List<TrabajoDtoEnviado> trabajos){

    public ClienteDtoEnviado(Cliente cliente){
        this(cliente.getId(), cliente.getNombre(), cliente.getEmail(),
                cliente.getDireccion().stream().map(DireccionDtoEnviado::new).toList(),
                cliente.getFechaDeAlta(),cliente.getRole().toString(),
                cliente.getTrabajosPedidos().stream().map(TrabajoDtoEnviado::new).toList());
    }
}
