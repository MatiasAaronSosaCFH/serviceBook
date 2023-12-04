/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.servicebook.repository;

import com.servicebook.models.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT c FROM Calificacion c WHERE c.estrellas = :estrellas")
    List<Calificacion> buscarPorEstrellas(@Param("estrellas") Integer estrellas);

    @Modifying
    @Query("UPDATE Calificacion c SET c.trabajo = :trabajo WHERE c.id = :id")
    void modificarTrabajo(@Param("trabajo")Long trabajo, Long id);

    @Modifying
    @Query("UPDATE Calificacion c SET c.alta = true WHERE c.id = :id AND c.alta = false")
    void darAlta(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Calificacion c SET c.alta = false WHERE c.id = :id AND c.alta = true")
    void darBaja(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Calificacion c SET c.estrellas = :estrellas WHERE c.id = :id")
    void cambiarEstrellas(@Param("id") Long id, @Param("estrellas") Integer estrellas);

    @Modifying
    @Query("UPDATE Calificacion c SET c.descripcion = :descripcion WHERE c.id = :id")
    void cambiarDescripcion(@Param("id") Long id, @Param("descripcion") String descripcion);

    @Modifying
    @Query("UPDATE Calificacion c SET c.descripcion = :descripcion, c.estrellas = :estrellas WHERE c.id = :id")
    void cambiarCalificacion(@Param("descripcion") String descripcion,
                             @Param("estrellas") Integer estrellas,
                             @Param("id") Long id);
} 
