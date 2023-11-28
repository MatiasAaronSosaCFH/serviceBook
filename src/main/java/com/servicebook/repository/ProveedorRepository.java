package com.servicebook.repository;

import com.servicebook.models.Proveedor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    
    @Query("SELECT p FROM Proveedor p WHERE p.alta = true AND f.id =:id")
    Optional<Proveedor> buscarPorId(@Param("id") Long id);
    
    
}
