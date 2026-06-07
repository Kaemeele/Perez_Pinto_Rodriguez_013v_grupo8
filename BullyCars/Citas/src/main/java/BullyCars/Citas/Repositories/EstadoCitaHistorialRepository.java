package BullyCars.Citas.Repositories;

import BullyCars.Citas.Models.EstadoCitaHistorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCitaHistorialRepository extends JpaRepository<EstadoCitaHistorial, Long> {
}
