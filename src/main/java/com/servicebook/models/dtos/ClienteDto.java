package com.servicebook.models.dtos;
import com.servicebook.models.Cliente;
import com.servicebook.models.dtos.DireccionDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public record ClienteDto(@Email @NotBlank String email,
                         @NotBlank String nombre,
                         @NotBlank String password,
                         @NotBlank List<DireccionDto> direccion) {

    public ClienteDto(Cliente cliente){
        this(cliente.getEmail(), cliente.getNombre(),
                cliente.getPassword(),
                cliente.getDireccion().stream().map(DireccionDto::new).toList());
    }
}
