package com.servicebook.service;

import com.servicebook.models.Calificacion;
import com.servicebook.models.dtos.CalificacionDtoRecibido;
import com.servicebook.models.enums.Estrellas;
import com.servicebook.repository.CalificacionRepository;
import com.servicebook.repository.TrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;
    @Autowired
    private TrabajoRepository trabajoRepository;
    public  Calificacion convertirDtoRecibido(CalificacionDtoRecibido calificacion){
        Calificacion cal = new Calificacion();
        cal.setAlta(true);
        cal.setEstrellas(Estrellas.obtenerEstrellas(calificacion.estrellas()));
        cal.setDescripcion(calificacion.descripcion());
        cal.setTrabajo(trabajoRepository.buscarPorId(calificacion.trabajo()).orElse(null));
        return cal;
    }
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

    public void modificarCalificacion(String descipcion, Integer estrellas, Long id){
        calificacionRepository.cambiarCalificacion(descipcion,estrellas,id);
    }




}
