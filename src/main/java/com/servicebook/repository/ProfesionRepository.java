package com.servicebook.repository;

import com.servicebook.models.Profesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProfesionRepository extends JpaRepository<Profesion, Long> {

    @Query("SELECT p FROM Profesion p WHERE p.alta = true")
    List<Profesion> listarProfesiones();

    @Query("SELECT p FROM Profesion p WHERE p.alta = true AND p.id = :id")
    Optional<Profesion> buscarPorIdDelProovedor(@Param("id") Long id);

    @Query("SELECT p FROM Profesion p WHERE p.nombre = :nombre")
    List<Profesion> buscarProfesion(@Param("nombre") String nombre);

    @Modifying
    @Query("UPDATE Profesion p SET p.nombre = :nombre WHERE p.id = :id")
    void modificarNombre(@Param("nombre") String nombre);

    @Modifying
    @Query("UPDATE Profesion p SET p.alta = true WHERE p.id = :id AND p.alta = true")
    void darAlta(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Profesion p SET p.alta = false WHERE p.id = :id AND p.alta = false")
    void darBaja(@Param("id") Long id);


}
