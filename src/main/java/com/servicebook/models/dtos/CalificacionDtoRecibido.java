package com.servicebook.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CalificacionDtoRecibido (@NotNull String descripcion,
                                       @NotBlank Integer estrellas,
                                       @NotBlank Long trabajo){


}
