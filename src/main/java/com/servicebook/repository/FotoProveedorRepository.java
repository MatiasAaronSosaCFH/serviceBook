/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.repository;

import com.servicebook.models.FotoProveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SantiagoPaguaga
 */
@Repository
public interface FotoProveedorRepository extends JpaRepository<FotoProveedor, Long>{
  
    @Query("SELECT f FROM FotoProveedor f WHERE f.alta = true")
    List<FotoProveedor> listarImagenesDeProveedor();

    @Query("SELECT f FROM FotoProveedor f WHERE f.id = :id")
    Optional<FotoProveedor> buscarFotoPorId(@Param("id") Long id);
  
}
