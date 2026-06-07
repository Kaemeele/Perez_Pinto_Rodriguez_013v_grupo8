package BullyCars.Reparaciones.Repositories;

import BullyCars.Reparaciones.Models.OrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo, Long> {
    java.util.Optional<OrdenTrabajo> findByCitaId(Long citaId);
}