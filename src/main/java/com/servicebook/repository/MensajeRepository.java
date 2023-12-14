/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.repository;

import com.servicebook.models.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SantiagoPaguaga
 */
@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long>{
  
}
