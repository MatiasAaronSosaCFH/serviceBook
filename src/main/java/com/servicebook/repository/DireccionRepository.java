
package com.servicebook.repository;

import com.servicebook.models.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long>{
    
  @Query("UPDATE Direccion d SET d.alta = false WHERE d.id = :id")
  public void baja(@Param("id") Long id);
  
  @Query("UPDATE Direccion d SET d.alta = true WHERE d.id = :id")
  public void alta(@Param("id") Long id);

  
}
