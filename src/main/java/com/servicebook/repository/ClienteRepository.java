package com.servicebook.repository;

import com.servicebook.models.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.alta = true AND c.id =:id")
    Optional<Cliente> buscarPorId(@Param("id") Long id);

    @Query("SELECT c FROM Cliente c WHERE c.alta = true")
    List<Cliente> listarClientes();

    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    Optional<Cliente> findByEmail(@Param("email") String email);

    @Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    List<Cliente> buscarPorNombre(@Param("nombre") String nombre);

    @Modifying
    @Query("UPDATE Cliente c SET c.nombre = :nombre WHERE c.id = :id")
    void modificarNombre(@Param("nombre") String nombre, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Cliente c SET c.email = :email WHERE c.id = :id")
    void modificarEmail(@Param("email") String email, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Cliente c SET c.password = :password WHERE c.id = :id")
    void modificarPassword(@Param("password") String password, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Cliente c SET c.alta = true WHERE c.id = :id AND c.alta = false")
    void darAlta(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Cliente c SET c.alta = false WHERE c.id = :id AND c.alta = true")
    void darBaja(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Cliente c SET c.nombre = :nombre , c.email = :email, c.password = :password WHERE c.id = :id")
    void modificarCliente(@Param("nombre") String nombre,
                          @Param("email") String email,
                          @Param("password") String password,
                          @Param("id") Long id);
}
