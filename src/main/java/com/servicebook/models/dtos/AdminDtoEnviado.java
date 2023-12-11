package com.servicebook.models.dtos;

import com.servicebook.models.Admin;
import com.servicebook.models.FotoUsuario;

import java.util.Date;

public record AdminDtoEnviado(Long id,
                                String nombre,
                                FotoUsuario foto,
                                String email,
                                Date fechaDeAlta,
                                String role){

    public AdminDtoEnviado(Admin admin){
        this(admin.getId(), admin.getNombre(), admin.getFoto(), admin.getEmail(),
                admin.getFechaDeAlta(),admin.getRole().toString());
    }
}