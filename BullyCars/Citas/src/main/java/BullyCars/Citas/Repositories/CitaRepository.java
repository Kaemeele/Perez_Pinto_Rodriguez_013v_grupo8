package BullyCars.Citas.Repositories;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import BullyCars.Citas.Models.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    // Es vital que este nombre coincida letra por letra con el que usas en el Service
Optional<Cita> findByVehiculoIdAndFechaHora(Long vehiculoId, LocalDateTime fechaHora);}