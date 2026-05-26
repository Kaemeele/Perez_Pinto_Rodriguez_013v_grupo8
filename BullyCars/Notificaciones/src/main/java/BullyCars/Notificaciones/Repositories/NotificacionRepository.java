package BullyCars.Notificaciones.Repositories;

import BullyCars.Notificaciones.Models.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para interactuar con la base de datos y realizar operaciones CRUD.
 */
@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}
