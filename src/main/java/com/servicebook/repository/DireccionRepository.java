
package com.servicebook.repository;

import com.servicebook.models.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

  @Modifying
  @Query("UPDATE Direccion d SET d.alta = false WHERE d.id = :id")
  void baja(@Param("id") Long id);

  @Modifying
  @Query("UPDATE Direccion d SET d.alta = true WHERE d.id = :id")
  void alta(@Param("id") Long id);

  @Modifying
  @Query("UPDATE Direccion d SET d.calle = :calle WHERE d.id = :id")
  void modificarCalle(@Param("calle") String calle, @Param("id") Long id);

  @Modifying
  @Query("UPDATE Direccion d SET d.numero = :numero WHERE d.id = :id")
  void moificarNumero(@Param("numero") String numero, @Param("id") Long id);

  @Modifying
  @Query("UPDATE Direccion d SET d.localidad = :localidad WHERE d.id = :id")
  void modificarLocalidad(@Param("localidad") String localidad, @Param("id") Long id);

  @Modifying
  @Query("UPDATE Direccion d SET d.provincia = :provincia WHERE d.id = :id")
  void modificarProvincia(@Param("provincia") String provincia, @Param("id") Long id);


  @Modifying
  @Query("UPDATE Direccion d SET d.provincia = :provincia, d.localidad = :localidad, d.numero = :numero , d.calle = :calle WHERE d.id = :id")
  void modificarDireccion(@Param("provincia") String provincia,
                          @Param("localidad") String localidad,
                          @Param("numero") String numero,
                          @Param("calle") String calle,
                          @Param("id") Long id);

  @Query("SELECT d FROM Direccion d WHERE d.alta = true")
  List<Direccion> listarDirecciones();

  @Query("SELECT d FROM Direccion d WHERE d.alta = true AND d.id = :id")
  Optional<Direccion> buscarPorId(@Param("id") Long id);

  @Query("SELECT d FROM Direccion d WHERE d.alta = true AND d.cliente = :id")
  List<Direccion> buscarDireccionesPorCLiente(@Param("id")Long id);

  @Query("SELECT d FROM Direccion d WHERE d.alta = true AND d.proveedor = :id")
  List<Direccion> buscarDireccionesPorProveedor(@Param("id")Long id);

  @Query("SELECT d FROM Direccion d WHERE d.provincia = :provincia")
  List<Direccion> buscarPorProvincia(@Param("provincia") String provincia);

  @Query("SELECT d FROM Direccion d WHERE d.localidad = :localidad")
  List<Direccion> buscarPorLocalidad(@Param("localidad") String localidad);
}
