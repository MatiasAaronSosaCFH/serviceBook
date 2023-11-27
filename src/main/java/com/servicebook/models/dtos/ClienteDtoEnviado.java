package com.servicebook.models.dtos;

import com.servicebook.models.Cliente;

import java.util.List;

public record ClienteDtoEnviado(String nombre,
                                String email,
                                Long id,
                                List<DireccionDto> direcciones,
                                List<TrabajoDtoEnviado> trabajosContratados) {

    public ClienteDtoEnviado(Cliente cliente){
        this(cliente.getNombre(),cliente.getEmail(),cliente.getId(),
                cliente.getDireccion().stream().map(DireccionDto::new).toList(),
                cliente.getTrabajosPedidos().stream().map(TrabajoDtoEnviado::new).toList());
    }
}
