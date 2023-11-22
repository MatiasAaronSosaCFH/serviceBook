
package com.servicebook.repository;

import com.servicebook.models.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mathi
 */
@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {
    
}
