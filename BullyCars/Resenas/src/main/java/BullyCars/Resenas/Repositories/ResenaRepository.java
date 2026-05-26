package BullyCars.Resenas.Repositories;

import BullyCars.Resenas.Models.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para interactuar con la base de datos y realizar operaciones CRUD.
 */
@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
}