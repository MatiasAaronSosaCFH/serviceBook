package com.servicebook.models.dtos;

import com.servicebook.models.Calificacion;

public record CalificacionDtoEnvidado(Long id,
                                      Integer estrellas,
                                      Long trabajoId,
                                      String descripcion) {


    public CalificacionDtoEnvidado(Calificacion calificacion){
        this(calificacion.getId(), calificacion.getEstrellas().getNumero(),
                calificacion.getTrabajo().getId(),calificacion.getDescripcion());
    }
}
