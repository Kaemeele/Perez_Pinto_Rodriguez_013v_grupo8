package BullyCars.Facturacion.Repositories;

import BullyCars.Facturacion.Models.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para interactuar con la base de datos y realizar operaciones CRUD.
 */
@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
