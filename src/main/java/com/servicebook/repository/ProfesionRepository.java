package com.servicebook.repository;

import com.servicebook.models.Profesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfesionRepository extends JpaRepository<Profesion, Long> {


}
