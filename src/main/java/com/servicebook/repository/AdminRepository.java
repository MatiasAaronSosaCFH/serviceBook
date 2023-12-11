/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicebook.repository;

import com.servicebook.models.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author SantiagoPaguaga
 */
public interface AdminRepository extends JpaRepository<Admin, Long>{
  
    @Query("SELECT a FROM Admin a WHERE a.email = :email")
    Optional<Admin> findByEmail(@Param("email") String email);
    
     @Query("SELECT a FROM Admin a WHERE a.alta = true AND a.id =:id")
     Optional<Admin> buscarPorId(@Param("id") Long id);
  
}
