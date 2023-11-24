package com.servicebook.models.dtos;

import com.servicebook.models.Direccion;

public record DireccionDto(String calle,
                           String numero,
                           String provincia,
                           String localidad) {

    public DireccionDto(Direccion direccion){
        this(direccion.getCalle(), direccion.getNumero(), direccion.getProvincia(), direccion.getLocalidad());
    }
}
