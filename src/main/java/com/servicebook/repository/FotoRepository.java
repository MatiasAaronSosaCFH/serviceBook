package com.servicebook.repository;

import com.servicebook.models.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FotoRepository extends JpaRepository<Foto , Long>  {

    @Query("SELECT f FROM Foto f WHERE f.alta = true")
    List<Foto> listadoDeFotos();

    @Query("SELECT f FROM Foto f WHERE f.alta = true AND f.id =:id")
    Optional<Foto> buscarPorId(@Param("id") Long id);

    @Query("UPDATE Foto f SET d.alta = false WHERE f.id = :id")
    void darDeBaja(@Param("id") Long id);

    @Query("UPDATE Foto f SET f.alta = true WHERE fid = :id")
    void darAltaalta(@Param("id") Long id);
}
