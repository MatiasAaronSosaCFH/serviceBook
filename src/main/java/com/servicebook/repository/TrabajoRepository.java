
package com.servicebook.repository;

import com.servicebook.models.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;


@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {
    
    //@Query("SELECT l FROM Usuario l WHERE l.nombre = :nombre")
    //Trabajo buscarNombreDeUsuario(@Param("nombre") String nombre);
    
    //@Query("SELECT l FROM Proveedor l WHERE l.nombre = :nombre")
    //Trabajo buscoNombreProveedor(@Param("nombre") String nombre);
 
    @Query("UPDATE Trabajo t SET t.terminoProveedor = true WHERE t.id = :id")
    void confirmacionProveedor(@Param("id") Long id);
    
    @Query("UPDATE Trabajo t SET t.terminoCliente = true WHERE t.id = :id")
    void confirmacionCliente(@Param("id") Long id);

    //@Query("SELECT t FROM Trabajo t JOIN Proveedor p ON p.id = t.proveedor_id WHERE p.id = :id")
    //List<Trabajo> listarPorIdProveedor(@Param("id") Long id);
}
