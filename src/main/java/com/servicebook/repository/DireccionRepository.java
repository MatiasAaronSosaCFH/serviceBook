
package com.servicebook.repository;

import com.servicebook.models.Cliente;
import com.servicebook.models.Direccion;
import com.servicebook.models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

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

  @Query("SELECT d FROM Direccion d WHERE d.alta = true AND :cliente MEMBER OF d.clientes")
  List<Direccion> buscarDireccionesPorCliente(@Param("cliente") Cliente cliente);

  @Query("SELECT d FROM Direccion d WHERE d.alta = true AND :proveedor MEMBER OF d.proveedores")
  List<Direccion> buscarDireccionesPorProveedor(@Param("proveedor") Proveedor proveedor);

  @Query("SELECT d FROM Direccion d WHERE d.provincia = :provincia")
  List<Direccion> buscarPorProvincia(@Param("provincia") String provincia);

  @Query("SELECT d FROM Direccion d WHERE d.localidad = :localidad")
  List<Direccion> buscarPorLocalidad(@Param("localidad") String localidad);
  
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM clientes_direcciones WHERE cliente_id = :clienteId AND direccion_id = :direccionId", nativeQuery = true)
  void deleteClientesDirecciones(Long clienteId, Long direccionId);
  
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM proveedores_direcciones WHERE proveedor_id = :proveedorId AND direccion_id = :direccionId", nativeQuery = true)
  void deleteProveedoresDirecciones(Long proveedorId, Long direccionId);
}
