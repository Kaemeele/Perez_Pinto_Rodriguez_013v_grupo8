package BullyCars.inventario.Repositories;

import BullyCars.inventario.Models.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para interactuar con la base de datos y realizar operaciones CRUD.
 */
@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Long> {
}