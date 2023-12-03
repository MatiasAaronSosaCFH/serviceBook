
package com.servicebook.repository;

import com.servicebook.models.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {

    @Query("SELECT t FROM Trabajo t  WHERE t.proveedor = :id AND t.alta = true")
    List<Trabajo> buscarTrabajoPorProveedor(@Param("id")Long id);

    @Query("SELECT t FROM Trabajo t  WHERE t.cliente = :id AND t.alta = true")
    List<Trabajo> buscarTrabajoPorCliente(@Param("id")Long id);

    @Query("SELECT t FROM Trabajo t  WHERE t.calificacion = :id AND t.alta = true")
    List<Trabajo> buscarTrabajoPorCalificacion(@Param("id")Long id);


    @Query("SELECT t FROM Trabajo t  WHERE t.terminoCliente = :true AND t.terminoProveedor = true AND t.alta = true")
    List<Trabajo> buscarTrabajoTerminado();

    @Query("SELECT t FROM Trabajo t WHERE t.alta = true")
    List<Trabajo> listarTrabajos();

    @Query("SELECT t FROM Trabajo t WHERE t.alta = true AND t.id = :id")
    Optional<Trabajo> buscarPorID(@Param("id")Long id);

    @Modifying
    @Query("UPDATE Trabajo t SET t.terminoProveedor = true WHERE t.id = :id")
    void confirmacionProveedor(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Trabajo t SET t.terminoCliente = true WHERE t.id = :id")
    void confirmacionCliente(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Trabajo t SET t.proveedor = :proveedor WHERE t.id = :id")
    void modificarProveedor(@Param("proveedor")Long proveedor, @Param("id")Long id);

    @Modifying
    @Query("UPDATE Trabajo t SET t.cliente = :cliente WHERE t.id = :id")
    void modificarCliente(@Param("cliente")Long cliente, @Param("id")Long id);

    @Modifying
    @Query("UPDATE Trabajo t SET t.calificacion = :calificacion WHERE t.id = :id")
    void modificarCalificacion(@Param("calificacion")Long calificacion, @Param("id")Long id);

    @Modifying
    @Query("UPDATE Trabajo t SET t.terminoCliente = :boll WHERE t.id = :id")
    void modificarTerminoCliente(@Param("boll")Boolean boll, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Trabajo t SET t.terminoProveedor = :boll WHERE t.id = :id")
    void modificarTerminoProveedor(@Param("boll")Boolean boll, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Trabajo t SET t.terminoProveedor = :terminoP, t.terminoCliente = :terminoC, t.calificacion = :calificacion, " +
            "t.proveedor = :proveedor, t.cliente = :cliente WHERE t.id = :id")
    void modificarTrabajo(@Param("terminoP") Boolean terminoP,
                          @Param("terminoC") Boolean terminoC,
                          @Param("calificacion")Long calificacion,
                          @Param("proveedor")Long proveedor,
                          @Param("cliente")Long cliente,
                          @Param("id")Long id);
}
