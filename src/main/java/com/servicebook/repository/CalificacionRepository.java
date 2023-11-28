/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.servicebook.repository;

import com.servicebook.models.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion , Long> {
   
    @Query("SELECT c FROM Calificacion c WHERE c.alta = true")
    List<Calificacion> listarCalificaciones();

    @Query("SELECT c FROM Calificacion c WHERE c.alta = true AND c.trabajo = :id")
    Optional<Calificacion> buscarPorIdTrabajo(@Param("id") Long id);


} 
