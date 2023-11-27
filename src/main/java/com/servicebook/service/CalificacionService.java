package com.servicebook.service;

import com.servicebook.models.Calificacion;
import com.servicebook.models.enums.Estrellas;
import org.springframework.stereotype.Service;

/**
 *
 * @author julip
 */


@Service
public class CalificacionService {
    
    
    public Calificacion calificar(Estrellas estrellas, String descripcion){
        Calificacion calificacion = new Calificacion();
        calificacion.setEstrellas(estrellas);
        calificacion.setDescripcion(descripcion);
        return calificacion;
    }

}
