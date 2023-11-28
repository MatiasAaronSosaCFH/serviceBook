
package com.servicebook.repository;

import com.servicebook.models.Direccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long>{
    
  @Query("UPDATE Direccion d SET d.alta = false WHERE d.id = :id")
  void baja(@Param("id") Long id);
  
  @Query("UPDATE Direccion d SET d.alta = true WHERE d.id = :id")
  void alta(@Param("id") Long id);

  @Query("SELECT d FROM Direccion d WHERE d.alta = true AND d.id = :id")
  Optional<Direccion> buscarPorIdCliente(@Param("id") Long id);

  Page<Direccion> listarDirecciones(Pageable paginacion);
}
