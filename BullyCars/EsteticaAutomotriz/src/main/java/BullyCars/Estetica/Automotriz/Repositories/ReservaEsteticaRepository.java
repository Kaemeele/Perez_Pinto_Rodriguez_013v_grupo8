package BullyCars.Estetica.Automotriz.Repositories;

import BullyCars.Estetica.Automotriz.Models.ReservaEstetica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReservaEsteticaRepository extends JpaRepository<ReservaEstetica, Long> {
    Optional<ReservaEstetica> findByCitaId(Long citaId);
}
