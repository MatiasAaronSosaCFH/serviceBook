package com.servicebook.repository;

import com.servicebook.models.Proveedor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
<<<<<<< HEAD

    @Query("SELECT p FROM Proveedpr p WHERE p.alta = true")
    List<Proveedor> listarProveedores();

    @Query("SELECT p FROM Proveedor p WHERE p.alta = true AND p.id = :id")
    Optional<Proveedor> buscarProveedorPorId(@Param("id") Long id);
=======
    
    @Query("SELECT p FROM Proveedor p WHERE p.alta = true AND f.id =:id")
    Optional<Proveedor> buscarPorId(@Param("id") Long id);
    
    
>>>>>>> 2f1525de441acb3e1735f87faedfec622b531959
}