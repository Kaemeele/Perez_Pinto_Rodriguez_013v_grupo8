package BullyCars.Estetica.Automotriz.Repositories;

import BullyCars.Estetica.Automotriz.Models.ServicioEstetica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para interactuar con la base de datos y realizar operaciones CRUD.
 */
@Repository
public interface EsteticaRepository extends JpaRepository<ServicioEstetica, Long> {
}
