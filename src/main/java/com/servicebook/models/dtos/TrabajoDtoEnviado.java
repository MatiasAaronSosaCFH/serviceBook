package com.servicebook.models.dtos;

import com.servicebook.models.Calificacion;
import com.servicebook.models.Foto;
import com.servicebook.models.Trabajo;

import java.util.List;

public record TrabajoDtoEnviado (Long id,
                                 Calificacion calificacion,
                                 List<String> fotos){

    public TrabajoDtoEnviado(Trabajo trabajo){
        this(trabajo.getId(),trabajo.getCalificacion(),
                trabajo.getFotos().stream().map(Foto::getUrl).toList());
    }
}
