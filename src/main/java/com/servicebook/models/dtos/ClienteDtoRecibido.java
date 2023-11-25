package com.servicebook.models.dtos;
import com.servicebook.models.Cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public record ClienteDtoRecibido(@Email @NotBlank String email,
                                 @NotBlank String nombre,
                                 @NotBlank String password,
                                 @NotBlank List<DireccionDto> direccion) {

    public ClienteDtoRecibido(Cliente cliente){
        this(cliente.getEmail(), cliente.getNombre(),
                cliente.getPassword(),
                cliente.getDireccion().stream().map(DireccionDto::new).toList());
    }
}
