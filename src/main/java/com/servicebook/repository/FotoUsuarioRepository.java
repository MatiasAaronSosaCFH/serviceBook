package com.servicebook.repository;

import com.servicebook.models.FotoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FotoUsuarioRepository extends JpaRepository<FotoUsuario, Long> {

    @Modifying
    @Query("UPDATE FotoUsuario f SET f.alta = true WHERE f.id = :id")
    void darAlta(@Param("id")Long id);

    @Modifying
    @Query("UPDATE FotoUsuario f SET f.alta = false WHERE f.id = :id")
    void darBaja(@Param("id")Long id);

    @Modifying
    @Query("UPDATE FotoUsuario f SET f.url = :url WHERE f.id = :id")
    void modificarUrl(@Param("url") String url);

    @Modifying
    @Query("UPDATE FotoUsuario f SET f.nombre = :nombre WHERE f.id = :id")
    void modificarNombre(@Param("nombre")String nombre,@Param("id")Long id);

    @Modifying
    @Query("UPDATE FotoUsuario f SET f.fotoId = :fotoId WHERE f.id = :id")
    void modificarFotoId(@Param("fotoId")String fotoID, @Param("id")Long id);

    @Query("SELECT f FROM FotoUsuario f WHERE f.alta = true")
    List<FotoUsuario> listarImagenesDeUsuario();

    @Query("SELECT f FROM FotoUsuario f WHERE f.id = :id")
    Optional<FotoUsuario> buscarFotoPorId(@Param("id")long id);
}
