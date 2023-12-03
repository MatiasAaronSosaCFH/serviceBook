package com.servicebook.models.dtos;
import com.servicebook.models.Cliente;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ClienteDtoRecibido(@NotBlank String nombre,
                                 @NotBlank @Email String email,
                                 @NotBlank String password,
                                 @NotNull DireccionDtoRecibido direccionDtoRecibido) {


}
