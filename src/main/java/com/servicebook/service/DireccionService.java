/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.service;

import com.servicebook.models.Direccion;
import com.servicebook.repository.DireccionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SantiagoPaguaga
 */
@Service
public class DireccionService {
  
  @Autowired
  private DireccionRepository direccionRepository;
  
  @Transactional
  public void registrarDireccion(String calle, String numero, String localidad, String provincia){
    
    Direccion direccion = new Direccion();
    
    direccion.setCalle(calle);
    direccion.setNumero(numero);
    direccion.setLocalidad(localidad);
    direccion.setProvincia(provincia);
    
    direccionRepository.save(direccion);
    
  }
          
  @Transactional
  public void eliminarDireccion(Direccion direccion){
  
    direccionRepository.deleteById(direccion.getId());
  
  }
          
  public List<Direccion> listadoDirecciones(){
  
    return direccionRepository.findAll();
    
  }
  
  @Transactional(readOnly = true)
  public Direccion buscarPorId(Long id){
  
    return direccionRepository.findById(id).orElse(null);
    
  }
  
  @Transactional
  public void darDeBajaDireccion(String calle, String numero, String localidad, String provincia){
  
     direccionRepository.darDeBaja(calle, localidad, provincia, numero);
    
  }
  
  
  
}
