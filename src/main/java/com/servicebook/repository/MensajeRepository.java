/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.repository;

import com.servicebook.models.Mensaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SantiagoPaguaga
 */
@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long>{
  
    @Query("SELECT m FROM Mensaje m WHERE m.trabajo.id = :id AND m.alta = true")
    List<Mensaje> buscarMensajesPorTrabajo(@Param("id")Long id);
  
}
