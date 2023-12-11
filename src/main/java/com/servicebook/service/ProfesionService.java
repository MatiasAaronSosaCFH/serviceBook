package com.servicebook.service;

import com.servicebook.models.Profesion;
import com.servicebook.repository.ProfesionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesionService {

  @Autowired
  private ProfesionRepository profesionRepository;

  public void crearProfesion(String nombre) {
    Optional<Profesion> respuesta = profesionRepository.buscarProfesionPorNombre(nombre);
    if (!respuesta.isPresent()) {
      Profesion prof = new Profesion();
      prof.setNombre(nombre);
      prof.setAlta(Boolean.TRUE);
      profesionRepository.save(prof);
    }
  }

  public List<Profesion> listaProfesiones() {
    return profesionRepository.listarProfesiones();
  }

  public List<Profesion> listaProfesionPorAlta() {
    return profesionRepository.listarProfesiones();
  }

  public void cambiarEstado(Long id) {
    Optional<Profesion> respuesta = profesionRepository.findById(id);
    if (respuesta.isPresent()) {
      Boolean estado = respuesta.get().getAlta();
      Profesion profesion = respuesta.get();

      profesion.setAlta(!estado);

      profesionRepository.save(profesion);
    }
  }

  public List<Profesion> obtenerProfesionesPorNombres(List<String> nombres) {
    return profesionRepository.findByNombreIn(nombres);
  }
  
  public Profesion obtenerProfesionPorNombre(String nombre) {
    return profesionRepository.findByNombre(nombre);
  }

}
