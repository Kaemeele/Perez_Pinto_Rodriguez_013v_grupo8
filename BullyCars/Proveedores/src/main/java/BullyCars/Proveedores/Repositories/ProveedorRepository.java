package BullyCars.Proveedores.Repositories;

import BullyCars.Proveedores.Models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para interactuar con la base de datos y realizar operaciones CRUD.
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}