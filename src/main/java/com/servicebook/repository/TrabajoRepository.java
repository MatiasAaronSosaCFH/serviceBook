
package com.servicebook.repository;

import com.servicebook.models.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {
    
    //@Query("SELECT l FROM Usuario WHERE l.nombre = :nombre")
    //public Trabajo buscarNombreDeUsuario(@Param("nombre") String nombre);
    
    //@Query("SELECT l FROM Proveedor WHERE l.nombre = :nombre")
    //public Trabajo buscoNombreProveedor(@Param("nombre") String nombre);
    
}
