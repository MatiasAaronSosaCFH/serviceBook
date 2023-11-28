package com.servicebook.service;

import com.servicebook.exception.MiException;
import com.servicebook.models.Direccion;
import com.servicebook.repository.DireccionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DireccionService {

  @Autowired
  private DireccionRepository direccionRepository;

  @Transactional
  public void registrar(String calle, String numero, String localidad, String provincia) throws MiException {

    validar(calle, numero, localidad, provincia);

    Direccion direccion = new Direccion();

    direccion.setCalle(calle);
    direccion.setNumero(numero);
    direccion.setLocalidad(localidad);
    direccion.setProvincia(provincia);

    direccionRepository.save(direccion);

  }

  @Transactional
  public void eliminar(Long id) {

    Optional<Direccion> respuesta = direccionRepository.findById(id);

    if (respuesta.isPresent()) {

      direccionRepository.deleteById(id);

    }

  }

  @Transactional(readOnly = true)
  public Page<Direccion> listado(Pageable paginacion) {

    return direccionRepository.listarDirecciones(paginacion);

  }

  @Transactional(readOnly = true)
  public Direccion buscarPorId(Long id) throws MiException {

    Optional<Direccion> respuesta = direccionRepository.findById(id);

    if (respuesta.isPresent()) {

      return respuesta.get();

    } else {

      throw new MiException("No existe la dirección con el id " + id);

    }
  }

  @Transactional
  public void baja(Long id) throws MiException {

    Optional<Direccion> respuesta = direccionRepository.findById(id);

        if (respuesta.isPresent()) {
            
           direccionRepository.baja(id);
          
        } else {
        
          throw new MiException("No existe la dirección con el id " + id);
        
        }

  }
  
  @Transactional
  public void alta(Long id) throws MiException {

    Optional<Direccion> respuesta = direccionRepository.findById(id);

        if (respuesta.isPresent()) {
            
           direccionRepository.alta(id);
          
        } else {
        
          throw new MiException("No existe la dirección con el id " + id);
        
        }

  }
  
  @Transactional
  public void modificar(Long id, String calle, String numero, String localidad, String provincia) throws MiException {

    Direccion direccion = buscarPorId(id);

    direccion.setCalle(calle);
    direccion.setLocalidad(localidad);
    direccion.setNumero(numero);
    direccion.setProvincia(provincia);

    direccionRepository.save(direccion);

  }

  public void validar(String calle, String numero, String localidad, String provincia) throws MiException {

    if (calle.trim().isEmpty() || calle == null) {

      throw new MiException("La calle no puede ser nula o estar vacía");

    }

    if (numero.trim().isEmpty() || numero == null) {

      throw new MiException("El número no puede ser nulo o estar vacío");

    }

    if (localidad.trim().isEmpty() || localidad == null) {

      throw new MiException("La localidad no puede ser nula o estar vacía");

    }

    if (provincia.trim().isEmpty() || provincia == null) {

      throw new MiException("La provincia no puede ser nula o estar vacía");

    }

  }

}
