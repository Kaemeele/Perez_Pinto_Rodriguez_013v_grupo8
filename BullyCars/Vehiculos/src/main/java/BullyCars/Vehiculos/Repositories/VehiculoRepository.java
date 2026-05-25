package BullyCars.Vehiculos.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BullyCars.Vehiculos.Models.Vehiculo;

/**
 * Repositorio JPA para interactuar con la base de datos y realizar operaciones CRUD.
 */
@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}