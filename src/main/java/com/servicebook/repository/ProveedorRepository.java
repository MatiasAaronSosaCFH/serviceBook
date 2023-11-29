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

    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<Proveedor> listarProveedores();

    @Query("SELECT p FROM Proveedor p WHERE p.alta = true AND p.id = :id")
    Optional<Proveedor> buscarProveedorPorId(@Param("id") Long id);

    @Query("SELECT p FROM Proveedor p WHERE p.alta = true AND p.id =:id")
    Optional<Proveedor> buscarPorId(@Param("id") Long id);

    Optional<Proveedor> findByEmail(String email);
}
