package BullyCars.Citas.Repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BullyCars.Citas.Models.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    Optional<Cita> findByVehiculoIdAndFechaHora(Long vehiculoId, LocalDateTime fechaHora);
    java.util.List<Cita> findByConfirmado(Boolean confirmado);
}
