/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.servicebook.repository;

import com.servicebook.models.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FotoRepository extends JpaRepository<Foto , Long>  {

    @Query("SELECT f FROM Foto f WHERE f.alta = true")
    List<Foto> listadoDeFotos();

    @Query("SELECT f FROM Foto f WHERE f.alta = true AND f.id =:id")
    Optional<Foto> buscarPorId(@Param("id") Long id);


}
