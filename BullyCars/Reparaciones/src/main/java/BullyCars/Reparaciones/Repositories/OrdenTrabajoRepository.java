package BullyCars.Reparaciones.Repositories;

import BullyCars.Reparaciones.Models.OrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para interactuar con la base de datos y realizar operaciones CRUD.
 */
@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo, Long> {
}