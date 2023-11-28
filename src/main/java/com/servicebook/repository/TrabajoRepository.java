
package com.servicebook.repository;

import com.servicebook.models.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;


@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {
    
    @Query("SELECT l FROM Usuario l WHERE l.nombre = :nombre")
    public Trabajo buscarNombreDeUsuario(@Param("nombre") String nombre);
    
    @Query("SELECT l FROM Proveedor l WHERE l.nombre = :nombre")
    public Trabajo buscoNombreProveedor(@Param("nombre") String nombre);
 
    @Query("UPDATE Trabajo t SET t.terminoProveedor = true WHERE t.id = :id")
    public void confirmacionProveedor(@Param("id") Long id);
    
    @Query("UPDATE Trabajo t SET t.terminoCliente = true WHERE t.id = :id")
    public void confirmacionCliente(@Param("id") Long id);
    
    //@Query("SELECT t from Trabajo WHERE t.alta = true")
    
}
