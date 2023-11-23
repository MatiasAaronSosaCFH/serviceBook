package com.servicebook.service;

import com.servicebook.models.Direccion;
import com.servicebook.repository.DireccionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DireccionService {
  
  @Autowired
  private DireccionRepository direccionRepository;
  
  @Transactional
  public void registrar(String calle, String numero, String localidad, String provincia){
    
    Direccion direccion = new Direccion();
    
    direccion.setCalle(calle);
    direccion.setNumero(numero);
    direccion.setLocalidad(localidad);
    direccion.setProvincia(provincia);
    
    direccionRepository.save(direccion);
    
  }
          
  @Transactional
  public void eliminar(Long id){
  
    Optional<Direccion> respuesta = direccionRepository.findById(id);
    
    if(respuesta.isPresent()){
    
      direccionRepository.deleteById(id);
    
    }
  
  }
          
  public List<Direccion> listado(){
  
    return direccionRepository.findAll();
    
  }
  
  @Transactional(readOnly = true)
  public Direccion buscarPorId(Long id){
  
    return direccionRepository.findById(id).orElse(null);
    
  }
  
  @Transactional
  public void baja(Long id){
  
     direccionRepository.baja(id);
    
  }
  
  @Transactional
  public void modificar(Long id, String calle, String numero, String localidad, String provincia){
  
    Direccion direccion = buscarPorId(id);
    
    if(direccion != null){
    
      direccion.setCalle(calle);
      direccion.setLocalidad(localidad);
      direccion.setNumero(numero);
      direccion.setProvincia(provincia);
      
      direccionRepository.save(direccion);
    
    }
    
  }
  
}
