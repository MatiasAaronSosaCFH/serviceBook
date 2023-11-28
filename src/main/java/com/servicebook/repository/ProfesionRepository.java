package com.servicebook.repository;

import com.servicebook.models.Profesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProfesionRepository extends JpaRepository<Profesion, Long> {

    @Query("SELECT p FROM Profesion p WHERE p.alta = true")
    List<Profesion> listarProfesiones();

    @Query("SELECT p FROM Profesion p WHERE p.alta = true AND p.id = :id")
    Optional<Profesion> buscarPorIdDelProovedor();
}
