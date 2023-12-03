package com.servicebook.repository;

import com.servicebook.models.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FotoRepository extends JpaRepository<Foto , Long>  {

    @Query("SELECT f FROM Foto f WHERE f.alta = true")
    List<Foto> listadoDeFotos();

    @Query("SELECT f FROM Foto f WHERE f.alta = true AND f.id = :id")
    Optional<Foto> buscarPorId(@Param("id") Long id);

    @Query("SELECT f FROM Foto f WHERE f.alta = true AND f.trabajo = :id")
    List<Foto> buscarFotosPorTrabajo(@Param("id")Long id);

    @Modifying
    @Query("UPDATE Foto f SET f.alta = false WHERE f.id = :id")
    void darBaja(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Foto f SET f.alta = true WHERE f.id = :id")
    void darAlta(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Foto f SET f.url = :url WHERE f.id = :id")
    void modificarUrl(@Param("url") String url);

    @Modifying
    @Query("UPDATE Foto f SET f.nombre = :nombre WHERE f.id = :id")
    void modificarNombre(@Param("nombre")String nombre,@Param("id")Long id);

    @Modifying
    @Query("UPDATE Foto f SET f.fotoId = :fotoId WHERE f.id = :id")
    void modificarFotoId(@Param("fotoId")String fotoID, @Param("id")Long id);

    @Modifying
    @Query("UPDATE Foto f SET f.trabajo = :trabajo WHERE f.id = :id")
    void modificarTrabajo(@Param("trabajo")Long trabajo, @Param("id")Long id);
}
