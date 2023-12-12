package com.servicebook.repository;

import com.servicebook.models.FotoProveedor;
import com.servicebook.models.Profesion;
import com.servicebook.models.Proveedor;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

  @Query("SELECT p FROM Proveedor p ")
  List<Proveedor> listarProveedoresTodo();

  @Query("SELECT p FROM Proveedor p WHERE p.alta = true")
  List<Proveedor> listarProveedoresAlta();

  @Query("SELECT p FROM Proveedor p WHERE p.alta = false")
  List<Proveedor> listarProveedoresBaja();

  @Query("SELECT p FROM Proveedor p WHERE p.aprobacion = false")
  List<Proveedor> listarProveedoresAprobacion();

  List<Proveedor> findByProfesionesNombre(String nombreProfesion);

  @Query("SELECT p FROM Proveedor p JOIN p.trabajosRealizados t WHERE t.id = :trabajoId")
  Optional<Proveedor> buscarProveedorPorTrabajo(@Param("trabajoId") Long id);

  @Query("SELECT p FROM Proveedor p WHERE p.alta = true AND p.id = :id")
  Optional<Proveedor> buscarProveedorPorId(@Param("id") Long id);

  @Query("SELECT p FROM Proveedor p WHERE p.alta = true AND p.id =:id")
  Optional<Proveedor> buscarPorId(@Param("id") Long id);

  @Query("SELECT p FROM Proveedor p WHERE p.email = :email AND p.alta = true")
  Optional<Proveedor> findByEmail(@Param("email") String email);

  @Query("SELECT p FROM Proveedor p WHERE p.precioPorHora = :precio AND p.alta = true")
  List<Proveedor> buscarPorPrecio(@Param("precio") Double precio);

  @Query("SELECT p FROM Proveedor p WHERE p.precioPorHora BETWEEN :precioMin AND :precioMax")
  List<Proveedor> buscarPorPrecio(@Param("precioMin") Double precioMin, @Param("precioMax") Double precioMax);

  @Modifying
  @Query("UPDATE Proveedor p SET p.nombre = :nombre WHERE p.id = :id")
  void modificarNombre(@Param("nombre") String nombre, @Param("id") Long id);

  @Modifying
  @Query("UPDATE Proveedor p SET p.email = :email WHERE p.id = :id")
  void modificarEmail(@Param("email") String email, @Param("id") Long id);

  @Modifying
  @Query("UPDATE Proveedor p SET p.password = :password WHERE p.id = :id")
  void modificarPassword(@Param("password") String password, @Param("id") Long id);

  @Modifying
  @Query("UPDATE Proveedor p SET p.alta = true WHERE p.id = :id AND p.alta = false")
  void darAlta(@Param("id") Long id);

  @Modifying
  @Query("UPDATE Proveedor p SET p.alta = false WHERE p.id = :id AND p.alta = true")
  void darBaja(@Param("id") Long id);

  @Modifying
  @Query("UPDATE Proveedor p SET p.emailDeContacto = :email WHERE p.id = :id")
  void modificarEmailDeContacto(@Param("email") String email, @Param("id") Long id);

  @Modifying
  @Query("UPDATE Proveedor p SET p.numeroDeContacto = :numero WHERE p.id = :id")
  void modificarNumeroDeContacto(@Param("numero") String numero);

  @Modifying
  @Query("UPDATE Proveedor p SET p.presentacion = :presentacion WHERE p.id = :id")
  void modificarPresentacion(@Param("presentacion") String presentacion, @Param("id") Long id);

  @Modifying
  @Query("UPDATE Proveedor p SET p.precioPorHora = :precio WHERE p.id = :id")
  void modificarPrecio(@Param("precio") Double precio, @Param("id") Long id);

  @Modifying
  @Query("UPDATE Proveedor p SET p.nombre = :nombre , p.email = :email, p.password = :password,"
          + "p.emailDeContacto = :emailDeContacto, p.numeroDeContacto = :numero, "
          + "p.presentacion = :presentacion, p.precioPorHora = :precio WHERE p.id = :id")
  void modificarProveedor(@Param("nombre") String nombre,
          @Param("email") String email,
          @Param("password") String password,
          @Param("emailDeContacto") String emailDeContacto,
          @Param("numero") String numero,
          @Param("presentacion") String presentacion,
          @Param("precio") Double precio,
          @Param("id") Long id);

  @Query(value = "SELECT COUNT(*) FROM proveedores WHERE email = :email", nativeQuery = true)
  Integer existeEmail(@Param("email") String email);

//  @Query(value = "SELECT CAST(p.id AS SIGNED) AS proveedor_id, p.nombre, p.presentacion, f.id AS foto_id FROM proveedores p LEFT JOIN foto_proveedor f ON p.id = f.proveedor_id", nativeQuery = true)
//  List<Object[]> findAllProveedoresConFotos();
  @Query("SELECT p FROM Proveedor p")
  Page<Proveedor> listarProveedores(Pageable pageable);

  @Query(value = "SELECT p.id FROM profesiones p "
          + "JOIN proveedores_profesiones pp ON p.id = pp.profesion_id "
          + "WHERE pp.proveedor_id = :proveedorId", nativeQuery = true)
  List<BigInteger> findProfesionesByProveedorId(@Param("proveedorId") Long proveedorId);

  @Query(value = "SELECT f.id FROM foto_proveedor f WHERE f.proveedor_id = :proveedorId", nativeQuery = true)
  List<BigInteger> findFotosByProveedorId(@Param("proveedorId") Long proveedorId);

}
