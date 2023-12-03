package com.servicebook.service;

import com.servicebook.models.Calificacion;
import com.servicebook.models.enums.Estrellas;
import com.servicebook.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;
    
    public Calificacion calificar(Estrellas estrellas, String descripcion){
        Calificacion calificacion = new Calificacion();
        calificacion.setEstrellas(estrellas);
        calificacion.setDescripcion(descripcion);
        return calificacion;
    }

    public List<Calificacion> listarCalificaciones(){
        return calificacionRepository.listarCalificaciones();
    }

    public Calificacion buscarCalificacionDeTrabajo(Long trabajo){
        return calificacionRepository.buscarPorIdTrabajo(trabajo).orElse(null);
    }

    public List<Calificacion> buscarCalificacionesPorEstrellas(Integer estrellas){
        return calificacionRepository.buscarPorEstrellas(estrellas);
    }

    public void modificarEstrellas(Long id, Integer estrellas){
        calificacionRepository.cambiarEstrellas(id,estrellas);
    }

    public void modificarDescripcion(Long id, String descripcion){
        calificacionRepository.cambiarDescripcion(id,descripcion);
    }

    public void modificarTrabajo(Long trabajo, Long id){
        calificacionRepository.modificarTrabajo(trabajo, id);
    }
}
