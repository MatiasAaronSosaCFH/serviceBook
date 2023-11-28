package com.servicebook.repository;

import com.servicebook.models.Cliente;
import com.servicebook.models.Proveedor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.alta = true AND f.id =:id")
    Optional<Cliente> buscarPorId(@Param("id") Long id);

}
