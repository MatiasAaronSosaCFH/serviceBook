
package com.servicebook.repository;

import com.servicebook.models.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long>{
    
  @Query("UPDATE Direccion d SET d.alta = false WHERE d.calle = :calle AND d.localidad = :localidad AND "
       + "d.provincia = :provincia AND d.numero = :numero")
  public void darDeBaja(@Param("calle") String calle, @Param("localidad") String localidad, @Param("provincia") String provincia, 
                        @Param("numero") String numero);

  
}
